package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;

public class OrdemServicoDAO {

	private static OrdemServicoDAO instance;
	private Connection con = ConnectionUtil.getConnection();
	private static OrdemServico ordemServico = new OrdemServico();
	ArrayList<OrdemServico> listaOS = new ArrayList<OrdemServico>();

	public static OrdemServicoDAO getInstace() {
		if (instance == null) {
			instance = new OrdemServicoDAO();
		}
		return instance;
	}

	public OrdemServico mostrarOrcamento() {
		return ordemServico;
	}

	public Servico mostrarServico(int i) {
		return ordemServico.getServico(i);
	}

	public ArrayList<Servico> mostrarServicos() {
		return ordemServico.getServicos();
	}

	public void RemoverServico(int i) {
		ordemServico.removerServico(i);
	}
	
	public void insertCliente(Cliente cliente) {
		ordemServico.setCliente(cliente);
	}
	

	public void insertOrdemServico(OrdemServico os)
			throws ClassNotFoundException, SQLException {
		try {
			String query = "INSERT INTO orcamento_has_servico "
					+ "(orcamento_id, servico_id, quantidadeOriginal, copias, valorTotal) "
					+ "values (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, 1);
			stmt.setInt(2, os.getServico().getId());
			stmt.setInt(3, os.getQuantidadeOriginal());
			stmt.setInt(4, os.getCopias());
			stmt.setDouble(5, os.getValorTotal());
			stmt.execute();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void editOrdemServico(OrdemServico os) {
	}

	public void deleteServicoOrdemServico(OrdemServico os) {
		this.listaOS.remove(verificaExistencia(os));
	}

	public ArrayList<OrdemServico> showItensServicoOrdemServicos()
			throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM orcamento orc JOIN orcamento_has_servico ohs"
				+ " ON orc.id = ohs.orcamento_id JOIN servico s ON s.id = ohs.servico_id"
				+ " JOIN cliente cli ON orc.cliente_id = cli.id where orc.id = 1";
		System.out.println(query);
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
			osRetorno.getServico().setValorUnt(rs.getDouble("valorUnt"));
			osRetorno.getServico().setDescricao(rs.getString("descricao"));
			// recuper nome Cliente
			osRetorno.getCliente().setNome(rs.getString("nome"));

			listaOS.add(osRetorno);
		}
		return listaOS;
	}

	public ArrayList<OrdemServico> showOrdemServicos()
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM orcamento orc join cliente cli on cli.id = orc.id;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		OrdemServico osRetorno = null;
		ArrayList<OrdemServico> listaOS = new ArrayList<>();

		while (rs.next()) {

			osRetorno = new OrdemServico();

			osRetorno.setId(rs.getInt("id"));
			osRetorno.setData(rs.getDate("data"));
			osRetorno.getCliente().setNome(rs.getString("nome"));
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

	public Integer verificaExistencia(OrdemServico os) {
		for (int i = 0; i < listaOS.size(); i++) {
			if (os.getServico().getId() == (this.listaOS.get(i).getId())) {
				return i;
			}
		}
		return null;
	}
}
