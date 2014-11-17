package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.OrdemServico;

public class OrdemServicoControl {

	public static void insertOrdemServico(OrdemServico os) {
		try {
			ArrayList<OrdemServico> listaOS = new OrdemServicoDAO()
					.showItensServicoOrdemServicos();
			boolean contem = false;
			for (OrdemServico arrayOS : listaOS) {
				arrayOS.getServico().getId().equals(os.getServico().getId());
				System.out.println("ID COMPARA: "
						+ arrayOS.getServico().getId());
				System.out.println("ID VERIFICA: " + os.getServico().getId());

				contem = true;
			}
			if (contem)
				throw new SQLException("Serviço já adicionado a lista");
			if (os.getServico().getValorUnt() <= 0)
				throw new Exception("Valor unitário inválido");
			if (os.getServico().getCopias() <= 0)
				throw new Exception("Quantidade de cópias inválidas");
			if (os.getServico().getOriginais() <= 0)
				throw new Exception("Quantidade de copias inválidas");
			OrdemServicoDAO.getInstace().insertOrdemServico(os);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} finally {

		}
	}

	public static void deleteServicoOrdemServico(OrdemServico os)
			throws SQLException {
		OrdemServicoDAO.getInstace().deleteServicoOrdemServico(
				os.getServico().getId());
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
