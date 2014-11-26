package br.senai.sc.view;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.control.OrdemServicoControl;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.utils.OrdemServicoTableModel;
import javax.swing.SwingConstants;

public class MostrarOSUI extends JInternalFrame {
	private JTextField jtfNomeCliente;
	private JTextField jtfData;
	private JTable jtListaServicosOS;
	private JTextField jtfDescricao;
	private JTextField jtfStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarOSUI frame = new MostrarOSUI(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MostrarOSUI(final OrdemServico os) {
		setRootPaneCheckingEnabled(false);
		setFrameIcon(new ImageIcon(
				MostrarOSUI.class
						.getResource("/br/senai/sc/icons/relatorio_insumo.png")));
		setTitle("ORC R2F - Impress\u00E3o Ordem Servi\u00E7o");
		setBackground(SystemColor.inactiveCaption);
		setRootPaneCheckingEnabled(false);
		setEnabled(false);
		setBorder(null);
		setSize(1500, 1000);
		setBounds(0, 0, 1200, 600);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				"Detalhes da Ordem de Servi\u00E7o", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 252,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblNomeCliente = new JLabel("Nome:");

		jtfNomeCliente = new JTextField();
		jtfNomeCliente.setEditable(false);
		jtfNomeCliente.setColumns(10);
		jtfNomeCliente.setText(os.getCliente().getNome());
		JLabel lblData = new JLabel("Data:");

		jtfData = new JTextField();
		jtfData.setHorizontalAlignment(SwingConstants.CENTER);
		jtfData.setEditable(false);
		jtfData.setColumns(10);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		jtfData.setText(sdf.format(os.getData()));

		JLabel lblServicos = new JLabel(
				"Servi\u00E7os que cont\u00E9m na ordem de servi\u00E7o:");

		JScrollPane spItensOS = new JScrollPane();

		JLabel lblTotalR$ = new JLabel("Total:  R$:");

		JLabel lblValorTotal = new JLabel("0.00");

		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(os.getValorTotal());
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		lblValorTotal.setText(string2);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(MostrarOSUI.class
				.getResource("/br/senai/sc/icons/exit_icon.png")));

		jtfDescricao = new JTextField();
		jtfDescricao.setEditable(false);
		jtfDescricao.setColumns(10);
		jtfDescricao.setText(os.getDescricao());

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");

		jtfStatus = new JTextField();
		jtfStatus.setHorizontalAlignment(SwingConstants.CENTER);
		jtfStatus.setEditable(false);
		jtfStatus.setColumns(10);
		if (os.getStatus().equals("Aprovado")) {
			jtfStatus.setText("Aprovado");
		} else {
			jtfStatus.setText("Aguardando");
		}
		JLabel lblStatus = new JLabel("Status:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(25)
							.addComponent(lblNomeCliente)
							.addGap(7)
							.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 567, Short.MAX_VALUE)
							.addComponent(lblStatus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfStatus, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblData)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfData, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblServicos))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(spItensOS, GroupLayout.PREFERRED_SIZE, 1148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDescricao)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtfDescricao, GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 850, Short.MAX_VALUE)
					.addComponent(lblTotalR$, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblValorTotal, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomeCliente)
						.addComponent(jtfData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblData)
						.addComponent(jtfStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus))
					.addGap(18)
					.addComponent(lblServicos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spItensOS, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescricao)
						.addComponent(jtfDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTotalR$)
							.addComponent(lblValorTotal)))
					.addContainerGap(43, Short.MAX_VALUE))
		);

		jtListaServicosOS = new JTable();
		try {
			new OrdemServicoControl();
			jtListaServicosOS.setModel(new OrdemServicoTableModel(
					OrdemServicoControl.showItensServicoOrdemServicos(os
							.getId())));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		jtListaServicosOS.getColumnModel().getColumn(0).setResizable(false);
		jtListaServicosOS.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtListaServicosOS.getColumnModel().getColumn(1).setResizable(false);
		jtListaServicosOS.getColumnModel().getColumn(1).setPreferredWidth(150);
		spItensOS.setViewportView(jtListaServicosOS);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
