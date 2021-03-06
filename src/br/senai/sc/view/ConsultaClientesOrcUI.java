package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrcamentoFlashControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.ClienteTableModel;

public class ConsultaClientesOrcUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JTable table;
	private JTable jtConsultaCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClientesOrcUI frame = new ConsultaClientesOrcUI();
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
	public ConsultaClientesOrcUI() {
		addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent arg0) {
				setLocation(2,40);
			}
		});
		setFrameIcon(new ImageIcon(
				ConsultaClientesOrcUI.class
						.getResource("/br/senai/sc/icons/fornecedores.png")));
		setTitle("Consultar Clientes");
		setBorder(null);
		getContentPane().setBackground(new Color(176, 196, 222));
		setBounds(2, 40, 1192, 500);
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(176, 196, 222));

		JScrollPane scrollPane = new JScrollPane();

		JButton jbSelecionar = new JButton("Selecionar");
		jbSelecionar.setIcon(new ImageIcon(ConsultaClientesOrcUI.class.getResource("/br/senai/sc/icons/add_icon.png")));
		jbSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Cliente cli = null;
				try {
					cli = (Cliente) new ClienteTableModel(new ClienteControl()
							.showAllClientes()).get(jtConsultaCliente
							.getSelectedRow());
					new OrcamentoFlashControl().inserirCliente(cli);
					dispose();
				} catch (ClassNotFoundException | SQLException
						| ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JButton jbCancelar = new JButton("Sair");
		jbCancelar.setIcon(new ImageIcon(ConsultaClientesOrcUI.class.getResource("/br/senai/sc/icons/exit_icon.png")));
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																1171,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																1175,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jbSelecionar,
																				GroupLayout.PREFERRED_SIZE,
																				219,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				jbCancelar,
																				GroupLayout.PREFERRED_SIZE,
																				219,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(15, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 43,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 222,
										GroupLayout.PREFERRED_SIZE)
								.addGap(65)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(jbSelecionar)
												.addComponent(jbCancelar))
								.addContainerGap(198, Short.MAX_VALUE)));

		jtConsultaCliente = new JTable();
		scrollPane.setViewportView(jtConsultaCliente);
		try {
			jtConsultaCliente.setModel(new ClienteTableModel(
					new ClienteControl().showAllClientes()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jtConsultaCliente.getColumnModel().getColumn(0).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtConsultaCliente.getColumnModel().getColumn(1).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtConsultaCliente.getColumnModel().getColumn(1).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtConsultaCliente.getColumnModel().getColumn(2).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtConsultaCliente.getColumnModel().getColumn(3).setResizable(false);
		getContentPane().setLayout(groupLayout);

		final JLabel lblFiltro = new JLabel("Filtro");

		jtfFiltro = new JTextField();
		jtfFiltro.setColumns(10);

		JLabel lblTipoFiltro = new JLabel("Tipo filtro");

		final JComboBox jcbTipoFiltro = new JComboBox();
		jcbTipoFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfFiltro.setText(null);
			}
		});

		jcbTipoFiltro.setModel(new DefaultComboBoxModel(new String[] {"Nome", "CPF / CNPJ", "Email", "Telefone"}));

		JButton btnNewButton_1 = new JButton("Procurar");
		btnNewButton_1.setIcon(new ImageIcon(ConsultaClientesOrcUI.class.getResource("/br/senai/sc/icons/search.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Cliente cli = new Cliente();
				try {
					jtConsultaCliente.setModel(
							new ClienteTableModel(
									new ClienteControl().showFilterClientes( 
											jcbTipoFiltro.getSelectedItem().toString(),jtfFiltro.getText(), false
									)
							)
					);
									
									
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(20)
						.addComponent(lblFiltro)
						.addGap(10)
						.addComponent(jtfFiltro, GroupLayout.PREFERRED_SIZE,
								625, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 78,
								Short.MAX_VALUE)
						.addComponent(lblTipoFiltro)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(jcbTipoFiltro,
								GroupLayout.PREFERRED_SIZE, 124,
								GroupLayout.PREFERRED_SIZE)
						.addGap(76)
						.addComponent(btnNewButton_1,
								GroupLayout.PREFERRED_SIZE, 129,
								GroupLayout.PREFERRED_SIZE).addGap(30)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblFiltro)
												.addComponent(
														jtfFiltro,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_1)
												.addComponent(lblTipoFiltro)
												.addComponent(
														jcbTipoFiltro,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(8)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
