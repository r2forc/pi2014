package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.Servico;

public class ServicoDAO {
	private static ServicoDAO instance;
	private ArrayList<Servico> listaServicos = new ArrayList<Servico>();
	private Connection con = ConnectionUtil.getConnection();

	public static ServicoDAO getInstace() {
		if (instance == null) {
			instance = new ServicoDAO();
		}
		return instance;
	}

	public ArrayList<Servico> showAllServicos() throws ClassNotFoundException,
			SQLException {

		String query = "SELECT * FROM Servico ORDER BY descricao ASC;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Servico sRetorno = null;
		ArrayList<Servico> listaServicos = new ArrayList<>();

		while (rs.next()) {

			sRetorno = new Servico();

			sRetorno.setId(rs.getInt("id"));
			sRetorno.setDescricao(rs.getString("descricao"));
			sRetorno.setValorUnt(rs.getDouble("valorUnt"));

			listaServicos.add(sRetorno);
		}
		return listaServicos;
	}

	public ArrayList<Servico> showFilterServicos(String column, String value)
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM Servico WHERE " + column + " LIKE '%"
				+ value + "%' ORDER BY descricao ASC;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Servico sRetorno = null;
		ArrayList<Servico> listaServicos = new ArrayList<>();

		while (rs.next()) {

			sRetorno = new Servico();

			sRetorno.setId(rs.getInt("id"));
			sRetorno.setDescricao(rs.getString("descricao"));
			sRetorno.setValorUnt(rs.getDouble("valorUnt"));

			listaServicos.add(sRetorno);
		}
		return listaServicos;
	}
}
