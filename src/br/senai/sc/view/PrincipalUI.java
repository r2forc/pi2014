package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private static PrincipalUI instancia;

	// ta tudo funcionando

	// SINGLETON
	public static PrincipalUI obterInstancia() {

		if (instancia == null) {
			instancia = new PrincipalUI();
		}

		return instancia;
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = obterInstancia();
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

	public PrincipalUI() {
		setResizable(false);
		setTitle("Sistema De Vendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnVendas = new JMenu("Or\u00E7amento");
		menuBar.add(mnVendas);

		JMenuItem mntmRegistrarVenda = new JMenuItem(
				"Gerar Ordem de Servi\u00E7o");
		mntmRegistrarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemServicoUI ordemServico;
				try {
					ordemServico = new OrdemServicoUI();
					ordemServico.setVisible(true);
					ordemServico.setFocusable(true);
					ordemServico.requestFocus();
					getContentPane().add(ordemServico, 0);
					getContentPane().add(ordemServico);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnVendas.add(mntmRegistrarVenda);

		JMenuItem mntmGerarOramento = new JMenuItem("Gerar Or\u00E7amento");
		mntmGerarOramento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrcamentoUI orcamento;
				try {
					orcamento = new OrcamentoUI();
					orcamento.setVisible(true);
					orcamento.setFocusable(true);
					orcamento.requestFocus();
					getContentPane().add(orcamento, 0);
					getContentPane().add(orcamento);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnVendas.add(mntmGerarOramento);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem mntmRelatrioPorStatus = new JMenuItem(
				"Relat\u00F3rio Por Status");
		mntmRelatrioPorStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioPorStatusUI relatorioPorStatus;

				try {
					relatorioPorStatus = new RelatorioPorStatusUI();
					relatorioPorStatus.setVisible(true);
					relatorioPorStatus.setFocusable(true);
					relatorioPorStatus.requestFocus();
					getContentPane().add(relatorioPorStatus, 0);
					getContentPane().add(relatorioPorStatus);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		mnRelatrios.add(mntmRelatrioPorStatus);

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClientesUI cc = new ConsultaClientesUI();
				;
				try {
					cc.setVisible(true);
					cc.setFocusable(true);
					cc.requestFocus();
					getContentPane().add(cc, 0);
					getContentPane().add(cc);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnClientes.add(mntmCadastrarCliente);

		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGap(0, 432, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGap(0, 263, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);
	}
}
