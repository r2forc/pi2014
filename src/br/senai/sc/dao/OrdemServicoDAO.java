package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.OrdemServico;

public class OrdemServicoDAO {

	private static OrdemServicoDAO instance;
	private Connection con = ConnectionUtil.getConnection();

	public static OrdemServicoDAO getInstace() {
		if (instance == null) {
			instance = new OrdemServicoDAO();
		}
		return instance;
	}

	public void insertOrdemServico(OrdemServico os) {
	}

	public void editOrdemServico(OrdemServico os) {
	}

	public void deleteOrdemServico(OrdemServico os) {
	}

	public ArrayList<OrdemServico> showAllOrdemServicos()
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM orcamento ORDER BY descricao ASC;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		OrdemServico osRetorno = null;
		ArrayList<OrdemServico> listaOS = new ArrayList<>();

		while (rs.next()) {

			osRetorno = new OrdemServico();

			osRetorno.setId(rs.getInt("id"));
			osRetorno.getOrcamento().getServico()
					.setId(rs.getInt("servico_id"));
			osRetorno.setStatus(rs.getInt("status"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.setValorTotal(rs.getDouble("valorTotal"));
			osRetorno.setDescricao(rs.getString("descricao"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}
}
