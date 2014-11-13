package br.senai.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.control.ServicoControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Servico;
import br.senai.sc.utils.ClienteTableModel;
import br.senai.sc.utils.ServicoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

public class ConsultaServicosUI extends JInternalFrame {
	private JTextField jtfFiltro;
	private JTable table;
	private JTable jtConsultaServico;
	
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
	 */
	public ConsultaServicosUI() {
		setClosable(true);
		setTitle("Consultar Servi\u00E7os");
		setBorder(null);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1200, 600);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		
		JButton btnNovo = new JButton("Novo");
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
			}
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servico serv = null;
				try {
					serv = (Servico) new ServicoTableModel(
							new ServicoControl().showAllServicos())
								.get(jtConsultaServico.getSelectedRow());
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				CadastrarEditarServico ces = new CadastrarEditarServico(serv);
				PrincipalUI.obterInstancia().getContentPane().add(ces);
				ces.setFocusable(true);
				ces.moveToFront();
				getContentPane().add(ces, 0);
				ces.requestFocus();
				ces.setVisible(true);
				
			}
		});
		
		JButton btnNewButton = new JButton("Exluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcao = JOptionPane.showConfirmDialog(null, 
						"Deseja excluir o serviço selecionado?", 
						"Excluir Serviço", 
						JOptionPane.YES_NO_OPTION);
				if ( opcao == 0){
					Servico ser = null;
					try {
						ser = (Servico) new ServicoTableModel(
								new ServicoControl().showAllServicos())
									.get(jtConsultaServico.getSelectedRow());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ServicoControl sc = new ServicoControl();
					try {
						sc.deleteServico(ser.getId());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				try {
					jtConsultaServico.setModel(new ServicoTableModel( 
							new ServicoControl().showAllServicos() ) );
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jtConsultaServico.setModel(new ServicoTableModel( 
							new ServicoControl().showAllServicos() ) );
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
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
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
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
						.addComponent(btnNewButton)
						.addComponent(btnAtualizar))
					.addGap(69))
		);
		
		jtConsultaServico = new JTable();
		scrollPane.setViewportView(jtConsultaServico);
		try {
			jtConsultaServico.setModel(new ServicoTableModel( 
					new ServicoControl().showAllServicos() ) );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cliente cli = new Cliente();
				try {
					jtConsultaServico.setModel(new ServicoTableModel( 
							new ServicoControl().showFilterServicos(jtfFiltro.getText() ) ) );
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
					.addComponent(jtfFiltro, GroupLayout.PREFERRED_SIZE, 902, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
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
						.addComponent(btnNewButton_1))
					.addGap(8))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
