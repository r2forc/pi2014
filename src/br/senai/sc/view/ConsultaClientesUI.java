package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import br.senai.sc.control.ClienteControl;
import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.ClienteTableModel;

public class ConsultaClientesUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JTable jtConsultaCliente;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClientesUI frame = new ConsultaClientesUI();
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
	public ConsultaClientesUI() {
		addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0,0);
			}
		});
		setFrameIcon(new ImageIcon(ConsultaClientesUI.class.getResource("/br/senai/sc/icons/fornecedores.png")));
		setTitle("Consultar Clientes");
		setBorder(null);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1200, 600);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarEditarCliente cec = null;
				try {
					cec = new CadastrarEditarCliente(null);
					cec.setFocusable(true);
					cec.moveToFront();
					getContentPane().add(cec, 0);
					cec.requestFocus();
					cec.setVisible(true);
					cec.addInternalFrameListener(new InternalFrameListener() {  
					    public void internalFrameClosed(InternalFrameEvent e) { 
					    	try {
								jtConsultaCliente.setModel(new ClienteTableModel( new ClienteControl().showAllClientes() ) );
							} catch (ClassNotFoundException | SQLException e1) {
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
				
				
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
			}	
		});
		
		final JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cli = null;
				try {
					if (jtConsultaCliente.getSelectedRow() == -1)
						throw new ArrayIndexOutOfBoundsException("Selecione um Cliente");
					
					cli =  ClienteDAO.getInstace().getListaClientes().get(jtConsultaCliente.getSelectedRow());
					CadastrarEditarCliente cec = null;		
					cec = new CadastrarEditarCliente(cli);
					PrincipalUI.obterInstancia().getContentPane().add(cec);
					cec.setFocusable(true);
					cec.moveToFront();
					getContentPane().add(cec, 0);
					cec.requestFocus();
					cec.setVisible(true);
					cec.addInternalFrameListener(new InternalFrameListener() {  
					    public void internalFrameClosed(InternalFrameEvent e) { 
					    	jtConsultaCliente.setModel(new ClienteTableModel(ClienteDAO.getInstace().getListaClientes() ) );
					    }
					    public void internalFrameActivated(InternalFrameEvent arg0) {}
						public void internalFrameClosing(InternalFrameEvent arg0) {}
						public void internalFrameDeactivated(InternalFrameEvent arg0) {}
						public void internalFrameDeiconified(InternalFrameEvent arg0) {}
						public void internalFrameIconified(InternalFrameEvent arg0) {}
						public void internalFrameOpened(InternalFrameEvent arg0) {}  
					});
				} catch (ParseException | ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		final JButton btnExcluir = new JButton("Exluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (jtConsultaCliente.getSelectedRow() == -1)
						throw new ArrayIndexOutOfBoundsException("Selecione um Cliente");
					int opcao = JOptionPane.showConfirmDialog(null, 
						"Deseja excluir o cliente selecionado?", 
						"Excluir Cliente", 
						JOptionPane.YES_NO_OPTION);
					if ( opcao == 0){
						Cliente cli = null;
						cli =  ClienteDAO.getInstace().getListaClientes().get(jtConsultaCliente.getSelectedRow());	
						ClienteControl cc = new ClienteControl();
						cc.deleteCliente(cli.getId());
						jtConsultaCliente.setModel(new ClienteTableModel(ClienteDAO.getInstace().getListaClientes() ) );
					}			
				} catch (SQLException | ArrayIndexOutOfBoundsException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		final JButton btnRestaurar = new JButton("Restaurar");
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (jtConsultaCliente.getSelectedRow() == -1)
						throw new ArrayIndexOutOfBoundsException("Selecione um Cliente");
					int opcao = JOptionPane.showConfirmDialog(null, 
						"Deseja Restaurar o cliente selecionado?", 
						"Restaurar Cliente", 
						JOptionPane.YES_NO_OPTION);
					if ( opcao == 0){
						Cliente cli = null;
						cli =  ClienteDAO.getInstace().getListaClientes().get(jtConsultaCliente.getSelectedRow());	
						ClienteControl cc = new ClienteControl();
						cc.restauraCliente(cli.getId());
						jtConsultaCliente.setModel(new ClienteTableModel(ClienteDAO.getInstace().getListaClientes() ) );
					}			
				} catch (SQLException | ArrayIndexOutOfBoundsException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnRestaurar.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1175, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(btnRestaurar, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1171, GroupLayout.PREFERRED_SIZE)))
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
		
		jtConsultaCliente = new JTable();
		scrollPane.setViewportView(jtConsultaCliente);
		try {
			jtConsultaCliente.setModel(new ClienteTableModel( new ClienteControl().showAllClientes() ) );	
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		jtConsultaCliente.getColumnModel().getColumn(0).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtConsultaCliente.getColumnModel().getColumn(1).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtConsultaCliente.getColumnModel().getColumn(1).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtConsultaCliente.getColumnModel().getColumn(2).setResizable(false);
		jtConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtConsultaCliente.getColumnModel().getColumn(3).setResizable(false);
		getContentPane().setLayout(groupLayout);		
		
		final JLabel lblFiltro = new JLabel("Filtro");
		
		jtfFiltro = new JTextField();
		jtfFiltro.setColumns(10);
		
		JLabel lblTipoFiltro = new JLabel("Tipo filtro");
		
		final JComboBox<Object> jcbTipoFiltro = new JComboBox<Object>();
		jcbTipoFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfFiltro.setText(null);
			}
		});
		jcbTipoFiltro.setModel(new DefaultComboBoxModel<Object>(new String[] {"Nome", "CPF / CNPJ", "Email", "Telefone"}));
		final JCheckBox jckbExluidos = new JCheckBox("Exclu\u00EDdos");
		jckbExluidos.setBackground(SystemColor.inactiveCaption);
		
		
		JButton btnNewButton_1 = new JButton("Procurar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(jckbExluidos.isSelected()){ 
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
					btnRestaurar.setVisible(true);
				}
				else{ 
					btnExcluir.setEnabled(true); 
					btnAlterar.setEnabled(true); 
					btnRestaurar.setVisible(false);
				}
				
				try {
					jtConsultaCliente.setModel(new ClienteTableModel( 
							new ClienteControl().showFilterClientes(jcbTipoFiltro.getSelectedItem().toString() ,jtfFiltro.getText(), jckbExluidos.isSelected() ) ) );
					
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
					.addComponent(jtfFiltro, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTipoFiltro)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jcbTipoFiltro, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(jckbExluidos)
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
						.addComponent(lblTipoFiltro)
						.addComponent(jcbTipoFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jckbExluidos))
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
