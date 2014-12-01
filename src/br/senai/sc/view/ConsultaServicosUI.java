package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.senai.sc.control.ServicoControl;
import br.senai.sc.dao.ServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.ServicoTableModel;

public class ConsultaServicosUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JTable table;
	private JTable jtConsultaServico;
	private JCheckBox jckbExcluidos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaServicosUI frame = new ConsultaServicosUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ConsultaServicosUI() throws ClassNotFoundException, SQLException {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0,0);
			}
		});
		setFrameIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/imprimir.png")));
		setTitle("Consultar Servi\u00E7os");
		setBorder(null);
		getContentPane().setBackground(new Color(176, 196, 222));
		setBounds(0, 0, 1200, 600);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(176, 196, 222));
		
		final JCheckBox jckbExcluidos = new JCheckBox("Exclu\u00EDdos");
		jckbExcluidos.setBackground(new Color(176, 196, 222));
		
		final JButton btnRestaurar = new JButton("Restaurar");
		btnRestaurar.setIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/entrada_produto.png")));
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(jtConsultaServico.getSelectedRow() == -1)
						throw new Exception("Selecione um Serviço");
					
					int opcao = JOptionPane.showConfirmDialog(null, 
							"Deseja restaurar o serviço selecionado?", 
							"restaurar Serviço", 
							JOptionPane.YES_NO_OPTION);
					if ( opcao == 0){
						Servico ser = null;
						ser = ServicoDAO.getInstace().getListaServicos().get(jtConsultaServico.getSelectedRow());
						ServicoControl sc = new ServicoControl();
						sc.restaurarServico(ser.getId());
						int exclusao = jckbExcluidos.isSelected() ? 1 : 0;
						jtConsultaServico.setModel(new ServicoTableModel( 
								new ServicoControl().showFilterServicos(jtfFiltro.getText(), exclusao ) ) );
					} 
					}
					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		btnRestaurar.setVisible(false);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/add_icon.png")));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarEditarServico ces = new CadastrarEditarServico(null);
				try {
					ces.setFocusable(true);
					ces.moveToFront();
					getContentPane().add(ces, 0);
					ces.requestFocus();
					ces.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ces.addInternalFrameListener(new InternalFrameListener() {  
				    public void internalFrameClosed(InternalFrameEvent e) { 
				    	try { 
				    		jtConsultaServico.setModel(new ServicoTableModel( new ServicoControl().showAllServicos() ) );
				    	}
				    	catch (ClassNotFoundException | SQLException e1) {	
				    		e1.printStackTrace(); 
				    	}
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
		
		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/refresh-icon.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servico serv = null;
				try {
					if(jtConsultaServico.getSelectedRow() == -1)
						throw new Exception("Selecione um Serviço");
					
					serv = ServicoDAO.getInstace().getListaServicos().get(jtConsultaServico.getSelectedRow());
					CadastrarEditarServico ces = new CadastrarEditarServico(serv);
					PrincipalUI.obterInstancia().getContentPane().add(ces);
					ces.setFocusable(true);
					ces.moveToFront();
					getContentPane().add(ces, 0);
					ces.requestFocus();
					ces.setVisible(true);
					ces.addInternalFrameListener(new InternalFrameListener() {  
					    public void internalFrameClosed(InternalFrameEvent e) { 
					    	try {
					    		int exclusao = jckbExcluidos.isSelected() ? 1 : 0;
								jtConsultaServico.setModel(new ServicoTableModel( 
										new ServicoControl().showFilterServicos(jtfFiltro.getText(), exclusao ) ) );
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }
					    public void internalFrameActivated(InternalFrameEvent arg0) {}
						public void internalFrameClosing(InternalFrameEvent arg0) {}
						public void internalFrameDeactivated(InternalFrameEvent arg0) {}
						public void internalFrameDeiconified(InternalFrameEvent arg0) {}
						public void internalFrameIconified(InternalFrameEvent arg0) {}
						public void internalFrameOpened(InternalFrameEvent arg0) {}  
					});
	
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		final JButton btnExcluir = new JButton("Exluir");
		btnExcluir.setIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/delete_icon.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				if(jtConsultaServico.getSelectedRow() == -1)
					throw new Exception("Selecione um Serviço");
				
				int opcao = JOptionPane.showConfirmDialog(null, 
						"Deseja excluir o serviço selecionado?", 
						"Excluir Serviço", 
						JOptionPane.YES_NO_OPTION);
				if ( opcao == 0){
					Servico ser = null;
					ser = ServicoDAO.getInstace().getListaServicos().get(jtConsultaServico.getSelectedRow());
					ServicoControl sc = new ServicoControl();
					sc.deleteServico(ser.getId());
					int exclusao = jckbExcluidos.isSelected() ? 1 : 0;
					jtConsultaServico.setModel(new ServicoTableModel( 
							new ServicoControl().showFilterServicos(jtfFiltro.getText(), exclusao ) ) );
				} 
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.setIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/exit_icon.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1175, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1171, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(btnRestaurar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnCancelar)
						.addComponent(btnRestaurar))
					.addGap(69))
		);
		
		jtConsultaServico = new JTable();
		scrollPane.setViewportView(jtConsultaServico);
		
		jtConsultaServico.setModel(new ServicoTableModel( new ServicoControl().showAllServicos() ) );
					
		jtConsultaServico.getColumnModel().getColumn(0).setResizable(false);
		jtConsultaServico.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtConsultaServico.getColumnModel().getColumn(1).setResizable(false);
		jtConsultaServico.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtConsultaServico.getColumnModel().getColumn(1).setResizable(false);
		getContentPane().setLayout(groupLayout);		
		
		
		final JLabel lblFiltro = new JLabel("Filtro");
		
		jtfFiltro = new JTextField();
		jtfFiltro.setColumns(10);

		JButton btnNewButton_1 = new JButton("Procurar");
		btnNewButton_1.setIcon(new ImageIcon(ConsultaServicosUI.class.getResource("/br/senai/sc/icons/search.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cli = new Cliente();
				try {
					if(jckbExcluidos.isSelected()){
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnRestaurar.setVisible(true);
					}else{
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
						btnRestaurar.setVisible(false);
					}
					
					int exclusao = jckbExcluidos.isSelected() ? 1 : 0;
					jtConsultaServico.setModel(new ServicoTableModel( 
							new ServicoControl().showFilterServicos(jtfFiltro.getText(), exclusao ) ) );
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblFiltro)
					.addGap(10)
					.addComponent(jtfFiltro, GroupLayout.PREFERRED_SIZE, 767, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addComponent(jckbExcluidos)
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltro)
						.addComponent(jtfFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1)
						.addComponent(jckbExcluidos))
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
