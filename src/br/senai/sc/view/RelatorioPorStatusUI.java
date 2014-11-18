package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.RelatorioControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.MaskFields;
import br.senai.sc.utils.RelatorioTableModel;

public class RelatorioPorStatusUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtTabelaStatus;
	private ArrayList<Cliente> listaClientes;
	private JFormattedTextField jftfDataInicial;
	private JFormattedTextField jftfDataFinal;
	private JTextField tfCliente;

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

		final JComboBox jcbStatus = new JComboBox();
		jcbStatus.setForeground(Color.BLACK);
		jcbStatus.setModel(new DefaultComboBoxModel(new String[] { "Todos",
				"Aguardando", "Aprovado" }));

		JLabel lblStatus = new JLabel("Status:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					jtTabelaStatus.setModel(new RelatorioTableModel(
							new RelatorioControl().procurarPorFiltro(tfCliente
									.getText(), jcbStatus.getSelectedItem()
									.toString(), jftfDataInicial.getText(),
									jftfDataFinal.getText())));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPesquisar.setIcon(new ImageIcon(RelatorioPorStatusUI.class
				.getResource("/br/senai/sc/icons/search.png")));

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		JLabel lblNewLabel = new JLabel("Cliente:");

		MaskFields mascara = new MaskFields();
		JFormattedTextField formattedTextField = null;
		try {

			jftfDataInicial = new JFormattedTextField(mascara.maskData(null));
			jftfDataFinal = new JFormattedTextField(mascara.maskData(null));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// COMBO BOX CLIENTE
		JComboBox jcbCliente = new JComboBox();
		listaClientes = new ClienteControl().showAllClientes();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}

		JLabel lblDataInicial = new JLabel("Data Inicial:");

		JLabel lblDataFinal = new JLabel("Data Final:");

		JButton button = new JButton("Imprimir");

		tfCliente = new JTextField();
		tfCliente.setColumns(10);

		JLabel lblValorTotalR = new JLabel("Valor Total: R$");

		JLabel jlValor = new JLabel("00.00");

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
																						1148,
																						Short.MAX_VALUE)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblNewLabel)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										tfCliente,
																										GroupLayout.PREFERRED_SIZE,
																										446,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.UNRELATED)
																								.addComponent(
																										lblStatus)
																								.addPreferredGap(
																										ComponentPlacement.UNRELATED)
																								.addComponent(
																										jcbStatus,
																										GroupLayout.PREFERRED_SIZE,
																										118,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.UNRELATED)
																								.addComponent(
																										lblDataInicial)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jftfDataInicial,
																										GroupLayout.PREFERRED_SIZE,
																										86,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										lblDataFinal)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jftfDataFinal,
																										GroupLayout.PREFERRED_SIZE,
																										94,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										btnPesquisar,
																										GroupLayout.PREFERRED_SIZE,
																										106,
																										GroupLayout.PREFERRED_SIZE)))
																.addContainerGap())
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		button,
																		GroupLayout.PREFERRED_SIZE,
																		125,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		btnSair,
																		GroupLayout.PREFERRED_SIZE,
																		125,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		734,
																		Short.MAX_VALUE)
																.addComponent(
																		lblValorTotalR)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jlValor)
																.addGap(41)))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(
														jcbStatus,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStatus)
												.addComponent(lblDataInicial)
												.addComponent(lblDataFinal)
												.addComponent(
														jftfDataInicial,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jftfDataFinal,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														tfCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPesquisar))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 385,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														btnSair,
														GroupLayout.PREFERRED_SIZE,
														46,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														button,
														GroupLayout.PREFERRED_SIZE,
														46,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		lblValorTotalR)
																.addComponent(
																		jlValor)))
								.addGap(44)));

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
