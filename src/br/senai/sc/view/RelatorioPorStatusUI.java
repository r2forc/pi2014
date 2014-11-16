package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.RelatorioControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.ClienteTableModel;
import br.senai.sc.utils.RelatorioTableModel;

public class RelatorioPorStatusUI extends JInternalFrame {
	private JTable jtTabelaStatus;
	private ArrayList<Cliente> listaClientes;
	private JComboBox jcbCliente_1;
	private JTextField tfDataInicial;
	private JTextField tfDataFinal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioPorStatusUI frame = new RelatorioPorStatusUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public RelatorioPorStatusUI() throws ClassNotFoundException, SQLException {
		setClosable(true);
		setTitle("Relatorio por Status");
		setBorder(null);
		setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1200, 600);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(null, "Consulta", TitledBorder.LEFT,
				TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 980,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 551,
								Short.MAX_VALUE).addContainerGap()));

		JComboBox jcbStatus = new JComboBox();
		jcbStatus.setForeground(SystemColor.windowBorder);
		jcbStatus.setModel(new DefaultComboBoxModel(new String[] { "Status" }));

		JLabel lblStatus = new JLabel("Status:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnPesquisar = new JButton("Pesquisar");

		JButton btnImprimir = new JButton("Imprimir");

		JLabel lblNewLabel = new JLabel("Cliente:");

		// COMBO BOX CLIENTE
		JComboBox jcbCliente = new JComboBox();
		listaClientes = new ClienteControl().showAllClientes();
		jcbCliente_1 = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbCliente_1.setModel(modelCliente);

		JLabel lblDataInicial = new JLabel("Data Inicial:");

		tfDataInicial = new JTextField();
		tfDataInicial.setColumns(10);

		JLabel lblDataFinal = new JLabel("Data Final:");

		tfDataFinal = new JTextField();
		tfDataFinal.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														scrollPane,
														GroupLayout.DEFAULT_SIZE,
														1148, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblNewLabel)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jcbCliente_1,
																		GroupLayout.PREFERRED_SIZE,
																		438,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		lblStatus)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		jcbStatus,
																		0,
																		171,
																		Short.MAX_VALUE)
																.addGap(18)
																.addComponent(
																		lblDataInicial)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		tfDataInicial,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		lblDataFinal)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		tfDataFinal,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		btnPesquisar,
																		GroupLayout.PREFERRED_SIZE,
																		106,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														btnImprimir,
														Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE,
														125,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnPesquisar)
												.addComponent(
														jcbStatus,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStatus)
												.addComponent(lblNewLabel)
												.addComponent(
														jcbCliente_1,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataInicial)
												.addComponent(
														tfDataInicial,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataFinal)
												.addComponent(
														tfDataFinal,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 385,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnImprimir,
										GroupLayout.PREFERRED_SIZE, 46,
										GroupLayout.PREFERRED_SIZE).addGap(46)));

		jtTabelaStatus = new JTable();

		try {
			jtTabelaStatus.setModel(new RelatorioTableModel(
					new RelatorioControl().showAllRelatorios()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jtTabelaStatus.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaStatus.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtTabelaStatus.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaStatus.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaStatus.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(jtTabelaStatus);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
