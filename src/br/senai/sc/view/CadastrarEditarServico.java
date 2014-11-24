package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import br.senai.sc.control.ServicoControl;
import br.senai.sc.model.Servico;

public class CadastrarEditarServico extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		getContentPane().setBackground(new Color(176, 196, 222));
		setTitle("Cadastrar Servi\u00E7o");
		if(serv != null)
			setTitle("Editar Servi\u00E7o");
		setFrameIcon(new ImageIcon(CadastrarEditarServico.class.getResource("/br/senai/sc/icons/edit_icon.png")));
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBounds(100, 100, 494, 180);
		
		JLabel jlDescricao = new JLabel("Descricao");
		
		jtfDescricao = new JTextField();
		jtfDescricao.setColumns(10);
		
		jtfValor = new JTextField();
		jtfValor.setColumns(10);
		
		JLabel jlValor = new JLabel("Valor");

		if (serv != null ){
			jtfDescricao.setText(serv.getDescricao());
			jtfValor.setText(serv.getValorUnt().toString());
		}
		
		
		JButton jbSalvar = new JButton("Salvar");
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( serv != null){
					//Editar
					try {
						Servico s = new Servico();
						s.setDescricao(jtfDescricao.getText());
						if(jtfValor.getText().equals(""))
							throw new Exception("Digite um valor válido");
						Double valor = Double.parseDouble(jtfValor.getText());
						s.setValorUnt(valor);
						if( valor < 0.0)
							throw new Exception("Digite um valor válido");
						s.setId(serv.getId());
						ServicoControl sc = new ServicoControl();
						if(sc.editServico(s))
						dispose();
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Digite um valor válido");
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					
				} else {
					//Inserir
					try {
					Servico s = new Servico();
					s.setDescricao(jtfDescricao.getText());
					if(jtfValor.getText().equals(""))
						throw new Exception("Digite um valor válido");
					Double valor = Double.parseDouble(jtfValor.getText());
					s.setValorUnt(valor);
					if( valor < 0.0)
						throw new Exception("Digite um valor válido");
					ServicoControl sc = new ServicoControl();
					if(sc.insertServico( s ))
					dispose();
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Digite um valor válido");
					}catch (Exception e) {
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
		

		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(jbSalvar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(284, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jlDescricao)
						.addComponent(jlValor))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(jbCancelar, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
							.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
						.addComponent(jtfValor, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addGap(48))
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
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbSalvar)
						.addComponent(jbCancelar))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
