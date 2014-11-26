package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.senai.sc.control.OrcamentoFlashControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.OrcamentoFlashTableModel;
import br.senai.sc.utils.ServicoTableModel;

public class OrcamentoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCliente;
	private JTextField jtfServico;
	private JTable jtListaServicos;
	private JTextField jtfOriginais;
	private JTextField jtfQuantidadeCopias;
	private JTextField jtfValorUnitario;
	private JTable jtOrcamentoFlash;
	private JLabel tfValorTotal;
	private JTextField jtfDescricao;
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
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0,0);
			}
		});
		setFrameIcon(new ImageIcon(OrcamentoUI.class.getResource("/br/senai/sc/icons/icon_table.png")));
		setBorder(null);
		getContentPane().setBackground(new Color(176, 196, 222));
		setTitle("Gerar Or\u00E7amento");
		setBounds(0, 0, 1194, 600);
		final OrcamentoFlashControl ofc = new OrcamentoFlashControl();
		
		JLabel jlCliente = new JLabel("Cliente:");
		
		jtfCliente = new JTextField();
		
		jtfCliente.setEditable(false);
		jtfCliente.setColumns(10);
		
		final JLabel tfValorTotal = new JLabel("00.00");
		
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
		jbProcurarServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jtListaServicos.setModel(new ServicoTableModel( new ServicoControl().showFilterServicos(jtfServico.getText(),0) ) );
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}	
			}
		});
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
					new OrcamentoFlashControl();
					serv.setId(new ServicoControl().showAllServicos().get(jtListaServicos.getSelectedRow()).getId());
					serv.setDescricao(new ServicoControl().showAllServicos().get(jtListaServicos.getSelectedRow()).getDescricao());
					serv.setValorUnt(Double.parseDouble(jtfValorUnitario.getText()));
					serv.setOriginais(Integer.parseInt(jtfOriginais.getText()));
					serv.setCopias(Integer.parseInt(jtfQuantidadeCopias.getText()));
					OrcamentoFlashControl.inserirServico(serv);
					jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( new OrcamentoFlashControl().showAllOrcamento() ) );
					Double valorTotal = 0.0;
					
					OrcamentoFlashDAO of = new OrcamentoFlashDAO();
					
					for(int i = 0; i < of.getInstace().mostrarServicos().size(); i++){
						valorTotal += ((of.getInstace().mostrarServico(i).getValorUnt() * (of.getInstace().mostrarServico(i).getCopias()) * of.getInstace().mostrarServico(i).getOriginais()));
					}
					
					if(valorTotal.toString().length() > 7){
						String[] valorDivido = valorTotal.toString().split("\\.");
						valorDivido[1] = valorDivido[1].substring(0, 2);
						tfValorTotal.setText(valorDivido[0] + "." + valorDivido[1] );
					}else{
					tfValorTotal.setText( valorTotal.toString() );
					}
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Numero inválidos");
				}catch(ArrayIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null,"Selecione um Serviço para adicionar");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Falha ao Adicionar");
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
		
		JLabel jlValorTotal = new JLabel("Valor Total: R$");
		
		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrcamentoFlashControl ofc = new OrcamentoFlashControl();
				if(ofc.salvarNoBanco(Double.parseDouble(tfValorTotal.getText()),jtfDescricao.getText())){
					JOptionPane.showMessageDialog(null, "Orçamento Cadastrado com Sucesso");
					ofc.destruirFlash();
					jtfDescricao.setText("");
					jtfCliente.setText("");
					jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( 
							new OrcamentoFlashControl().showAllOrcamento() ) );
				}
				
			}
		});
		
		JButton btCancelar = new JButton("Sair");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ofc.showAllServicos().size() != 0)
					ofc.destruirFlash();
				
				jtfDescricao.setText("");
				jtfCliente.setText("");
				dispose();
			}
		});
		
		JLabel jlServicos = new JLabel("Servi\u00E7os:");
		
		JButton btnRemover = new JButton("Remover Servi\u00E7o");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(jtOrcamentoFlash.getSelectedRow() == -1)
						throw new Exception("Selecione um Serviço para excluir");
					
				new OrcamentoFlashControl().removerServico(jtOrcamentoFlash.getSelectedRow());
				jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( 
				new OrcamentoFlashControl().showAllOrcamento() ) );
				
				Double valorTotal = 0.0;
				OrcamentoFlashDAO of = new OrcamentoFlashDAO();
				for(int i = 0; i < of.getInstace().mostrarServicos().size(); i++){
					valorTotal += (of.getInstace().mostrarServico(i).getValorUnt() * (of.getInstace().mostrarServico(i).getCopias() * of.getInstace().mostrarServico(i).getOriginais()));
				}
				if(valorTotal.toString().length() > 7){
					String[] valorDivido = valorTotal.toString().split("\\.");
					valorDivido[1] = valorDivido[1].substring(0, 2);
					tfValorTotal.setText(valorDivido[0] + "." + valorDivido[1] );
				}else{
				tfValorTotal.setText( valorTotal.toString() );
				}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		jtfDescricao = new JTextField();
		jtfDescricao.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spOrcamentoFlahs, GroupLayout.PREFERRED_SIZE, 1172, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(31)
									.addComponent(jlValorTotal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfValorTotal)
									.addPreferredGap(ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
									.addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addGap(73)
									.addComponent(jbSalvar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addGap(73)
									.addComponent(btCancelar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
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
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
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
								.addComponent(spListaClientes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1161, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblDescrio)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jtfDescricao, GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)))
							.addGap(36))))
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
					.addComponent(spOrcamentoFlahs, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlValorTotal)
						.addComponent(btnRemover)
						.addComponent(tfValorTotal)
						.addComponent(btCancelar)
						.addComponent(jbSalvar))
					.addGap(71))
		);
		
		jtOrcamentoFlash = new JTable();
		
		jtOrcamentoFlash.setModel(new OrcamentoFlashTableModel( new OrcamentoFlashControl().showAllOrcamento() ) );
					
		jtOrcamentoFlash.getColumnModel().getColumn(0).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(1).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(2).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(3).setResizable(false);
		jtOrcamentoFlash.getColumnModel().getColumn(4).setResizable(false);
		spOrcamentoFlahs.setViewportView(jtOrcamentoFlash);
		
		jtListaServicos = new JTable();
		jtListaServicos.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				try {
					jtfValorUnitario.setText(new ServicoControl().showAllServicos().get(jtListaServicos.getSelectedRow()).getValorUnt().toString());
					jtfOriginais.setText("1");
					jtfQuantidadeCopias.setText("1");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}

		});
		spListaClientes.setViewportView(jtListaServicos);
		try {
			jtListaServicos.setModel(new ServicoTableModel( new ServicoControl().showAllServicos() ) );	
		}  catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		jtListaServicos.getColumnModel().getColumn(0).setResizable(false);
		
		jtListaServicos.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtListaServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaServicos.getColumnModel().getColumn(1).setPreferredWidth(100);
		getContentPane().setLayout(groupLayout);

	}
}
