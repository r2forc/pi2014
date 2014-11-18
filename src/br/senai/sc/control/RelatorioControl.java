package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.dao.RelatorioDAO;
import br.senai.sc.model.RelatorioStatus;

public class RelatorioControl {
	public ArrayList<RelatorioStatus> showAllRelatorios()
			throws ClassNotFoundException, SQLException {
		return RelatorioDAO.getInstance().showAllOrcamentos();
	}

	public ArrayList<RelatorioStatus> procurarPorFiltro(String cliente,
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

		return RelatorioDAO.getInstance().showFilterRelatorioStatus(cliente,
				status, dataInicial, dataFinal);
	}

}
