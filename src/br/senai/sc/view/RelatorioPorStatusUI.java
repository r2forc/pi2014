package br.senai.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class RelatorioPorStatusUI extends JInternalFrame {
	private JTextField jtfSelecaoCliente;
	private JTable jtTabelaStatus;

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
	 */
	public RelatorioPorStatusUI() {
		setTitle("Relatorio por Status");
		setBorder(null);
		setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1000, 600);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
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

		jtfSelecaoCliente = new JTextField();
		jtfSelecaoCliente.setForeground(SystemColor.windowBorder);
		jtfSelecaoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jtfSelecaoCliente.setColumns(10);

		JComboBox jcbStatus = new JComboBox();
		jcbStatus.setForeground(SystemColor.windowBorder);
		jcbStatus.setModel(new DefaultComboBoxModel(new String[] { "Status" }));

		JLabel lblStatus = new JLabel("Status:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnPesquisar = new JButton("Pesquisar");

		JButton btnImprimir = new JButton("Imprimir");

		JLabel lblNewLabel = new JLabel("Cliente:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						scrollPane,
																						GroupLayout.DEFAULT_SIZE,
																						948,
																						Short.MAX_VALUE)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblNewLabel)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jtfSelecaoCliente,
																										GroupLayout.PREFERRED_SIZE,
																										402,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(54)
																								.addComponent(
																										lblStatus)
																								.addPreferredGap(
																										ComponentPlacement.UNRELATED)
																								.addComponent(
																										jcbStatus,
																										0,
																										281,
																										Short.MAX_VALUE)
																								.addPreferredGap(
																										ComponentPlacement.UNRELATED)
																								.addComponent(
																										btnPesquisar,
																										GroupLayout.PREFERRED_SIZE,
																										106,
																										GroupLayout.PREFERRED_SIZE)))
																.addContainerGap())
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnImprimir,
																		GroupLayout.PREFERRED_SIZE,
																		125,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfSelecaoCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPesquisar)
												.addComponent(
														jcbStatus,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStatus)
												.addComponent(lblNewLabel))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnImprimir,
										GroupLayout.PREFERRED_SIZE, 46,
										GroupLayout.PREFERRED_SIZE).addGap(32)));

		jtTabelaStatus = new JTable();
		jtTabelaStatus.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "Cliente", "Status", "Data", "Valor" }) {
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, Double.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
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
