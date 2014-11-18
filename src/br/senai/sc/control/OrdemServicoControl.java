package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.dao.RelatorioDAO;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.RelatorioStatus;

public class OrdemServicoControl {

	public static void insertOrdemServico(OrdemServico os) throws SQLException {
		try {
			if (OrdemServicoDAO.getInstace().verificarServicosOrdemServicos(
					os.getId(), os.getServico().getId())) {
				throw new Exception("Serviço já adicionado a lista");
			}
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

	public ArrayList<OrdemServico> procurarPorFiltro(String cliente,
			String status, String dataInicial, String dataFinal)
			throws ClassNotFoundException, SQLException {

		switch (status) {
		case "Todos":
			status = "0 or 1";
			break;
		case "Aguardando":
			status = "0";
			break;
		case "Aprovado":
			status = "1";
		}

		String[] datasFinais = dataFinal.split("/");
		dataFinal = datasFinais[2] + "-" + datasFinais[1] + "-"
				+ datasFinais[0];

		String[] datasIniciais = dataInicial.split("/");
		dataInicial = datasIniciais[2] + "-" + datasIniciais[1] + "-"
				+ datasIniciais[0];

		if (dataInicial.equals("____-__-__")) {
			dataInicial = null;
		}

		if (dataFinal.equals("____-__-__")) {
			dataFinal = null;

		}

		return OrdemServicoDAO.getInstace().showFilterOrdemServico(cliente,
				status, dataInicial, dataFinal);
	}

	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
}
