package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.RelatorioStatus;

public class RelatorioDAO {
	private static RelatorioDAO instance;
	private Connection con = ConnectionUtil.getConnection();

	public static RelatorioDAO getInstance() {
		if (instance == null) {
			instance = new RelatorioDAO();
		}
		return instance;
	}

	public ArrayList<RelatorioStatus> showAllOrcamentos() throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM ocamento ORDER BY descricao ASC;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		RelatorioStatus oRetorno = null;
		ArrayList<RelatorioStatus> listaRelatorio = new ArrayList<>();

		while (rs.next()) {

			oRetorno = new RelatorioStatus();

			oRetorno.getCliente().setNome(rs.getString("nome"));
			oRetorno.setData(rs.getDate("data"));
			oRetorno.setStatus(rs.getInt("status"));
			oRetorno.setValor(rs.getDouble("status"));


			listaRelatorio.add(oRetorno);
		}
		return listaRelatorio;
	}
	
	public ArrayList<RelatorioStatus> showFilterRelatorioStatus(String column,
			String value) throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM orcamento orc JOIN cliente cli ON cli.id = orc.id WHERE "
				+ column + " LIKE '%" + value + "%';";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		RelatorioStatus oRetorno = null;
		ArrayList<RelatorioStatus> listaRelatorio = new ArrayList<>();

		while (rs.next()) {

			oRetorno = new RelatorioStatus();

			oRetorno.getCliente().setNome(rs.getString("nome"));
			oRetorno.setData(rs.getDate("data"));
			oRetorno.setStatus(rs.getInt("status"));
			oRetorno.setValor(rs.getDouble("status"));

			listaRelatorio.add(oRetorno);
		}
		return listaRelatorio;
	}

}
