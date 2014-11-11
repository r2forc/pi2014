package br.senai.sc.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class RelatorioPorStatusUI extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioPorStatusUI frame = new RelatorioPorStatusUI();
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
	public RelatorioPorStatusUI() {
		setTitle("Relatorio por Status");
		setBorder(null);
		setBackground(new Color(50, 205, 50));
		setBounds(0, 0, 1000, 600);

	}

}
