package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.ConsultaOrdemServicoTableModel;

public class ConsultaOrdemServicoUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable jtListaItensVenda;

	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private ArrayList<OrdemServico> listaOS = new ArrayList<OrdemServico>();

	// private Double somaTotal = 0.00;
	private JTextField tfPesquisa;

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

	public ConsultaOrdemServicoUI() throws ClassNotFoundException, SQLException {
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
		panel.setBorder(new TitledBorder(null, "Ordem de Servi\u00E7o(s)",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(143, Short.MAX_VALUE)));

		JScrollPane jspListaOrdemServicos = new JScrollPane();

		jtListaItensVenda = new JTable();
		jtListaItensVenda.setModel(new ConsultaOrdemServicoTableModel(
				new OrdemServicoControl().showOrdemServicos()));
		jtListaItensVenda.getColumnModel().getColumn(0).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaItensVenda.getColumnModel().getColumn(1).setResizable(false);
		jtListaItensVenda.getColumnModel().getColumn(1).setPreferredWidth(150);
		jspListaOrdemServicos.setViewportView(jtListaItensVenda);

		tfPesquisa = new JTextField();
		tfPesquisa.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														jspListaOrdemServicos,
														Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														1148, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		tfPesquisa,
																		GroupLayout.DEFAULT_SIZE,
																		1049,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		btnNewButton)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														tfPesquisa,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton))
								.addGap(18)
								.addComponent(jspListaOrdemServicos,
										GroupLayout.DEFAULT_SIZE, 334,
										Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
