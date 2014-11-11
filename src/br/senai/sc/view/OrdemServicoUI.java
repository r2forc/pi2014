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

public class OrdemServicoUI extends JInternalFrame {
	private JTextField jtfDataVenda;
	private JTextField jtfQuantidade;
	private JTable jtListaItensVenda;

	private JComboBox<Servico> jcbServico;
	private JComboBox<Cliente> jcbCliente;

	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private ArrayList<OrdemServico> listaOS = new ArrayList<OrdemServico>();
	private Double somaTotal = 0.00;
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
		setBackground(SystemColor.inactiveCaption);
		setRootPaneCheckingEnabled(false);
		setEnabled(false);
		setBorder(null);
		setSize(1500, 1000);
		setTitle("ORC R2F - Efetuar Ordem de Servi\u00E7o");
		setBounds(0, 0, 1200, 600);

		JLabel jlData = new JLabel("Data:");

		jtfDataVenda = new JTextField();
		jtfDataVenda.setColumns(10);

		jtfQuantidade = new JTextField();
		jtfQuantidade.setColumns(10);

		// PREENCHE O COMBOBOX CLIENTE
		JLabel jlCliente = new JLabel("Cliente:");
		listaClientes = new ClienteControl().showAllClientes();
		jcbCliente = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> modelCliente = new DefaultComboBoxModel<Cliente>();
		for (Cliente cliente : listaClientes) {
			modelCliente.addElement(cliente);
		}
		jcbCliente.setModel(modelCliente);

		// PREENCHE O COMBOBOX SERVICO
		JLabel ljServico = new JLabel("Servi\u00E7os:");
		listaServicos = new ServicoControl().showAllServicos();
		jcbServico = new JComboBox<Servico>();
		DefaultComboBoxModel<Servico> modelServico = new DefaultComboBoxModel<Servico>();
		for (Servico servico : listaServicos) {
			modelServico.addElement(servico);
		}
		jcbServico.setModel(modelServico);

		// ADICIONA ITEM NO TABLEMODEL ORCAMENTO
		JButton jbAdicionarItem = new JButton("Adicionar Serviço");
		jbAdicionarItem
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\add_icon.png"));
		jbAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemServico os = new OrdemServico();
				os.getOrcamento().setQuantidadeOriginal(
						Integer.parseInt(jtfQuantidade.getText()));
				os.getOrcamento().getServico()
						.setDescricao((String) jcbServico.getSelectedItem());
				os.setValorTotal(os.getOrcamento().getServico().getValorUnt()
						* os.getOrcamento().getQuantidadeOriginal());

				listaOS.add(os);

				jtListaItensVenda.setModel(new OrdemServicoTableModel(listaOS));

				somaTotal += os.getValorTotal();
				jlValorTotal.setText(somaTotal.toString());
			}
		});

		JScrollPane jspItensVenda = new JScrollPane();

		JButton jbRemoverItem = new JButton("Remover Item");
		jbRemoverItem
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\exit_icon.png"));
		jbRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel jlTotalVenda = new JLabel("Total:");

		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.setIcon(new ImageIcon(
				"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\save_icon.png"));

		JButton jbLimpar = new JButton("Limpar");
		jbLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		jbLimpar.setIcon(new ImageIcon(
				"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\1415673761_edit-clear-24.png"));

		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar
				.setIcon(new ImageIcon(
						"C:\\Users\\Felipe\\Google Drive\\ADS\\2-SEMESTRE\\POO\\ProjetoIntegrador2014\\src\\br\\senai\\sc\\icons\\1415673847_exit.png"));
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblQuantidade = new JLabel("Quantidade:");
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
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								jlCliente)
																						.addComponent(
																								ljServico)
																						.addComponent(
																								jlData))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												jtfDataVenda,
																												GroupLayout.PREFERRED_SIZE,
																												74,
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
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																jcbCliente,
																																Alignment.LEADING,
																																0,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jcbServico,
																																Alignment.LEADING,
																																0,
																																241,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												jbAdicionarItem)))
																		.addGap(186))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								jbRemoverItem,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jbSalvar,
																								GroupLayout.PREFERRED_SIZE,
																								147,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								Alignment.TRAILING,
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												jbLimpar,
																												GroupLayout.PREFERRED_SIZE,
																												135,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												jbCancelar,
																												GroupLayout.PREFERRED_SIZE,
																												129,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(251))
																						.addGroup(
																								Alignment.TRAILING,
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												jlTotalVenda)
																										.addGap(18)
																										.addComponent(
																												jlValorTotal,
																												GroupLayout.PREFERRED_SIZE,
																												31,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(152))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				jspItensVenda,
																				GroupLayout.PREFERRED_SIZE,
																				542,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																jcbCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jlCliente))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																jcbServico,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(ljServico)
														.addComponent(
																jbAdicionarItem))
										.addGap(3)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jlData)
														.addComponent(
																jtfDataVenda,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jtfQuantidade,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblQuantidade))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(jspItensVenda,
												GroupLayout.PREFERRED_SIZE,
												242, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																jbRemoverItem,
																GroupLayout.PREFERRED_SIZE,
																33,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jlValorTotal)
														.addComponent(
																jlTotalVenda))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jbSalvar)
														.addComponent(jbLimpar)
														.addComponent(
																jbCancelar))
										.addContainerGap(15, Short.MAX_VALUE)));

		jtListaItensVenda = new JTable();
		jtListaItensVenda.setModel(new OrdemServicoTableModel(
				new OrdemServicoControl().showAllOrdemServico()));
		jtListaItensVenda.getColumnModel().getColumn(0).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaItensVenda.getColumnModel().getColumn(1).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtListaItensVenda.getColumnModel().getColumn(2).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(2).setPreferredWidth(62);
		jtListaItensVenda.getColumnModel().getColumn(3).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(3).setPreferredWidth(97);
		jspItensVenda.setViewportView(jtListaItensVenda);
		getContentPane().setLayout(groupLayout);
	}
}
