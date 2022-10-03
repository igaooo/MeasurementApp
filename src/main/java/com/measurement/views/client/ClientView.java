package com.measurement.views.client;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.measurement.models.Client;
import com.measurement.services.ClientService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientView extends JFrame {

	/**
	 * UUID of the client application
	 */
	private static final long serialVersionUID = -274184608771120417L;
	private JPanel contentPane;
	private JTextField textFieldFind;
	private JTextField textFieldName;
	private JTextField textFieldPwd;
	private JTextField textFieldUserName;
	private JButton btnFind;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnClear;

	private Long currentIdEdition = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView frame = new ClientView();
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
	public ClientView() {
		init();
		eventHandle();
	}

	private void init() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 41, 106, 46);
		contentPane.add(lblNewLabel);

		textFieldFind = new JTextField();
		textFieldFind.setBorder(null);
		textFieldFind.setFont(new Font("Serif", Font.PLAIN, 20));
		textFieldFind.setBounds(126, 54, 520, 33);
		contentPane.add(textFieldFind);
		textFieldFind.setColumns(10);

		btnFind = new JButton("Buscar");
		btnFind.setFont(new Font("Serif", Font.BOLD, 20));
		btnFind.setBounds(656, 54, 154, 33);
		contentPane.add(btnFind);

		JLabel lblClient = new JLabel("Cliente");
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 35));
		lblClient.setBounds(375, 125, 144, 58);
		contentPane.add(lblClient);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 194, 106, 46);
		contentPane.add(lblNewLabel_1);

		textFieldName = new JTextField();
		textFieldName.setBorder(null);
		textFieldName.setFont(new Font("Serif", Font.PLAIN, 20));
		textFieldName.setColumns(10);
		textFieldName.setBounds(126, 207, 684, 33);
		contentPane.add(textFieldName);

		btnSave = new JButton("Salvar");
		btnSave.setFont(new Font("Serif", Font.BOLD, 20));
		btnSave.setBounds(167, 424, 169, 39);
		contentPane.add(btnSave);

		btnDelete = new JButton("Excluir");
		btnDelete.setFont(new Font("Serif", Font.BOLD, 20));
		btnDelete.setBounds(367, 424, 169, 39);
		contentPane.add(btnDelete);

		btnClear = new JButton("Limpar");
		btnClear.setFont(new Font("Serif", Font.BOLD, 20));
		btnClear.setBounds(565, 424, 169, 39);
		contentPane.add(btnClear);

		textFieldPwd = new JTextField();
		textFieldPwd.setBorder(null);
		textFieldPwd.setFont(new Font("Serif", Font.PLAIN, 20));
		textFieldPwd.setColumns(10);
		textFieldPwd.setBounds(126, 263, 684, 33);
		contentPane.add(textFieldPwd);

		JLabel lblNewLabel_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 250, 106, 46);
		contentPane.add(lblNewLabel_1_1);

		textFieldUserName = new JTextField();
		textFieldUserName.setBorder(null);
		textFieldUserName.setFont(new Font("Serif", Font.PLAIN, 20));
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(176, 319, 634, 33);
		contentPane.add(textFieldUserName);

		JLabel lblNewLabel_1_2 = new JLabel("Nome de usuário:");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Serif", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 306, 158, 46);
		contentPane.add(lblNewLabel_1_2);
	}

	private void eventHandle() {
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findClient();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveClient();
				clearView();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUser();
				clearView();
			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearView();

				if (currentIdEdition != null) {
					currentIdEdition = null;
				}
			}
		});
	}

	private void deleteUser() {
		if (currentIdEdition == null) {
			JOptionPane.showMessageDialog(null,
					"Usuário não definido",
					"Erro",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		getService()
				.deleteById(currentIdEdition);
	}

	private void setClientInView(Client client) {
		textFieldName.setText(client.getName());
		textFieldPwd.setText("");
		textFieldUserName.setText(client.getUserName());
		textFieldPwd.setText(client.getPassword());
	}

	private void clearView() {
		textFieldFind.setText("");
		textFieldName.setText("");
		textFieldPwd.setText("");
		textFieldUserName.setText("");
	}

	private void findClient() {
		Client client = getService().getByUserName(textFieldFind.getText());

		if (client != null) {
			currentIdEdition = client.getId();
			setClientInView(client);
			return;
		}

		JOptionPane.showMessageDialog(null,
				"Nome de usuário não encontrado",
				"Erro",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private Boolean validateClient(Client client) {
		if (client.getName().length() == 0) {
			return false;
		} else if (client.getPassword().length() == 0) {
			return false;
		} else if (client.getUserName().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	private void saveClient() {
		Client client = getClient();

		client.setName(textFieldName.getText());
		client.setPassword(textFieldPwd.getText());
		client.setUserName(textFieldUserName.getText());

		if (!validateClient(client)) {
			JOptionPane.showMessageDialog(null,
					"Preencha todos os campos",
					"Erro",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (currentIdEdition != null) {
			client.setId(currentIdEdition);
			getService().update(client);
			currentIdEdition = null;
			JOptionPane.showMessageDialog(null,
					"Usuário alterado com sucesso",
					"Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

			return;
		}

		getService().add(client);
		
		JOptionPane.showMessageDialog(null,
				"Usuário salvo com sucesso",
				"Sucesso",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private ClientService getService() {
		return new ClientService();
	}

	private Client getClient() {
		return new Client();
	}
}
