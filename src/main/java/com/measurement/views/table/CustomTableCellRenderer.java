package com.measurement.views.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 5815807740699278387L;

    private DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private Component component;

    public CustomTableCellRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setHorizontalAlignment(0);
        setBorder(null);
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(58, 159, 161)));
        setFont(new Font("Verdana", Font.PLAIN, 20));

        if (isSelected) {
            component.setForeground(table.getSelectionForeground());
            component.setBackground(table.getSelectionBackground());
        } else {
            component.setForeground(table.getSelectionForeground());
            component.setBackground(row % 2 == 0 ? new Color(240, 240, 255) : table.getBackground());
        }

        if(value instanceof Double) {
			Number number = (Number) value;
			String text = decimalFormat.format(decimalFormat);
			setText(text);
			setForeground(number.doubleValue() < 0 ? Color.RED: Color.BLACK);
			setHorizontalAlignment(RIGHT);
		}
		
		if(value instanceof Integer) {
			setHorizontalAlignment(CENTER);
		}
		
		if(value != null && value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String textDate = sdf.format(date);
			setText(textDate);
			setHorizontalAlignment(CENTER);
		}
		
		// Set the component width to match the width of its table cell
        // and make the height arbitrarily large to accommodate all the contents
        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);

        // Now get the fitted height for the given width
        int rowHeight = this.getPreferredSize().height;

        // Get the current table row height
        int actualRowHeight = table.getRowHeight(row);

        // Set table row height to fitted height.
        // Important to check if this has been done already
        // to prevent a never-ending loop.
        if (rowHeight != actualRowHeight) {
           table.setRowHeight(row, rowHeight);
        }
        
		TableColumn col = table
				.getColumnModel()
				.getColumn(column);
			
		col.setMinWidth(100);

        return component;
    }
}
