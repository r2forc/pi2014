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

		String query = "SELECT * FROM orcamento_has_servico ORDER BY descricao ASC;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Orcamento osRetorno = null;
		ArrayList<Orcamento> listaOS = new ArrayList<>();

		while (rs.next()) {

			osRetorno = new Orcamento();

			osRetorno.getOrdemServico().setId(rs.getInt("orcamento_id"));
			osRetorno.getServico().setId(rs.getInt("servico_id"));
			osRetorno.setQuantidadeOriginal(rs.getInt("quantidadeOriginal"));
			osRetorno.setCopias(rs.getInt("copias"));
			osRetorno.setValorTotal(rs.getDouble("valorTotal"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}
}
