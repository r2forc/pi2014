package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PrincipalUI extends JFrame {

	private static final long serialVersionUID = 1L;
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
		setTitle("R2FOrc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnVendas = new JMenu("Or\u00E7amento");
		menuBar.add(mnVendas);

		JMenuItem mntmGerarOramento = new JMenuItem("Gerar Or\u00E7amento");
		mntmGerarOramento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrcamentoUI orcamento;
				try {
					orcamento = new OrcamentoUI();
					orcamento.setFocusable(true);
					getContentPane().add(orcamento, 0);
					orcamento.requestFocus();
					orcamento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnVendas.add(mntmGerarOramento);

		JMenuItem mntmConsultaOrdemDe = new JMenuItem(
				"Consulta Ordem de Servi\u00E7o");
		mntmConsultaOrdemDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaOrdemServicoUI ConsultaOS;
				try {
					ConsultaOS = new ConsultaOrdemServicoUI();
					ConsultaOS.setFocusable(true);
					getContentPane().add(ConsultaOS, 0);
					ConsultaOS.requestFocus();
					ConsultaOS.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mnVendas.add(mntmConsultaOrdemDe);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem mntmRelatrioPorStatus = new JMenuItem(
				"Relat\u00F3rio Por Status");
		mntmRelatrioPorStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RelatorioPorStatusUI relatorioPorStatus;

				try {
					relatorioPorStatus = new RelatorioPorStatusUI();
					relatorioPorStatus.setFocusable(true);
					getContentPane().add(relatorioPorStatus, 0);
					relatorioPorStatus.requestFocus();
					relatorioPorStatus.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		mnRelatrios.add(mntmRelatrioPorStatus);

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mntmConsultarClientes = new JMenuItem("Consultar Clientes");
		mntmConsultarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClientesUI cc = new ConsultaClientesUI();
				try {
					cc.setFocusable(true);
					getContentPane().add(cc, 0);
					cc.requestFocus();
					cc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JMenuItem mntmCadastrarClientes = new JMenuItem("Cadastrar Clientes");
		mntmCadastrarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CadastrarEditarCliente cec = new CadastrarEditarCliente(
							null);
					cec.setFocusable(true);
					getContentPane().add(cec, 0);
					cec.requestFocus();
					cec.setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		mnClientes.add(mntmCadastrarClientes);
		mnClientes.add(mntmConsultarClientes);

		JMenu mnServios = new JMenu("Servi\u00E7os");
		menuBar.add(mnServios);

		JMenuItem jmiConsultarServicos = new JMenuItem(
				"Consultar Servi\u00E7os");
		jmiConsultarServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConsultaServicosUI cs = new ConsultaServicosUI();
					cs.setFocusable(true);
					getContentPane().add(cs, 0);
					cs.requestFocus();
					cs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JMenuItem mntmCadastrarServio = new JMenuItem("Cadastrar Servi\u00E7o");
		mntmCadastrarServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarEditarServico ces = new CadastrarEditarServico(null);
				ces.setFocusable(true);
				getContentPane().add(ces, 0);
				ces.requestFocus();
				ces.setVisible(true);
			}
		});
		mnServios.add(mntmCadastrarServio);
		mnServios.add(jmiConsultarServicos);

		JLabel jlSair = new JLabel("Sair");
		jlSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		menuBar.add(jlSair);
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
