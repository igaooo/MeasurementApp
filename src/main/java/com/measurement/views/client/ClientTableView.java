package com.measurement.views.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.measurement.config.Constants;
import com.measurement.config.Page;
import com.measurement.models.Client;
import com.measurement.services.ClientService;
import com.measurement.views.table.CustomTableCellRenderer;
import com.measurement.views.table.TableHeaderRenderer;

public class ClientTableView extends JFrame {
private static final long serialVersionUID = 4304802158559819925L;
	
	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JTable table;
	private Page<Client> page;
	
	private TableClientModel model;
	private int line = 0;
	private int column = 0;
	private int pageLength = 10;
	private int currentPage = 0;
	
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnConsult;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnFirst;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientTableView frame = new ClientTableView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientTableView() {
		initComponents();
		eventHandle();
		showClient();
		initTable();
	}
	
	public void initTable() {
		System.out.println(page.getContent().size());
		model = new TableClientModel(page.getContent());
		model.fireTableDataChanged();
		table.setModel(model);
		table.getTableHeader().setDefaultRenderer(new TableHeaderRenderer());
		
		CustomTableCellRenderer renderTable = new CustomTableCellRenderer();
		
		for(int column=0; column < model.getColumnCount(); column++) {
			table.setDefaultRenderer(model.getColumnClass(column), renderTable);
		}
	}
	
	private void eventHandlers() {
		
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = 1;
				initTable();
			}
		});
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currentPage > 1) {
					currentPage = currentPage - 1;
					initTable();
				}
				
			}
		});
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currentPage < page.getTotalPage() ) {
					currentPage = currentPage + 1;
					initTable();
				}
				
			}
		});
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPage = page.getTotalPage();
				initTable();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showClienteFrame(Constants.ADD);
				initTable();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLinhaTabela();
				showClienteFrame(Constants.UPDATE);
				initTable();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLinhaTabela();
				showClienteFrame(Constants.DELETE);
				initTable();
				
			}
		});
		btnConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLinhaTabela();
				showClienteFrame(Constants.CONSULT);
			}
		});
	
	
	}
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 983, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 969, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 10, 67, 26);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Verdana", Font.PLAIN, 20));
		textFieldSearch.setBounds(87, 10, 698, 30);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		btnSearch = new JButton("Pesquisar");
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSearch.setBounds(795, 8, 164, 32);
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 46, 969, 563);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPaneClient = new JScrollPane();
		scrollPaneClient.setBounds(10, 10, 959, 403);
		panel_1.add(scrollPaneClient);
		
		table = new JTable();
		scrollPaneClient.setViewportView(table);
		
		btnAdd = new JButton("Incluir");
		btnAdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnAdd.setBounds(142, 521, 164, 32);
		panel_1.add(btnAdd);
		
		btnDelete = new JButton("Excluir");
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnDelete.setBounds(316, 521, 164, 32);
		panel_1.add(btnDelete);
		
		btnUpdate = new JButton("Alterar");
		btnUpdate.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnUpdate.setBounds(490, 521, 164, 32);
		panel_1.add(btnUpdate);
		
		btnConsult = new JButton("Consultar");
		btnConsult.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnConsult.setBounds(664, 521, 164, 32);
		panel_1.add(btnConsult);
		
		btnFirst = new JButton("Primeiro");
		btnFirst.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnFirst.setBounds(142, 423, 164, 32);
		panel_1.add(btnFirst);
		
		btnPrev = new JButton("Anterior");
		btnPrev.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnPrev.setBounds(316, 423, 164, 32);
		panel_1.add(btnPrev);
		
		btnNext = new JButton("Proximo");
		btnNext.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnNext.setBounds(490, 423, 164, 32);
		panel_1.add(btnNext);
		
		btnLast = new JButton("Ultimo");
		btnLast.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnLast.setBounds(664, 423, 164, 32);
		panel_1.add(btnLast);
	}
	
	public void initialTable() {
    	
    	listarCliente();
   	
    	model = new TableClientModel(page.getContent());
    	
    	model.fireTableDataChanged();
    	
    	table.setModel(model);
    	
    	RenderHeaderTable renderHeader = new RenderHeaderTable();
    	
    	table.getTableHeader().setDefaultRenderer(renderHeader);
    	
    	RenderTable render = new RenderTable();
    	
    	for (int column=0; column < model.getColumnCount(); column++) {
    		table.setDefaultRenderer(model.getColumnClass(column), render);
    	}
    	
    	
    	for (int col = 0; col < model.getColumnCount(); col++) {
    		TableColumn column = table.getColumnModel().getColumn(col);
    		column.setMinWidth(100);
    		column.setMaxWidth(100);
    		column.setPreferredWidth(100);
    	}
    	
    	
    	
    }

	public void showClient() {
		page = getClientService().paginatedList(currentPage, pageLength);
	}
	
	public void eventHandle() {
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
	ClientService getClientService() {return new ClientService(); }
}

public ClientService getClienteService() {
	return new ClientService();
}


public int getLine() {
	return line;
}


public void setLine(int line) {
	this.line = line;
}


public int getColumn() {
	return column;
}


public void setColumn(int column) {
	this.column = column;
}


public Client getClient() {
	return new Client();
}
