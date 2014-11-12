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
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.ClienteTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaClientesUI extends JInternalFrame {
	private JTextField textField;
	private JTable table;
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
		setTitle("Consultar Clientes");
		setBorder(null);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1200, 600);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		
		JButton btnNovo = new JButton("Novo");
		
		JButton btnAlterar = new JButton("Alterar");
		
		JButton btnNewButton = new JButton("Exluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcao = JOptionPane.showConfirmDialog(null, 
						"Deseja excluir o cliente selecionado?", 
						"Excluir Cliente", 
						JOptionPane.YES_NO_OPTION);
				if ( opcao == 0){
					Cliente cli = null;
					try {
						cli = (Cliente) new ClienteTableModel(
								new ClienteControl().showAllClientes())
									.get(jtConsultaCliente.getSelectedRow());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ClienteControl cc = new ClienteControl();
					try {
						cc.deleteCliente(cli.getId());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				try {
					jtConsultaCliente.setModel(new ClienteTableModel( 
							new ClienteControl().showAllClientes() ) );
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
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(btnNewButton))
					.addGap(69))
		);
		
		jtConsultaCliente = new JTable();
		scrollPane.setViewportView(jtConsultaCliente);
		try {
			jtConsultaCliente.setModel(new ClienteTableModel( 
					new ClienteControl().showAllClientes() ) );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
		JLabel lblFiltro = new JLabel("Filtro");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblTipoFiltro = new JLabel("Tipo filtro");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblFiltro)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 830, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(lblTipoFiltro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltro)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoFiltro)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
