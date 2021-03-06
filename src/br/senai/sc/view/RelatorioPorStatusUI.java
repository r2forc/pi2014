package br.senai.sc.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.senai.sc.control.RelatorioControl;
import br.senai.sc.dao.RelatorioDAO;
import br.senai.sc.utils.MaskFields;
import br.senai.sc.utils.RelatorioTableModel;

import javax.swing.SwingConstants;

public class RelatorioPorStatusUI extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtTabelaStatus;
	private JFormattedTextField jftfDataInicial;
	private JFormattedTextField jftfDataFinal;
	private JTextField tfCliente;
	private JLabel jlValorTotal = new JLabel("00.00");

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

	public void atualizarValor() throws ClassNotFoundException {

		Double valorTotal = 0.0;

		RelatorioDAO rc = new RelatorioDAO();

		for (int i = 0; i < rc.getInstance().getRelatorio().size(); i++) {
			valorTotal += rc.getInstance().getRelatorio().get(i).getValor();
		}
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(valorTotal);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		jlValorTotal.setText(string2);

	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public RelatorioPorStatusUI() throws ClassNotFoundException, SQLException {
		getContentPane().setBackground(new Color(176, 196, 222));
		setFrameIcon(new ImageIcon(
				"C:\\Users\\Fabr\u00EDcio\\git\\pi20144\\bin\\br\\senai\\sc\\icons\\search_user.png"));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent arg0) {
				setLocation(0, 0);
			}
		});

		setTitle("Relatorio por Status");
		setBorder(null);
		setBackground(SystemColor.inactiveCaption);
		setBounds(0, 0, 1200, 550);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1180,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 503,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		final JComboBox jcbStatus = new JComboBox();
		jcbStatus.setForeground(Color.BLACK);
		jcbStatus.setModel(new DefaultComboBoxModel(new String[] { "Todos",
				"Aguardando", "Aprovado" }));

		JLabel lblStatus = new JLabel("Status:");

		JScrollPane scrollPane = new JScrollPane();

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					jtTabelaStatus.setModel(new RelatorioTableModel(
							new RelatorioControl().procurarPorFiltro(tfCliente
									.getText(), jcbStatus.getSelectedItem()
									.toString(), jftfDataInicial.getText(),
									jftfDataFinal.getText())));
					atualizarValor();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPesquisar.setIcon(new ImageIcon(RelatorioPorStatusUI.class
				.getResource("/br/senai/sc/icons/search.png")));

		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(RelatorioPorStatusUI.class.getResource("/br/senai/sc/icons/exit_icon.png")));
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});

		JLabel lblNewLabel = new JLabel("Cliente:");

		MaskFields mascara = new MaskFields();
		JFormattedTextField formattedTextField = null;
		try {

			jftfDataInicial = new JFormattedTextField(mascara.maskData(null));
			jftfDataInicial.setHorizontalAlignment(SwingConstants.CENTER);
			jftfDataFinal = new JFormattedTextField(mascara.maskData(null));
			jftfDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblDataInicial = new JLabel("Data Inicial:");

		JLabel lblDataFinal = new JLabel("Data Final:");

		tfCliente = new JTextField();
		tfCliente.setColumns(10);

		JLabel lblValorTotalR = new JLabel("Valor Total: R$");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfCliente, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jcbStatus, 0, 93, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblDataInicial, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jftfDataInicial, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblDataFinal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jftfDataFinal, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblValorTotalR, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlValorTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(885)
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(45))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1170, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(lblDataInicial)
						.addComponent(jftfDataInicial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataFinal)
						.addComponent(jftfDataFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValorTotalR)
						.addComponent(jlValorTotal)
						.addComponent(btnSair)))
		);
		jtTabelaStatus = new JTable();

		jtTabelaStatus.setModel(new RelatorioTableModel(new RelatorioControl()
				.showAllRelatorios()));

		jtTabelaStatus.getColumnModel().getColumn(0).setResizable(false);
		jtTabelaStatus.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtTabelaStatus.getColumnModel().getColumn(1).setResizable(false);
		jtTabelaStatus.getColumnModel().getColumn(2).setResizable(false);
		jtTabelaStatus.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(jtTabelaStatus);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		atualizarValor();
	}
}
