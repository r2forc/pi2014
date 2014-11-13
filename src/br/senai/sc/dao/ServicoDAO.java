package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Servico;
import br.senai.sc.model.ConnectionUtil;

public class ServicoDAO {
	private static ServicoDAO instance;
	private Connection con = ConnectionUtil.getConnection();

	public static ServicoDAO getInstace() {
		if (instance == null) {
			instance = new ServicoDAO();
		}
		return instance;
	}

	public void insertServico(Servico servico) throws SQLException {
		try {
			String query = "INSERT INTO servico (descricao, valorUnt) values (?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, servico.getDescricao());
			stmt.setDouble(2, servico.getValorUnt());
			stmt.execute();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void editServico(Servico servico) throws SQLException {
		try {
			String query = "UPDATE servico SET descricao = ?, "
					+ "valorUnt = ? " + "WHERE id = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, servico.getDescricao());
			stmt.setDouble(2, servico.getValorUnt());
			stmt.setInt(3, servico.getId());
			stmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void deleteServico(Integer id) throws SQLException {
		try {
			String query = "DELETE FROM servico WHERE id =?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);

			stmt.executeUpdate();
			System.out.println(query);
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public ArrayList<Servico> showAllServicos() throws ClassNotFoundException,
			SQLException {

		String query = "SELECT * FROM servico ORDER BY descricao ASC;";

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

	public ArrayList<Servico> showFilterServicos(String value)
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM Servico WHERE descricao LIKE '%"
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
