package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;

public class OrdemServicoControl {

	public static void insertOrdemServico(OrdemServico os)
			throws ClassNotFoundException, SQLException {
		// if (os.getId() == null) {
		// print("ID ORCAMENTO Obrigatorio!");
		// xd
		if (os.getServico().getId() == null) {
			print("ID SERVIÇO Obrigatorio!");
		} else if (os.getServico().getOriginais() <= 0) {
			print("QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (os.getServico().getCopias() <= 0) {
			print("COPIAS Obrigatorio!");
		} else if (os.getValorTotal() <= 0) {
			print("VALOR TOTAL Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().insertOrdemServico(os);
		}
	}

	public static void deleteServicoOrdemServico(OrdemServico os)
			throws SQLException {
		OrdemServicoDAO.getInstace().deleteServicoOrdemServico(os);
	}

	public static ArrayList<OrdemServico> showItensServicoOrdemServicos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showItensServicoOrdemServicos();
	}

	public ArrayList<OrdemServico> showOrdemServicos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showOrdemServicos();
	}

	public ArrayList<OrdemServico> showFilterOS(String column, String value)
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showFilterOrdemServico(column,
				value);
	}

	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
}
