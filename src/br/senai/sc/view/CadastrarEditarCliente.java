package br.senai.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.senai.sc.utils.MaskFields;
import br.senai.sc.control.ClienteControl;
import br.senai.sc.model.Cliente;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.SystemColor;

public class CadastrarEditarCliente extends JInternalFrame {
	private JTextField jtfNome;
	private JTextField jtfEmail;
	private JFormattedTextField jtfCPFeCNPJ;
	private JFormattedTextField jtfTelefone;
	private JComboBox jcbCPFeCNPJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEditarCliente frame = new CadastrarEditarCliente(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public CadastrarEditarCliente(final Cliente cli) throws ParseException {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 600, 241);
		
		JLabel jlNome = new JLabel("Nome:");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		
		
		JLabel lblEmail = new JLabel("E-mail:");
		
		jtfEmail = new JTextField();
		jtfEmail.setColumns(10);
		
		JLabel jlTelefone = new JLabel("Telefone:");
		
		
		final JComboBox jcbCPFeCNPJ = new JComboBox();
		jcbCPFeCNPJ.setModel(new DefaultComboBoxModel(new String[] {"CPF", "CNPJ"}));
		jcbCPFeCNPJ.setBackground(SystemColor.inactiveCaptionBorder);
		
		MaskFields mascara = new MaskFields();
		JFormattedTextField formattedTextField = null;
		try { 
			MaskFormatter cpf = new MaskFormatter("###.###.###-##");  
			jtfCPFeCNPJ = new JFormattedTextField(); 
			jtfCPFeCNPJ.setFormatterFactory( new DefaultFormatterFactory(cpf) );  
			
			jtfTelefone = new JFormattedTextField(mascara.maskTelefone(null));
		} 
		catch (ParseException e) {	
			e.printStackTrace(); 
		}
		
		if (cli != null ){
			jtfNome.setText(cli.getNome());
			jtfCPFeCNPJ.setText(cli.getCpf());
			jtfEmail.setText(cli.getEmail());
			jtfTelefone.setText(cli.getTelefone());
			jtfCPFeCNPJ.enable(false);
			jcbCPFeCNPJ.enable(false);
			
		}
		
		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( cli != null){
					//Editar
					cli.setNome(jtfNome.getText());
					cli.setCpf(jtfCPFeCNPJ.getText());
					cli.setEmail(jtfEmail.getText());
					cli.setTelefone(jtfTelefone.getText());
					
					ClienteControl cc = new ClienteControl();
					try {
						cc.editCliente( cli );
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
				} else {
					//Inserir
					Cliente c = new Cliente();
					c.setNome(jtfNome.getText());
					c.setCpf(jtfCPFeCNPJ.getText());
					c.setEmail(jtfEmail.getText());
					c.setTelefone(jtfTelefone.getText());
					ClienteControl cc = new ClienteControl();
					try {
						if(cc.insertCliente( c ))
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				
			}
		});
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		

		jcbCPFeCNPJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(jcbCPFeCNPJ.getSelectedIndex() == 0){
						jtfCPFeCNPJ.setValue(null);
						MaskFields mascara = new MaskFields();
						MaskFormatter cpf = new MaskFormatter("###.###.###-##");  
						jtfCPFeCNPJ.setFormatterFactory( new DefaultFormatterFactory(cpf) );  
					}else{
						jtfCPFeCNPJ.setValue(null);
						MaskFields mascara = new MaskFields();
						MaskFormatter cpnj = new MaskFormatter("##.###.###/####-##");  
						jtfCPFeCNPJ.setFormatterFactory( new DefaultFormatterFactory(cpnj) );  
						}        
				} catch (ParseException e) { e.printStackTrace(); }
			}
		});
		
		

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jlNome)
						.addComponent(lblEmail)
						.addComponent(jlTelefone)
						.addComponent(jcbCPFeCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jtfTelefone)
						.addComponent(jtfEmail)
						.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
						.addComponent(jtfCPFeCNPJ, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
					.addGap(19))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(jbSalvar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
					.addComponent(jbCancelar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlNome)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfCPFeCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbCPFeCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlTelefone)
						.addComponent(jtfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbSalvar)
						.addComponent(jbCancelar))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
