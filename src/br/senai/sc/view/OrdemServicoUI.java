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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.OrdemServicoTableModel;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class OrdemServicoUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jtfCopias;
	private JTextField jtfQuantidade;
	private JTable jtListaItensVenda;

	private JComboBox<Servico> jcbServico;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private ArrayList<OrdemServico> listaOS = new ArrayList<OrdemServico>();
	private Double somaTotal = 0.00;
	private JTextField jtfCliente;
	JLabel jlValorTotal = new JLabel("0,00");

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

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ordem de Servi\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(14)
																		.addComponent(
																				jbSalvar,
																				GroupLayout.PREFERRED_SIZE,
																				134,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jbCancelar,
																				GroupLayout.PREFERRED_SIZE,
																				137,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				1180,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(
						Alignment.TRAILING).addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 410,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.LEADING)
												.addComponent(jbSalvar)
												.addComponent(jbCancelar))
								.addGap(174)));

		JScrollPane jspItensVenda = new JScrollPane();

		jtListaItensVenda = new JTable();
		jtListaItensVenda.setModel(new OrdemServicoTableModel(
				new OrdemServicoControl().showItensServicoOrdemServicos()));
		jtListaItensVenda.getColumnModel().getColumn(0).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaItensVenda.getColumnModel().getColumn(1).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(1).setPreferredWidth(150);
		jspItensVenda.setViewportView(jtListaItensVenda);

		jtfCliente = new JTextField();
		jtfCliente.setEditable(false);
		jtfCliente.setColumns(10);

		// PREENCHE O COMBOBOX CLIENTE
		JLabel lblCliente = new JLabel("Cliente:");

		// PREENCHE O MODEL SERVICO
		JLabel lblServico = new JLabel("Servi\u00E7os:");
		jcbServico = new JComboBox<Servico>();
		jcbServico.setModel(modelServico);

		// ADICIONA ITEM NO TABLEMODEL ORCAMENTO
		JButton jbAdicionarItem = new JButton("Adicionar Serviço");
		jbAdicionarItem
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\add_icon.png"));
		jbAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemServico os = new OrdemServico();
				OrdemServicoControl osd = new OrdemServicoControl();
				Servico service = new Servico();
				// Cliente cliente = new Cliente();

				// cliente = (Cliente) jtfCliente.getSelectedText());
				service = (Servico) jcbServico.getSelectedItem();

				service.setDescricao(service.getDescricao());
				os.setQuantidadeOriginal(Integer.parseInt(jtfQuantidade
						.getText()));
				os.setCopias(Integer.parseInt(jtfCopias.getText()));
				Double vtotal = (Double) (service.getValorUnt() * (os
						.getQuantidadeOriginal() + os.getCopias()));

				os.setValorTotal(vtotal);
				os.setServico(service);
				try {
					osd.insertOrdemServico(os);
					jtListaItensVenda.setModel(new OrdemServicoTableModel(osd
							.showItensServicoOrdemServicos()));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				somaTotal += os.getValorTotal();
				jlValorTotal.setText(somaTotal.toString());
			}
		});

		JLabel lblQuantidade = new JLabel("Quantidade Original:");

		jtfCopias = new JTextField();
		jtfCopias.setColumns(10);

		JLabel lblCopias = new JLabel("Copias:");

		jtfQuantidade = new JTextField();
		jtfQuantidade.setColumns(10);

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

						osc.deleteServicoOrdemServico(os);

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
																		jspItensVenda,
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
																						gl_panel.createSequentialGroup()
																								.addGroup(
																										gl_panel.createParallelGroup(
																												Alignment.LEADING)
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
																																										jtfQuantidade,
																																										GroupLayout.PREFERRED_SIZE,
																																										GroupLayout.DEFAULT_SIZE,
																																										GroupLayout.PREFERRED_SIZE))
																																				.addComponent(
																																						jbAdicionarItem,
																																						GroupLayout.DEFAULT_SIZE,
																																						279,
																																						Short.MAX_VALUE)
																																				.addComponent(
																																						jcbServico,
																																						0,
																																						279,
																																						Short.MAX_VALUE))
																																.addGap(839))
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
																																		366,
																																		GroupLayout.PREFERRED_SIZE)))
																								.addPreferredGap(
																										ComponentPlacement.RELATED))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										jbRemoverItem,
																										GroupLayout.PREFERRED_SIZE,
																										123,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										749,
																										Short.MAX_VALUE)
																								.addComponent(
																										jlTotalVenda)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jlValorTotal,
																										GroupLayout.PREFERRED_SIZE,
																										70,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(167)))
																.addGap(8)))));
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
												Alignment.BASELINE)
												.addComponent(
														jcbServico,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblServico))
								.addGap(9)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfCopias,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblQuantidade)
												.addComponent(
														jtfQuantidade,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblCopias))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jbAdicionarItem)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jspItensVenda,
										GroupLayout.PREFERRED_SIZE, 211,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(jbRemoverItem)
												.addComponent(jlValorTotal)
												.addComponent(jlTotalVenda))
								.addContainerGap(21, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
