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
}
