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

import javax.swing.JLabel;

import br.senai.sc.control.OrcamentoFlashControl;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.model.Cliente;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class OrcamentoUI extends JInternalFrame {
	private JTextField jtfCliente;
	private JTextField jtfServico;
	private JTable jtListaServicos;
	private JTextField jtfOriginais;
	private JTextField jtfCopias;

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
		setBounds(0, 0, 1200, 600);
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

					@Override
					public void internalFrameActivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameClosing(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameDeactivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameDeiconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameIconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameOpened(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}  
				});  
			}
		});
		btnProcurarCliente.setIcon(new ImageIcon(OrcamentoUI.class.getResource("/br/senai/sc/icons/search.png")));
		
		JLabel jlServicos = new JLabel("Servi\u00E7os:");
		
		jtfServico = new JTextField();
		jtfServico.setText("");
		jtfServico.setColumns(10);
		
		JButton jbProcurarServicos = new JButton("");
		jbProcurarServicos.setIcon(new ImageIcon(OrcamentoUI.class.getResource("/br/senai/sc/icons/search.png")));
		
		JScrollPane spListaClientes = new JScrollPane();
		
		JLabel jlQuantidadeDeOriginais = new JLabel("Quantidade de Originais:");
		
		jtfOriginais = new JTextField();
		jtfOriginais.setColumns(10);
		
		JLabel jlCopias = new JLabel("Quantidade de C\u00F3pias:");
		
		jtfCopias = new JTextField();
		jtfCopias.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(jlServicos)
										.addComponent(jlCliente))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jtfServico)
										.addComponent(jtfCliente, GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE))
									.addGap(43)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnProcurarCliente)
										.addComponent(jbProcurarServicos, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
								.addComponent(spListaClientes))
							.addContainerGap(24, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jlQuantidadeDeOriginais)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfOriginais, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
							.addGap(123)
							.addComponent(jlCopias)
							.addGap(18)
							.addComponent(jtfCopias, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addGap(33))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnProcurarCliente, 0, 0, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(jlCliente)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlServicos)
						.addComponent(jtfServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbProcurarServicos, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spListaClientes, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlQuantidadeDeOriginais)
						.addComponent(jtfOriginais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfCopias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar)
						.addComponent(jlCopias))
					.addGap(353))
		);
		
		jtListaServicos = new JTable();
		spListaClientes.setViewportView(jtListaServicos);
		jtListaServicos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Descri\u00E7\u00E3o", "Valor Unt"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtListaServicos.getColumnModel().getColumn(0).setResizable(false);
		jtListaServicos.getColumnModel().getColumn(0).setPreferredWidth(200);
		jtListaServicos.getColumnModel().getColumn(1).setResizable(false);
		jtListaServicos.getColumnModel().getColumn(1).setPreferredWidth(100);
		getContentPane().setLayout(groupLayout);

	}
}
