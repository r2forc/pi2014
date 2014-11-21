package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.utils.ConsultaOrdemServicoTableModel;
import br.senai.sc.utils.MaskFields;

public class ConsultaOrdemServicoUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtListaOrdemServicos;
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
					ConsultaOrdemServicoUI frame = new ConsultaOrdemServicoUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void atualizarTableModel() {
		jtListaOrdemServicos = new JTable();
		try {
			jtListaOrdemServicos.setModel(new ConsultaOrdemServicoTableModel(
					new OrdemServicoControl().showOrdemServicos()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ConsultaOrdemServicoUI() throws ClassNotFoundException, SQLException {
		setFrameIcon(new ImageIcon(ConsultaOrdemServicoUI.class.getResource("/br/senai/sc/icons/ordem_serc.png")));
		setEnabled(false);
		setRootPaneCheckingEnabled(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0, 0);
			}
		});
		atualizarTableModel();
		setTitle("R2Forc - Consulta Or\u00E7amentos");
		setBorder(null);
		setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1200, 550);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1180,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 503,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

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
		btnPesquisar.setIcon(new ImageIcon(ConsultaOrdemServicoUI.class
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

		tfCliente = new JTextField();
		tfCliente.setColumns(10);

		final JButton button = new JButton("Editar OS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (jtListaOrdemServicos.getSelectedRow() == -1)
						throw new ArrayIndexOutOfBoundsException(
								"Selecione uma OS");
					
					OrdemServico os = OrdemServicoDAO.getInstace().getListaOS().get(jtListaOrdemServicos.getSelectedRow());

					OrdemServicoUI osUI = new OrdemServicoUI(os);
					osUI.setFocusable(true);
					getParent().add(osUI, 0);
					osUI.requestFocus();
					osUI.setVisible(true);
					hide();

					osUI.addInternalFrameListener(new InternalFrameListener() {
						public void internalFrameClosed(InternalFrameEvent e) {
							try {
								jtListaOrdemServicos
										.setModel(new ConsultaOrdemServicoTableModel(
												new OrdemServicoControl()
														.showOrdemServicos()));
								show();
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

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel,
										GroupLayout.DEFAULT_SIZE, 44,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfCliente,
										GroupLayout.DEFAULT_SIZE, 424,
										Short.MAX_VALUE)
								.addGap(33)
								.addComponent(lblStatus,
										GroupLayout.DEFAULT_SIZE, 44,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jcbStatus, 0, 103,
										Short.MAX_VALUE)
								.addGap(18)
								.addComponent(lblDataInicial,
										GroupLayout.DEFAULT_SIZE, 62,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jftfDataInicial,
										GroupLayout.DEFAULT_SIZE, 71,
										Short.MAX_VALUE)
								.addGap(33)
								.addComponent(lblDataFinal)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jftfDataFinal,
										GroupLayout.DEFAULT_SIZE, 79,
										Short.MAX_VALUE)
								.addGap(18)
								.addComponent(btnPesquisar,
										GroupLayout.PREFERRED_SIZE, 123,
										GroupLayout.PREFERRED_SIZE).addGap(127))
				.addGroup(
						gl_panel.createSequentialGroup()
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 1170,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(18)
								.addComponent(button)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSair,
										GroupLayout.PREFERRED_SIZE, 95,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(1057, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(11)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(
														tfCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jcbStatus,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStatus)
												.addComponent(lblDataInicial)
												.addComponent(
														jftfDataInicial,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataFinal)
												.addComponent(
														jftfDataFinal,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPesquisar))
								.addGap(11)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 420,
										Short.MAX_VALUE)
								.addGap(11)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														btnSair,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(button))));

		jtListaOrdemServicos.getColumnModel().getColumn(0).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(0)
				.setPreferredWidth(100);
		jtListaOrdemServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(2).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(jtListaOrdemServicos);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}