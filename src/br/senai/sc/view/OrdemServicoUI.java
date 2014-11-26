package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.OrdemServicoTableModel;
import br.senai.sc.utils.ServicoTableModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

public class OrdemServicoUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jtfCopias;
	private JTextField jtfOriginais;
	private JTable jtListaServicosOS;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private JTextField jtfCliente;
	final JLabel tfValorTotal = new JLabel("00.00");
	private JTextField jtfValorUnitario;
	private JTextField jtfServico;
	private JTable jtListaServicos;
	private static int id_orc;
	private JTextField jtfDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdemServicoUI frame = new OrdemServicoUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void atualizarValor(OrdemServico os) throws ClassNotFoundException,
			SQLException {
		Double valorTotal = 0.0;
		new OrdemServicoControl();
		for (int i = 0; i < OrdemServicoControl.showItensServicoOrdemServicos(
				id_orc).size(); i++) {
			new OrdemServicoControl();
			valorTotal += OrdemServicoControl
					.showItensServicoOrdemServicos(id_orc).get(i)
					.getValorTotal();
		}
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(valorTotal);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		OrdemServicoControl.changeValorTotal(os, valorTotal);
		tfValorTotal.setText(string2);
	}

	public OrdemServicoUI(final OrdemServico os) throws ClassNotFoundException,
			SQLException {
		getContentPane().setBackground(new Color(176, 196, 222));
		setFrameIcon(new ImageIcon(
				OrdemServicoUI.class
						.getResource("/br/senai/sc/icons/efeturar_ordem.png")));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0, 0);
			}
		});

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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1171,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 518,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(45, Short.MAX_VALUE)));

		JScrollPane spItensOS = new JScrollPane();

		jtListaServicosOS = new JTable();
		try {
			new OrdemServicoControl();
			jtListaServicosOS.setModel(new OrdemServicoTableModel(
					OrdemServicoControl.showItensServicoOrdemServicos(id_orc)));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		jtListaServicosOS.getColumnModel().getColumn(0).setResizable(false);
		jtListaServicosOS.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaServicosOS.getColumnModel().getColumn(1).setResizable(false);
		jtListaServicosOS.getColumnModel().getColumn(1).setPreferredWidth(150);
		spItensOS.setViewportView(jtListaServicosOS);

		jtfCliente = new JTextField();
		jtfCliente.setEditable(false);
		jtfCliente.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente:");

		JLabel lblServico = new JLabel("Servi\u00E7os:");

		if (os != null) {
			id_orc = os.getId();
			jtfCliente.setText(os.getCliente().getNome());
			new OrdemServicoControl();
			jtListaServicosOS.setModel(new OrdemServicoTableModel(
					OrdemServicoControl.showItensServicoOrdemServicos(id_orc)));
		}

		// ADICIONA ITEM NO TABLEMODEL ORDEM DE SERVIÇOS
		JButton jbAdicionarItem = new JButton("Adicionar Serviço");
		if (os.getStatus() == "Aprovado") {
			jbAdicionarItem.setEnabled(false);
		} else {
			jbAdicionarItem.setEnabled(true);
		}
		jbAdicionarItem.setIcon(new ImageIcon(OrdemServicoUI.class
				.getResource("/br/senai/sc/icons/add_icon.png")));
		jbAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemServico os = new OrdemServico();

				try {
					Double valorTotal = (Integer.parseInt(jtfOriginais
							.getText()) * Integer.parseInt(jtfCopias.getText()))
							* Double.parseDouble(jtfValorUnitario.getText());
					new OrdemServicoControl();
					os.setId(id_orc);
					os.getServico().setId(
							new ServicoControl().showAllServicos()
									.get(jtListaServicos.getSelectedRow())
									.getId());
					os.getServico().setDescricao(
							new ServicoControl().showAllServicos()
									.get(jtListaServicos.getSelectedRow())
									.getDescricao());
					os.getServico().setValorUnt(
							Double.parseDouble(jtfValorUnitario.getText()));
					os.getServico().setOriginais(
							Integer.parseInt(jtfOriginais.getText()));
					os.getServico().setCopias(
							Integer.parseInt(jtfCopias.getText()));
					os.setValorTotal(valorTotal);
					os.setDescricao(jtfDescricao.getText());
					OrdemServicoControl.insertOrdemServico(os);
					OrdemServicoControl.changeDescricao(os);

					new OrdemServicoControl();
					jtListaServicosOS.setModel(new OrdemServicoTableModel(
							OrdemServicoControl
									.showItensServicoOrdemServicos(id_orc)));
					atualizarValor(os);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Numero inválidos");
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null,
							"Selecione um Serviço para adicionar");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao Adicionar");
				}
			}
		});

		JLabel lblQuantidade = new JLabel("Quantidade Original:");

		jtfCopias = new JTextField();
		jtfCopias.setColumns(10);

		JLabel lblCopias = new JLabel("Copias:");

		jtfOriginais = new JTextField();
		jtfOriginais.setColumns(10);

		JLabel jlTotalVenda = new JLabel("Total:  R$:");

		JButton jbRemoverServico = new JButton("Remover Servi\u00E7o");
		if (os.getStatus() == "Aprovado") {
			jbRemoverServico.setEnabled(false);
		} else {
			jbRemoverServico.setEnabled(true);
		}
		jbRemoverServico.setIcon(new ImageIcon(OrdemServicoUI.class
				.getResource("/br/senai/sc/icons/delete_icon.png")));
		jbRemoverServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					OrdemServicoControl.deleteServicoOrdemServico(
							id_orc,
							OrdemServicoControl
									.showItensServicoOrdemServicos(id_orc)
									.get(jtListaServicosOS.getSelectedRow())
									.getServico().getId());

					new OrdemServicoControl();
					jtListaServicosOS.setModel(new OrdemServicoTableModel(
							OrdemServicoControl
									.showItensServicoOrdemServicos(id_orc)));
					os.setDescricao(jtfDescricao.getText());
					OrdemServicoControl.changeDescricao(os);
					atualizarValor(os);
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null,
							"Selecione um serviço para deletar");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falha ao deletar");
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
		button.setIcon(new ImageIcon(OrdemServicoUI.class
				.getResource("/br/senai/sc/icons/search.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jtListaServicos.setModel(new ServicoTableModel(
							new ServicoControl().showFilterServicos(
									jtfServico.getText(), 0)));
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});

		JButton jbImprimir = new JButton("Imprimir");
		jbImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					os.setDescricao(jtfDescricao.getText());
					OrdemServicoControl.changeDescricao(os);
					OrdemServicoControl.changeStatus(os, 1);
					MostrarOSUI mosUI = new MostrarOSUI(os);
					mosUI.setFocusable(true);
					getParent().add(mosUI, 0);
					mosUI.requestFocus();
					mosUI.setVisible(true);
					hide();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jbImprimir.setIcon(new ImageIcon(OrdemServicoUI.class
				.getResource("/br/senai/sc/icons/printer.png")));

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.setIcon(new ImageIcon(OrdemServicoUI.class
				.getResource("/br/senai/sc/icons/exit_icon.png")));
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					os.setDescricao(jtfDescricao.getText());
					OrdemServicoControl.changeDescricao(os);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		atualizarValor(os);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");

		jtfDescricao = new JTextField();
		jtfDescricao.setColumns(10);

		if (os != null) {
			jtfDescricao.setText(os.getDescricao());
		}
		if (os.getStatus() == "Aprovado") {
			jtfDescricao.setEnabled(false);
		} else {
			jtfDescricao.setEnabled(true);
		}

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
																		jbImprimir,
																		GroupLayout.PREFERRED_SIZE,
																		134,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jbRemoverServico)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jbCancelar,
																		GroupLayout.PREFERRED_SIZE,
																		137,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		460,
																		Short.MAX_VALUE)
																.addComponent(
																		jlTotalVenda,
																		GroupLayout.PREFERRED_SIZE,
																		66,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		tfValorTotal,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(119))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblDescricao)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jtfDescricao))
																				.addGroup(
																						Alignment.LEADING,
																						gl_panel.createSequentialGroup()
																								.addGap(12)
																								.addComponent(
																										lblCliente)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jtfCliente))
																				.addGroup(
																						Alignment.LEADING,
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
																																						jtfCopias,
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
																																						GroupLayout.PREFERRED_SIZE)))))
																				.addComponent(
																						spItensOS,
																						Alignment.LEADING)
																				.addComponent(
																						spListaServicos,
																						Alignment.LEADING))
																.addContainerGap(
																		38,
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
																						jtfCopias,
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
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblDescricao)
												.addComponent(
														jtfDescricao,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.BASELINE)
																				.addComponent(
																						jlTotalVenda)
																				.addComponent(
																						tfValorTotal)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.BASELINE)
																				.addComponent(
																						jbImprimir)
																				.addComponent(
																						jbRemoverServico)
																				.addComponent(
																						jbCancelar))))
								.addGap(12)));

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
					jtfCopias.setText("1");
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
