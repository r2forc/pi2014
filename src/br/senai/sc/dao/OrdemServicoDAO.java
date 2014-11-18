package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.OrdemServico;

import com.mysql.jdbc.Statement;

public class OrdemServicoDAO {

	private static OrdemServicoDAO instance;
	private Connection con = ConnectionUtil.getConnection();

	public static OrdemServicoDAO getInstace() {
		if (instance == null) {
			instance = new OrdemServicoDAO();
		}
		return instance;
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

			osRetorno = new OrdemServico();
			osRetorno.setId(rs.getInt("id"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.setQuantidadeOriginal(rs.getInt("quantidadeOriginal"));
			osRetorno.setCopias(rs.getInt("copias"));
			osRetorno.setValorTotal(rs.getDouble("valorTotal"));
			osRetorno.setStatus(rs.getInt("status"));
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
			osRetorno.setStatus(rs.getInt("status"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}

	public ArrayList<OrdemServico> showFilterOrdemServico(String column,
			String value) throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM orcamento orc join cliente cli on cli.id = orc.id where "
				+ column + " LIKE '%" + value + "%';";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		OrdemServico osRetorno = null;
		ArrayList<OrdemServico> listaOS = new ArrayList<>();

		while (rs.next()) {

			osRetorno = new OrdemServico();

			osRetorno.setId(rs.getInt("id"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.getCliente().setNome("nome");
			osRetorno.setValorTotal(rs.getDouble("valorTotal"));
			osRetorno.setStatus(rs.getInt("status"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}
}
