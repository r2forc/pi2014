package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.OrdemServico;

public class OrdemServicoControl {

	public static void insertOrdemServico(OrdemServico os) throws SQLException {
		try {
			if (OrdemServicoDAO.getInstace().verificarServicosOrdemServicos(
					os.getId(), os.getServico().getId())) {
				throw new Exception("Servi�o j� adicionado a lista");
			}
			if (os.getServico().getValorUnt() <= 0)
				throw new Exception("Valor unit�rio inv�lido");
			if (os.getServico().getCopias() <= 0)
				throw new Exception("Quantidade de c�pias inv�lidas");
			if (os.getServico().getOriginais() <= 0)
				throw new Exception("Quantidade de copias inv�lidas");
			OrdemServicoDAO.getInstace().insertOrdemServico(os);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {

		}
	}

	public static void deleteServicoOrdemServico(Integer id_orc, Integer id_serv)
			throws SQLException {
		OrdemServicoDAO.getInstace().deleteServicoOrdemServico(id_orc, id_serv);
	}

	public static ArrayList<OrdemServico> showItensServicoOrdemServicos(
			Integer id_orc) throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showItensServicoOrdemServicos(
				id_orc);
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
