package br.senai.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

import java.awt.SystemColor;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JLabel;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.OrcamentoFlashControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.OrcamentoFlashTableModel;
import br.senai.sc.utils.ServicoTableModel;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrcamentoUI extends JInternalFrame {
	private JTextField jtfCliente;
	private JTextField jtfServico;
	private JTable jtListaServicos;
	private JTextField jtfOriginais;
	private JTextField jtfQuantidadeCopias;
	private JTextField jtfValorUnitario;
	private JTable jtOrcamentoFlash;
	private JTextField tfValorTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrcamentoUI frame = new OrcamentoUI();
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
	public OrcamentoUI() {
		
		setClosable(true);
		setBorder(null);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setTitle("Gerar Or\u00E7amento");
		setBounds(0, 0, 1194, 600);
		final OrcamentoFlashControl ofc = new OrcamentoFlashControl();
		
		JLabel jlCliente = new JLabel("Cliente:");
		
		jtfCliente = new JTextField();
		
		jtfCliente.setEditable(false);
		jtfCliente.setColumns(10);
		
		
		
		JButton btnProcurarCliente = new JButton("");
		btnProcurarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClientesOrcUI cco = new ConsultaClientesOrcUI();
				try {
					
					cco.setFocusable(true);
					cco.moveToFront();
					
					cco.requestFocus();
					getContentPane().add(cco, 0);
					cco.setVisible(true);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				cco.addInternalFrameListener(new InternalFrameListener()  
				{  
				    public void internalFrameClosed(InternalFrameEvent e)  
				    {  
				    	jtfCliente.setText(ofc.mostrarCliente());
				    }
				    public void internalFrameActivated(InternalFrameEvent arg0) {}
					public void internalFrameClosing(InternalFrameEvent arg0) {}
					public void internalFrameDeactivated(InternalFrameEvent arg0) {}
					public void internalFrameDeiconified(InternalFrameEvent arg0) {}
					public void internalFrameIconified(InternalFrameEvent arg0) {}
					public void internalFrameOpened(InternalFrameEvent arg0) {}  
				});  
			}
		});
		btnProcurarCliente.setIcon(new ImageIcon(OrcamentoUI.class.getResource("/br/senai/sc/icons/search.png")));
		
		jtfServico = new JTextField();
		jtfServico.setText("");
		jtfServico.setColumns(10);
		
		JButton jbProcurarServicos = new JButton("");
		jbProcurarServicos.setIcon(new ImageIcon(OrcamentoUI.class.getResource("/br/senai/sc/icons/search.png")));
		
		JScrollPane spListaClientes = new JScrollPane();
		
		JLabel jlQuantidadeDeOriginais = new JLabel("Quantidade de Originais:");
		
		jtfOriginais = new JTextField();
		jtfOriginais.setText("1");
		jtfOriginais.setColumns(10);
		
		JLabel jlCopias = new JLabel("Quantidade de C\u00F3pias:");
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servico serv = new Servico();
				try {
					serv.setId(new ServicoControl().showAllServicos().get(jtListaServicos.getSelectedRow()).getId());
					serv.setDescricao(new ServicoControl().showAllServicos().get(jtListaServicos.getSelectedRow()).getDescricao());
					serv.setValorUnt(Double.parseDouble(jtfValorUnitario.getText()));
					serv.setOriginais(Integer.parseInt(jtfOriginais.getText()));
					serv.setCopias(Integer.parseInt(jtfQuantidadeCopias.getText()));
					new OrcamentoFlashControl().inserirServico(serv);
					jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( 
							new OrcamentoFlashControl().showAllOrcamento() ) );
					Double valorTotal = 0.0;
					OrcamentoFlash of = new OrcamentoFlashControl().showAllOrcamento();
					
					for(int i = 0; i < of.getServicos().size(); i++){
						valorTotal += (of.getServico(i).getValorUnt() * (of.getServico(i).getCopias() * of.getServico(i).getOriginais()));
					}
					tfValorTotal.setText(valorTotal.toString());
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		jtfQuantidadeCopias = new JTextField();
		jtfQuantidadeCopias.setText("1");
		jtfQuantidadeCopias.setColumns(10);
		
		JLabel jlValorUnitaro = new JLabel("Valor Unit\u00E1rio:");
		
		jtfValorUnitario = new JTextField();
		jtfValorUnitario.setColumns(10);
		
		JScrollPane spOrcamentoFlahs = new JScrollPane();
		
		JLabel jlValorTotal = new JLabel("Valor Total:");
		
		tfValorTotal = new JTextField();
		tfValorTotal.setColumns(10);
		
		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrcamentoFlashControl ofc = new OrcamentoFlashControl();
				ofc.salvarNoBanco(Double.parseDouble(tfValorTotal.getText()));
				dispose();
			}
		});
		
		JButton btCancelar = new JButton("Cancelar");
		
		JLabel jlServicos = new JLabel("Servi\u00E7os:");
		
		JButton btnRemover = new JButton("Remover Servi\u00E7o");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new OrcamentoFlashControl().removerServico(jtOrcamentoFlash.getSelectedRow());
				jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( 
				new OrcamentoFlashControl().showAllOrcamento() ) );
				
				Double valorTotal = 0.0;
				OrcamentoFlash of = new OrcamentoFlashControl().showAllOrcamento();
				for(int i = 0; i < of.getServicos().size(); i++){
					valorTotal += (of.getServico(i).getValorUnt() * (of.getServico(i).getCopias() * of.getServico(i).getOriginais()));
				}
				tfValorTotal.setText(valorTotal.toString());
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(jlValorTotal)
							.addGap(18)
							.addComponent(tfValorTotal, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
							.addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(jbSalvar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(61)
							.addComponent(btCancelar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(48))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(spOrcamentoFlahs, GroupLayout.DEFAULT_SIZE, 1161, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jlQuantidadeDeOriginais)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfOriginais, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addGap(70)
									.addComponent(jlCopias)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfQuantidadeCopias, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
									.addComponent(jlValorUnitaro)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfValorUnitario, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addGap(43)
									.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(14)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(jlServicos)
										.addComponent(jlCliente))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jtfServico)
										.addComponent(jtfCliente, GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE))
									.addGap(28)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jbProcurarServicos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnProcurarCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(spListaClientes, GroupLayout.DEFAULT_SIZE, 1161, Short.MAX_VALUE))
							.addGap(26)))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(jlCliente))
						.addComponent(btnProcurarCliente, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jbProcurarServicos, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jtfServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(jlServicos)))
					.addGap(25)
					.addComponent(spListaClientes, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlQuantidadeDeOriginais)
						.addComponent(jtfOriginais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlCopias)
						.addComponent(jtfQuantidadeCopias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar)
						.addComponent(jlValorUnitaro)
						.addComponent(jtfValorUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(spOrcamentoFlahs, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlValorTotal)
						.addComponent(tfValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbSalvar)
						.addComponent(btCancelar)
						.addComponent(btnRemover))
					.addGap(71))
		);
		
		jtOrcamentoFlash = new JTable();
		
			jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( 
					new OrcamentoFlashControl().showAllOrcamento() ) );
		
		jtOrcamentoFlash.getColumnModel().getColumn(0).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(1).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(2).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(3).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(4).setResizable(false);
		spOrcamentoFlahs.setViewportView(jtOrcamentoFlash);
		
		jtListaServicos = new JTable();
		jtListaServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					jtfValorUnitario.setText(new ServicoControl().showAllServicos().get(jtListaServicos.getSelectedRow()).getValorUnt().toString());
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		spListaClientes.setViewportView(jtListaServicos);
		try {
			jtListaServicos.setModel(new ServicoTableModel( 
					new ServicoControl().showAllServicos() ) );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jtListaServicos.getColumnModel().getColumn(0).setResizable(false);
		
		jtListaServicos.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtListaServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaServicos.getColumnModel().getColumn(1).setPreferredWidth(100);
		getContentPane().setLayout(groupLayout);

	}
}
