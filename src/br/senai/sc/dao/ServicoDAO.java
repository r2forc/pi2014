package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.Servico;

public class ServicoDAO {
	private static ServicoDAO instance;
	private ArrayList<Servico> listaServicos = new ArrayList<Servico>();

	public static ServicoDAO getInstace() {
		if (instance == null) {
			instance = new ServicoDAO();
		}
		return instance;
	}

	public ArrayList<Servico> showAllServicos() throws ClassNotFoundException,
			SQLException {

		String query = "SELECT * FROM Servico ORDER BY descricao ASC;";

		PreparedStatement stmt = obterConexao().prepareStatement(query);

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

	public Integer verificaExistencia(Servico servico) {
		for (int i = 0; i < this.listaServicos.size(); i++) {
			if (servico.getId().equals(this.listaServicos.get(i).getId())) {
				return i;
			}
		}
		return null;
	}

	public static Connection obterConexao() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/r2forc";

		Connection con = DriverManager.getConnection(url, "root", "26051993");

		return con;
	}
}
