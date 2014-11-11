package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.dao.ServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Servico;

public class ServicoControl {
	public ArrayList<Servico> showAllServicos() throws ClassNotFoundException,
			SQLException {
		return ServicoDAO.getInstace().showAllServicos();
	}
	public ArrayList<Servico> showFilterServicos(String column, String value)
			throws ClassNotFoundException, SQLException {
		return ServicoDAO.getInstace().showFilterServicos(column, value);
	}
}
