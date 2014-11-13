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

import javax.swing.border.EtchedBorder;


import br.senai.sc.control.ServicoControl;

import br.senai.sc.model.Servico;

public class CadastrarEditarServico extends JInternalFrame {
	private JTextField jtfDescricao;
	private JTextField jtfValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEditarServico frame = new CadastrarEditarServico(null);
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
	public CadastrarEditarServico(final Servico serv) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setClosable(true);
		setBounds(100, 100, 494, 180);
		
		JLabel jlDescricao = new JLabel("Descricao");
		
		jtfDescricao = new JTextField();
		jtfDescricao.setColumns(10);

		JLabel jlValor = new JLabel("Valor");
		
		jtfValor = new JTextField();
		jtfValor.setColumns(10);
		
		if (serv != null ){
			jtfDescricao.setText(serv.getDescricao());
			jtfValor.setText(serv.getValorUnt().toString());
		}
		
		
		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( serv != null){
					//Editar
					serv.setDescricao(jtfDescricao.getText());
					serv.setValorUnt(Double.parseDouble(jtfValor.getText()));
					ServicoControl sc = new ServicoControl();
					try {
						sc.editServico( serv );
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
				} else {
					//Inserir
					Servico s = new Servico();
					s.setDescricao(jtfDescricao.getText());
					s.setValorUnt(Double.parseDouble(jtfValor.getText()));
					
					ServicoControl sc = new ServicoControl();
					try {
						sc.insertServico( s );
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				dispose();
			}
		});
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jlDescricao)
						.addComponent(jlValor))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jtfValor, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(jbCancelar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
							.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)))
					.addGap(135))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(jbSalvar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(390, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlDescricao)
						.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlValor)
						.addComponent(jtfValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbSalvar)
						.addComponent(jbCancelar))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}


}
