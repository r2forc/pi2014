package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.RelatorioStatus;

public class RelatorioDAO {
	private static RelatorioDAO instance;
	private Connection con = ConnectionUtil.getConnection();
	private static Integer status = 0;

	public static RelatorioDAO getInstance() {
		if (instance == null) {
			instance = new RelatorioDAO();
		}
		return instance;
	}

	public ArrayList<RelatorioStatus> showAllOrcamentos()
			throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM orcamento orc JOIN cliente cli ON cli.id = orc.cliente_id  ORDER BY descricao ASC";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		RelatorioStatus oRetorno = null;

		ArrayList<RelatorioStatus> listaRelatorio = new ArrayList<>();

		while (rs.next()) {

			oRetorno = new RelatorioStatus();

			oRetorno.getCliente().setNome(rs.getString("cli.nome"));
			oRetorno.setData(rs.getDate("data"));
			status = rs.getInt("status");
			switch (status) {
			case 0:
				oRetorno.setStatus("Aguardando");
				break;
			case 1:
				oRetorno.setStatus("Aprovado");
				break;
			}
			oRetorno.setValor(rs.getDouble("valorTotal"));

			listaRelatorio.add(oRetorno);
		}
		return listaRelatorio;
	}

	public ArrayList<RelatorioStatus> showFilterRelatorioStatus(String cliente,
			String status, String dataInicial, String dataFinal)
			throws ClassNotFoundException, SQLException {

		String query = " SELECT * FROM orcamento orc JOIN cliente cli ON cli.id = orc.cliente_id  WHERE (status = "
				+ status + ")";
		if (!(cliente.equals("")))
			query += " AND nome LIKE '%" + cliente + "%' ";

		if (dataInicial != null) {
			query += " AND (data BETWEEN '" + dataInicial;
			if (dataFinal == null) {
				Date data = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dataFinal = sdf.format(data);
				query += "' AND '" + dataFinal + "')";
			} else {
				query += " AND '" + dataFinal + "')";

			}
		}
		System.out.println(query);

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		RelatorioStatus oRetorno = null;
		ArrayList<RelatorioStatus> listaRelatorio = new ArrayList<>();

		while (rs.next()) {

			oRetorno = new RelatorioStatus();

			oRetorno.getCliente().setNome(rs.getString("nome"));
			oRetorno.setData(rs.getDate("data"));
			switch (this.status) {
			case 0:
				oRetorno.setStatus("Aguardando");
				break;
			case 1:
				oRetorno.setStatus("Aprovado");
				break;
			}
			oRetorno.setValor(rs.getDouble("valorTotal"));

			listaRelatorio.add(oRetorno);
		}
		return listaRelatorio;
	}

}
