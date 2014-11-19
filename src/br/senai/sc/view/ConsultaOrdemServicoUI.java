package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.ConsultaOrdemServicoTableModel;
import br.senai.sc.utils.MaskFields;
import br.senai.sc.utils.OrdemServicoTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultaOrdemServicoUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private static JTable jtListaOrdemServicos;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private JTextField tfCliente;
	private JFormattedTextField jftfDataInicial;
	private JFormattedTextField jftfDataFinal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					OrdemServicoUI frame = new OrdemServicoUI(null);
					jtListaOrdemServicos
							.setModel(new ConsultaOrdemServicoTableModel(
									new OrdemServicoControl()
											.showOrdemServicos()));
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

	public ConsultaOrdemServicoUI() throws ClassNotFoundException, SQLException {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0,0);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 434, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 271, Short.MAX_VALUE));
		getContentPane().setLayout(groupLayout);
		setBackground(SystemColor.inactiveCaption);
		setRootPaneCheckingEnabled(false);
		setEnabled(false);
		setBorder(null);
		setSize(1500, 1000);
		setTitle("ORC R2F - Consulta Ordem de Servi\u00E7o");
		setBounds(0, 0, 1200, 600);
		listaClientes = new ClienteControl().showAllClientes();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		listaServicos = new ServicoControl().showAllServicos();
		DefaultComboBoxModel<Servico> modelServico = new DefaultComboBoxModel<Servico>();
		for (Servico servico : listaServicos) {
			modelServico.addElement(servico);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Or\u00E7amento(s)",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1180,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 421,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(142, Short.MAX_VALUE)));

		JScrollPane scrollpane = new JScrollPane();

		jtListaOrdemServicos = new JTable();
		jtListaOrdemServicos.setModel(new ConsultaOrdemServicoTableModel(
				new OrdemServicoControl().showOrdemServicos()));
		jtListaOrdemServicos.getColumnModel().getColumn(0).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(0)
				.setPreferredWidth(50);
		jtListaOrdemServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(1)
				.setPreferredWidth(150);
		scrollpane.setViewportView(jtListaOrdemServicos);

		JButton btnEditarOs = new JButton("Editar OS");
		btnEditarOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrdemServico os = null;
				try {
					if (jtListaOrdemServicos.getSelectedRow() == -1)
						throw new ArrayIndexOutOfBoundsException(
								"Selecione uma OS");

					os = (OrdemServico) new ConsultaOrdemServicoTableModel(
							new OrdemServicoControl().showOrdemServicos())
							.get(jtListaOrdemServicos.getSelectedRow());

					OrdemServicoUI osUI = null;
					osUI = new OrdemServicoUI(os);
					PrincipalUI.obterInstancia().getContentPane().add(osUI);
					osUI.setFocusable(true);
					osUI.moveToFront();
					getContentPane().add(osUI, 0);
					osUI.requestFocus();
					osUI.setVisible(true);
					osUI.addInternalFrameListener(new InternalFrameListener() {
						public void internalFrameClosed(InternalFrameEvent e) {
							try {
								jtListaOrdemServicos
										.setModel(new ConsultaOrdemServicoTableModel(
												new OrdemServicoControl()
														.showOrdemServicos()));
							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
						}

						public void internalFrameActivated(
								InternalFrameEvent arg0) {
						}

						public void internalFrameClosing(InternalFrameEvent arg0) {
						}

						public void internalFrameDeactivated(
								InternalFrameEvent arg0) {
						}

						public void internalFrameDeiconified(
								InternalFrameEvent arg0) {
						}

						public void internalFrameIconified(
								InternalFrameEvent arg0) {
						}

						public void internalFrameOpened(InternalFrameEvent arg0) {
						}
					});
				} catch (ClassNotFoundException | SQLException
						| ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JLabel label = new JLabel("Cliente:");

		tfCliente = new JTextField();
		tfCliente.setColumns(10);

		MaskFields mascara = new MaskFields();
		JFormattedTextField formattedTextField = null;
		try {

			jftfDataInicial = new JFormattedTextField(mascara.maskData(null));
			jftfDataFinal = new JFormattedTextField(mascara.maskData(null));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel label_1 = new JLabel("Status:");

		final JComboBox jcbStatus = new JComboBox();
		jcbStatus.setForeground(Color.BLACK);
		jcbStatus.setModel(new DefaultComboBoxModel(new String[] { "Todos",
				"Aguardando", "Aprovado" }));

		jcbStatus.setForeground(Color.BLACK);

		JLabel label_2 = new JLabel("Data Inicial:");

		JLabel label_3 = new JLabel("Data Final:");

		JButton button = new JButton("Pesquisar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jtListaOrdemServicos
							.setModel(new ConsultaOrdemServicoTableModel(
									new OrdemServicoControl()
											.procurarPorFiltro(tfCliente
													.getText(), jcbStatus
													.getSelectedItem()
													.toString(),
													jftfDataInicial.getText(),
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollpane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfCliente, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jcbStatus, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jftfDataInicial, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(jftfDataFinal, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(39))
						.addComponent(btnEditarOs))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(tfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)
								.addComponent(jcbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(jftfDataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(jftfDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
					.addGap(9)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnEditarOs))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
