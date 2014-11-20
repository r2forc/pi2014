package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.RelatorioStatus;

import com.mysql.jdbc.Statement;

public class OrdemServicoDAO {

	private static OrdemServicoDAO instance;
	private Connection con = ConnectionUtil.getConnection();
	private static Integer status = 0;

	public static OrdemServicoDAO getInstace() {
		if (instance == null) {
			instance = new OrdemServicoDAO();
		}
		return instance;
	}

	public boolean verificarServicosOrdemServicos(Integer id_orc,
			Integer id_serv) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM orcamento_has_servico WHERE orcamento_id = "
				+ id_orc + " AND " + "servico_id = " + id_serv;
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		int verifica = 0;
		while (rs.next()) {
			verifica = rs.getInt("orcamento_id");
		}
		if (verifica == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void insertOrdemServico(OrdemServico os)
			throws ClassNotFoundException, SQLException {
		try {
			String query = "INSERT INTO orcamento_has_servico "
					+ "(orcamento_id, servico_id, quantidadeOriginal, copias, valorTotal) "
					+ "values (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, os.getId());
			stmt.setInt(2, os.getServico().getId());
			stmt.setInt(3, os.getServico().getOriginais());
			stmt.setInt(4, os.getServico().getCopias());
			stmt.setDouble(5, os.getValorTotal());

			stmt.execute();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void deleteServicoOrdemServico(Integer id_orc, Integer id_serv)
			throws SQLException {
		try {
			String query = "DELETE FROM orcamento_has_servico "
					+ "WHERE orcamento_id=? " + " and servico_id=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id_orc);
			stmt.setInt(2, id_serv);

			stmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void changeStatus(OrdemServico os, Integer status)
			throws SQLException {
		try {
			String query = "UPDATE orcamento SET status = ? WHERE id = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, status);
			stmt.setInt(2, os.getId());

			stmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public boolean verificarStatus(Integer status, Integer id_orc)
			throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM orcamento WHERE status = " + status
				+ " AND " + "id = " + id_orc;
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		int verifica = 0;
		while (rs.next()) {
			verifica = rs.getInt("status");
		}
		if (verifica == 0) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<OrdemServico> showItensServicoOrdemServicos(Integer id_orc)
			throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM orcamento orc JOIN orcamento_has_servico ohs"
				+ " ON orc.id = ohs.orcamento_id JOIN servico s ON s.id = ohs.servico_id"
				+ " JOIN cliente cli ON orc.cliente_id = cli.id where orc.id = +"
				+ id_orc + ";";

		PreparedStatement stmt = con.prepareStatement(query);
		// stmt.setInt(1, os.getId());
		ResultSet rs = stmt.executeQuery();
		OrdemServico osRetorno = null;
		ArrayList<OrdemServico> listaOS = new ArrayList<>();
		while (rs.next()) {
			Integer status = 0;
			osRetorno = new OrdemServico();
			osRetorno.setId(rs.getInt("id"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.setQuantidadeOriginal(rs.getInt("quantidadeOriginal"));
			osRetorno.setCopias(rs.getInt("copias"));
			osRetorno.setValorTotal(rs.getDouble("ohs.valorTotal"));
			status = rs.getInt("status");
			switch (status) {
			case 0:
				osRetorno.setStatus("Aguardando");
				break;
			case 1:
				osRetorno.setStatus("Aprovado");
				break;
			}

			// recupera valorUnt e Descricao do Servico.
			osRetorno.getServico().setId(rs.getInt("s.id"));
			osRetorno.getServico().setValorUnt(rs.getDouble("valorUnt"));
			osRetorno.getServico().setDescricao(rs.getString("s.descricao"));
			// recuper nome Cliente
			osRetorno.getCliente().setNome(rs.getString("nome"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}

	public ArrayList<OrdemServico> showOrdemServicos()
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM orcamento orc JOIN cliente cli ON "
				+ "orc.cliente_id = cli.id ORDER BY data;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Orcamento osRetorno = null;
		ArrayList<OrdemServico> listaOS = new ArrayList<>();

		while (rs.next()) {
			osRetorno = new Orcamento();

			osRetorno.setId(rs.getInt("id"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.getCliente().setNome(rs.getString("cli.nome"));
			osRetorno.getServico().setId(rs.getInt("usuario_id"));
			osRetorno.setValorTotal(rs.getDouble("valorTotal"));
			status = rs.getInt("status");
			switch (status) {
			case 0:
				osRetorno.setStatus("Aguardando");
				break;
			case 1:
				osRetorno.setStatus("Aprovado");
				break;
			}
			listaOS.add(osRetorno);
		}
		return listaOS;
	}

	public ArrayList<OrdemServico> showFilterOrdemServico(String cliente,
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
				query += "' AND '" + dataFinal + "')";
			}
		}
		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		OrdemServico oRetorno = null;
		ArrayList<OrdemServico> listaRelatorio = new ArrayList<>();

		while (rs.next()) {
			oRetorno = new OrdemServico();

			oRetorno.getCliente().setNome(rs.getString("nome"));
			oRetorno.setData(rs.getDate("data"));
			this.status = rs.getInt("status");
			switch (this.status) {
			case 0:
				oRetorno.setStatus("Aguardando");
				break;
			case 1:
				oRetorno.setStatus("Aprovado");
				break;
			}
			oRetorno.setValorTotal(rs.getDouble("valorTotal"));
			listaRelatorio.add(oRetorno);
		}
		return listaRelatorio;
	}

}
