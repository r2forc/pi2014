package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.Orcamento;

public class OrcamentoDAO {
	private static OrcamentoDAO instance;
	private Connection con = ConnectionUtil.getConnection();

	public static OrcamentoDAO getInstace() {
		if (instance == null) {
			instance = new OrcamentoDAO();
		}
		return instance;
	}

	public void insertOrcamento(Orcamento orcamento) {
	}

	public void editOrcamento(Orcamento orcamento) {
	}

	public void deleteOrcamento(Orcamento orcamento) {
	}

	public ArrayList<Orcamento> showAllOrcamentos()
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM orcamento ORDER BY descricao ASC WHERE status = 1;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Orcamento osRetorno = null;
		ArrayList<Orcamento> listaOS = new ArrayList<>();

		while (rs.next()) {

			osRetorno = new Orcamento();

			osRetorno.setId(rs.getInt("orcamento_id"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.getCliente().setId(rs.getInt("cliente_id"));
			osRetorno.getServico().setId(rs.getInt("servico_id"));
			osRetorno.setValorTotal(rs.getDouble("valorTotal"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}
}
