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
import br.senai.sc.utils.OrdemServicoTableModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaOrdemServicoUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private static JTable jtListaOrdemServicos;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Servico> listaServicos;
	private ArrayList<OrdemServico> listaOS = new ArrayList<OrdemServico>();

	// private Double somaTotal = 0.00;
	private JTextField jtfFiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
	
				try {
					OrdemServicoUI frame = new OrdemServicoUI();
					jtListaOrdemServicos.setModel(new ConsultaOrdemServicoTableModel(
							new OrdemServicoControl().showOrdemServicos()));
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

		JScrollPane scrollpane = new JScrollPane();

		jtListaOrdemServicos = new JTable();
		jtListaOrdemServicos.setModel(new ConsultaOrdemServicoTableModel(
				new OrdemServicoControl().showOrdemServicos()));
		jtListaOrdemServicos.getColumnModel().getColumn(0).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaOrdemServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaOrdemServicos.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollpane.setViewportView(jtListaOrdemServicos);

		jtfFiltro = new JTextField();
		jtfFiltro.setColumns(10);
		final JComboBox jcbTipoFiltro = new JComboBox();
		JButton btnTipoFiltro = new JButton("Pesquisa");
		btnTipoFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jtListaOrdemServicos.setModel(new OrdemServicoTableModel(
							new OrdemServicoControl().showFilterOS(
									jcbTipoFiltro.getSelectedItem().toString(),
									jtfFiltro.getText())));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jcbTipoFiltro.setModel(new DefaultComboBoxModel(new String[] {"Nome", "Valor Total", "Status"}));

		JLabel lblTipoFiltro = new JLabel("Tipo filtro:");

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														scrollpane,
														GroupLayout.DEFAULT_SIZE,
														1148, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblPesquisa)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		jtfFiltro,
																		GroupLayout.DEFAULT_SIZE,
																		816,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		lblTipoFiltro,
																		GroupLayout.PREFERRED_SIZE,
																		59,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jcbTipoFiltro,
																		GroupLayout.PREFERRED_SIZE,
																		124,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(4)
																.addComponent(
																		btnTipoFiltro)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		btnTipoFiltro)
																.addComponent(
																		jcbTipoFiltro,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		lblTipoFiltro))
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		jtfFiltro,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		lblPesquisa)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollpane,
										GroupLayout.DEFAULT_SIZE, 334,
										Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
