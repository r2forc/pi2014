package br.senai.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JTable;

public class OrcamentoUI extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable jTableServicos;

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
		setBorder(null);
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setTitle("Gerar Or\u00E7amento");
		setBounds(0, 0, 1200, 600);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Servi\u00E7os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton jbPesquisaServico = new JButton("");
		jbPesquisaServico.setIcon(new ImageIcon("C:\\Users\\Felipe\\git\\pi2014\\src\\br\\senai\\sc\\icons\\search.png"));
		
		jTableServicos = new JTable();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(jTableServicos, GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 1048, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbPesquisaServico, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jTableServicos, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addComponent(jbPesquisaServico))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		textField = new JTextField();
		textField.setColumns(10);
		
		JButton jbPesquisaCliente = new JButton("");
		jbPesquisaCliente.setIcon(new ImageIcon("C:\\Users\\Felipe\\git\\pi2014\\src\\br\\senai\\sc\\icons\\search.png"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 1046, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbPesquisaCliente, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(jbPesquisaCliente, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
