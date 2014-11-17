package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrcamentoFlashControl;
import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.OrcamentoFlashTableModel;
import br.senai.sc.utils.OrdemServicoTableModel;
import br.senai.sc.utils.ServicoTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrdemServicoUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jtfQuantidadeCopias;
	private JTextField jtfOriginais;
	private JTable jtListaItensVenda;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private Double somaTotal = 0.00;
	private JTextField jtfCliente;
	JLabel tfValorTotal = new JLabel("0,00");
	private JTextField jtfValorUnitario;
	private JTextField jtfServico;
	private JTable jtListaServicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdemServicoUI frame = new OrdemServicoUI();
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

	public OrdemServicoUI() throws ClassNotFoundException, SQLException {
		setClosable(true);
		setBackground(SystemColor.inactiveCaption);
		setRootPaneCheckingEnabled(false);
		setEnabled(false);
		setBorder(null);
		setSize(1500, 1000);
		setTitle("ORC R2F - Efetuar Ordem de Servi\u00E7o");
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
		panel.setBorder(new TitledBorder(null, "Ordem de Servi\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1180,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 501,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(62, Short.MAX_VALUE)));

		JScrollPane spItensOS = new JScrollPane();

		jtListaItensVenda = new JTable();
		jtListaItensVenda.setModel(new OrdemServicoTableModel(
				new OrdemServicoControl().showItensServicoOrdemServicos()));
		jtListaItensVenda.getColumnModel().getColumn(0).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaItensVenda.getColumnModel().getColumn(1).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(1).setPreferredWidth(150);
		spItensOS.setViewportView(jtListaItensVenda);

		jtfCliente = new JTextField();
		jtfCliente.setEditable(false);
		jtfCliente.setColumns(10);

		// PREENCHE O COMBOBOX CLIENTE
		JLabel lblCliente = new JLabel("Cliente:");

		// PREENCHE O MODEL SERVICO
		JLabel lblServico = new JLabel("Servi\u00E7os:");

		// ADICIONA ITEM NO TABLEMODEL ORCAMENTO
		JButton jbAdicionarItem = new JButton("Adicionar Serviço");
		jbAdicionarItem
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\add_icon.png"));
		jbAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico serv = new Servico();
				try {
					new OrdemServicoControl();
					serv.setId(new ServicoControl().showAllServicos()
							.get(jtListaServicos.getSelectedRow()).getId());
					serv.setDescricao(new ServicoControl().showAllServicos()
							.get(jtListaServicos.getSelectedRow())
							.getDescricao());
					serv.setValorUnt(Double.parseDouble(jtfValorUnitario
							.getText()));
					serv.setOriginais(Integer.parseInt(jtfOriginais.getText()));
					serv.setCopias(Integer.parseInt(jtfQuantidadeCopias
							.getText()));
					OrcamentoFlashControl.inserirServico(serv);
					jtListaItensVenda.setModel(new OrdemServicoTableModel(
							new OrdemServicoControl()
									.showItensServicoOrdemServicos()));
					Double valorTotal = 0.0;

					OrcamentoFlashDAO of = new OrcamentoFlashDAO();

					for (int i = 0; i < of.getInstace().mostrarServicos()
							.size(); i++) {
						valorTotal += ((of.getInstace().mostrarServico(i)
								.getValorUnt()
								* (of.getInstace().mostrarServico(i)
										.getCopias()) * of.getInstace()
								.mostrarServico(i).getOriginais()));
					}

					if (valorTotal.toString().length() > 7) {
						String[] valorDivido = valorTotal.toString().split(
								"\\.");
						valorDivido[1] = valorDivido[1].substring(0, 2);
						tfValorTotal.setText(valorDivido[0] + "."
								+ valorDivido[1]);
					} else {
						tfValorTotal.setText(valorTotal.toString());
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Numero inválidos");
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null,
							"Selecione um Serviço para adicionar");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Falha ao Adicionar");
				}
			}
		});

		JLabel lblQuantidade = new JLabel("Quantidade Original:");

		jtfQuantidadeCopias = new JTextField();
		jtfQuantidadeCopias.setColumns(10);

		JLabel lblCopias = new JLabel("Copias:");

		jtfOriginais = new JTextField();
		jtfOriginais.setColumns(10);

		JLabel jlTotalVenda = new JLabel("Total:  R$:");

		JButton jbRemoverItem = new JButton("Remover Item");
		jbRemoverItem
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\exit_icon.png"));
		jbRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrdemServicoControl osc = new OrdemServicoControl();
				int opc = JOptionPane.showConfirmDialog(null,
						"Deseja excluir o serviço selecionado?",
						"Excluir serviço", JOptionPane.YES_NO_OPTION);

				if (opc == 0) {

					try {
						OrdemServico os = (OrdemServico) new OrdemServicoTableModel(
								new OrdemServicoControl()
										.showItensServicoOrdemServicos())
								.get(jtListaItensVenda.getSelectedRow());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		JLabel label = new JLabel("Valor Unit\u00E1rio:");

		jtfValorUnitario = new JTextField();
		jtfValorUnitario.setColumns(10);

		JScrollPane spListaServicos = new JScrollPane();

		jtfServico = new JTextField();
		jtfServico.setText("");
		jtfServico.setColumns(10);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\Felipe\\git\\pi2014\\src\\br\\senai\\sc\\icons\\search.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jtListaServicos.setModel(new ServicoTableModel(
							new ServicoControl().showFilterServicos(jtfServico
									.getText())));
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});

		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.setIcon(new ImageIcon(
				"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\save_icon.png"));

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\1415673847_exit.png"));
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
																.addComponent(
																		spItensOS,
																		GroupLayout.PREFERRED_SIZE,
																		1149,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap())
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addGroup(
																						Alignment.TRAILING,
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										jlTotalVenda,
																										GroupLayout.PREFERRED_SIZE,
																										66,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										tfValorTotal,
																										GroupLayout.PREFERRED_SIZE,
																										70,
																										GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addGroup(
																										gl_panel.createParallelGroup(
																												Alignment.LEADING,
																												false)
																												.addGroup(
																														gl_panel.createSequentialGroup()
																																.addGap(12)
																																.addComponent(
																																		lblCliente)
																																.addPreferredGap(
																																		ComponentPlacement.RELATED)
																																.addComponent(
																																		jtfCliente,
																																		GroupLayout.PREFERRED_SIZE,
																																		1085,
																																		GroupLayout.PREFERRED_SIZE))
																												.addGroup(
																														gl_panel.createSequentialGroup()
																																.addGroup(
																																		gl_panel.createParallelGroup(
																																				Alignment.TRAILING)
																																				.addComponent(
																																						lblCopias)
																																				.addComponent(
																																						lblServico))
																																.addPreferredGap(
																																		ComponentPlacement.RELATED)
																																.addGroup(
																																		gl_panel.createParallelGroup(
																																				Alignment.LEADING)
																																				.addGroup(
																																						gl_panel.createSequentialGroup()
																																								.addComponent(
																																										jtfServico,
																																										GroupLayout.PREFERRED_SIZE,
																																										1008,
																																										GroupLayout.PREFERRED_SIZE)
																																								.addPreferredGap(
																																										ComponentPlacement.RELATED)
																																								.addComponent(
																																										button,
																																										GroupLayout.PREFERRED_SIZE,
																																										49,
																																										GroupLayout.PREFERRED_SIZE))
																																				.addGroup(
																																						gl_panel.createParallelGroup(
																																								Alignment.LEADING,
																																								false)
																																								.addComponent(
																																										jbAdicionarItem,
																																										GroupLayout.DEFAULT_SIZE,
																																										GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addGroup(
																																										gl_panel.createSequentialGroup()
																																												.addComponent(
																																														jtfQuantidadeCopias,
																																														GroupLayout.PREFERRED_SIZE,
																																														GroupLayout.DEFAULT_SIZE,
																																														GroupLayout.PREFERRED_SIZE)
																																												.addPreferredGap(
																																														ComponentPlacement.RELATED)
																																												.addComponent(
																																														lblQuantidade)
																																												.addPreferredGap(
																																														ComponentPlacement.RELATED)
																																												.addComponent(
																																														jtfOriginais,
																																														GroupLayout.PREFERRED_SIZE,
																																														GroupLayout.DEFAULT_SIZE,
																																														GroupLayout.PREFERRED_SIZE)
																																												.addPreferredGap(
																																														ComponentPlacement.UNRELATED)
																																												.addComponent(
																																														label,
																																														GroupLayout.PREFERRED_SIZE,
																																														91,
																																														GroupLayout.PREFERRED_SIZE)
																																												.addPreferredGap(
																																														ComponentPlacement.RELATED)
																																												.addComponent(
																																														jtfValorUnitario,
																																														GroupLayout.PREFERRED_SIZE,
																																														114,
																																														GroupLayout.PREFERRED_SIZE))))))
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										11,
																										Short.MAX_VALUE))
																				.addComponent(
																						spListaServicos,
																						GroupLayout.DEFAULT_SIZE,
																						1149,
																						Short.MAX_VALUE))
																.addGap(9))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																				.addComponent(
																						jbRemoverItem,
																						Alignment.LEADING,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jbSalvar,
																						Alignment.LEADING,
																						GroupLayout.DEFAULT_SIZE,
																						134,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jbCancelar,
																		GroupLayout.PREFERRED_SIZE,
																		137,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap(
																		882,
																		Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCliente))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						lblServico)
																				.addComponent(
																						jtfServico,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.BASELINE)
																				.addComponent(
																						jtfQuantidadeCopias,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						lblQuantidade)
																				.addComponent(
																						jtfOriginais,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						lblCopias)
																				.addComponent(
																						label)
																				.addComponent(
																						jtfValorUnitario,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		jbAdicionarItem))
												.addComponent(
														button,
														GroupLayout.PREFERRED_SIZE,
														20,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(spListaServicos,
										GroupLayout.PREFERRED_SIZE, 121,
										GroupLayout.PREFERRED_SIZE)
								.addGap(16)
								.addComponent(spItensOS,
										GroupLayout.PREFERRED_SIZE, 145,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jbRemoverItem)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(jbSalvar)
												.addComponent(jbCancelar))
								.addGap(19)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(tfValorTotal)
												.addComponent(jlTotalVenda))
								.addContainerGap()));

		jtListaServicos = new JTable();
		jtListaServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					jtfValorUnitario.setText(new ServicoControl()
							.showAllServicos()
							.get(jtListaServicos.getSelectedRow())
							.getValorUnt().toString());
					jtfOriginais.setText("1");
					jtfQuantidadeCopias.setText("1");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});

		spListaServicos.setColumnHeaderView(jtListaServicos);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		spListaServicos.setViewportView(jtListaServicos);
		try {
			jtListaServicos.setModel(new ServicoTableModel(new ServicoControl()
					.showAllServicos()));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		jtListaServicos.getColumnModel().getColumn(0).setResizable(false);

		jtListaServicos.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtListaServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaServicos.getColumnModel().getColumn(1).setPreferredWidth(100);
		getContentPane().setLayout(groupLayout);
	}
}
