package com.measurement.views.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class TableModel<T> extends AbstractTableModel {
	private static final long serialVersionUID = 4355956103776161347L;
	protected List<T> table;
    protected String column[];

    public TableModel() {
		table = new ArrayList<T>();
	}

    public TableModel(List<T> table) {
		this.table = table;
	}

    public int getRowCount() {
        return table.size();
    }

    public int getColumnCount() {
        return column.length;
    }

    public T getValueAt(int line) {
        return table.get(line);
    }

    public void setValueAt(int index, T obj) {
        table.set(index, obj);
    }

    public List<T> getTable() {
        return table;
    }

    public void setTable(List<T> table) {
        this.table = table;
    }

    public String[] getColumn() {
        return column;
    }

    public void setColumn(String[] column) {
        this.column = column;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
