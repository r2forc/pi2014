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

public class OrcamentoUI extends JInternalFrame {
	private JTextField jtfCliente;

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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(jlCliente)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, 1008, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnProcurarCliente)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnProcurarCliente, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(jlCliente)
							.addComponent(jtfCliente)))
					.addGap(547))
		);
		getContentPane().setLayout(groupLayout);

	}
}
