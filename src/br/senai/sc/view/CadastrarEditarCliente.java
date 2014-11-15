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

import br.senai.sc.utils.MaskFields;
import br.senai.sc.control.ClienteControl;
import br.senai.sc.model.Cliente;

import javax.swing.JFormattedTextField;

public class CadastrarEditarCliente extends JInternalFrame {
	private JTextField jtfNome;
	private JTextField jtfEmail;
	private JFormattedTextField jtfCPF;
	private JFormattedTextField jtfTelefone;

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
	 */
	public CadastrarEditarCliente(final Cliente cli) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setClosable(true);
		setBounds(100, 100, 600, 241);
		
		JLabel jlNome = new JLabel("Nome:");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);

		JLabel jlCPF = new JLabel("CPF:");
		
		
		JLabel lblEmail = new JLabel("E-mail:");
		
		jtfEmail = new JTextField();
		jtfEmail.setColumns(10);
		
		JLabel jlTelefone = new JLabel("Telefone:");
		
		if (cli != null ){
			jtfNome.setText(cli.getNome());
			jtfCPF.setText(cli.getCpf());
			jtfEmail.setText(cli.getEmail());
			jtfTelefone.setText(cli.getTelefone());
			
		}
		
		
		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( cli != null){
					//Editar
					cli.setNome(jtfNome.getText());
					cli.setCpf(jtfCPF.getText());
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
					c.setCpf(jtfCPF.getText());
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
		
		
		MaskFields mascara = new MaskFields();
		JFormattedTextField formattedTextField = null;
		try {
			jtfCPF = new JFormattedTextField(mascara.maskCpf(null));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try {
			jtfTelefone = new JFormattedTextField(mascara.maskTelefone(null));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(jbSalvar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
					.addComponent(jbCancelar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jlNome)
						.addComponent(jlCPF)
						.addComponent(lblEmail)
						.addComponent(jlTelefone))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jtfTelefone)
						.addComponent(jtfEmail)
						.addComponent(jtfNome, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
						.addComponent(jtfCPF, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
					.addGap(19))
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
						.addComponent(jlCPF)
						.addComponent(jtfCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
