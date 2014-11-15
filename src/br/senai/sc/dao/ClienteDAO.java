package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;

public class ClienteDAO {

	private static ClienteDAO instance;
	private Connection con = ConnectionUtil.getConnection();

	public static ClienteDAO getInstace() {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	public void insertCliente(Cliente cliente) throws SQLException {
		try {
			String query = "INSERT INTO CLIENTE (nome, cpf, email, telefone) values (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefone());
			stmt.execute();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void editCliente(Cliente cliente) throws SQLException {
		try {
			String query = "UPDATE CLIENTE SET nome = ?," + "cpf = ?, "
					+ "email = ?, " + "telefone = ? " + "WHERE id = ?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefone());
			stmt.setInt(5, cliente.getId());
			stmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public void deleteCliente(Integer id) throws SQLException {
		try {
			String query = "DELETE FROM CLIENTE WHERE id =?;";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> showAllClientes() throws ClassNotFoundException,
			SQLException {

		String query = "SELECT * FROM Cliente ORDER BY nome ASC;";

		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Cliente cRetorno = null;
		ArrayList<Cliente> listaClientes = new ArrayList<>();

		while (rs.next()) {

			cRetorno = new Cliente();

			cRetorno.setId(rs.getInt("id"));
			cRetorno.setNome(rs.getString("nome"));
			cRetorno.setCpf(rs.getString("cpf"));
			cRetorno.setEmail(rs.getString("email"));
			cRetorno.setTelefone(rs.getString("telefone"));

			listaClientes.add(cRetorno);
		}
		return listaClientes;
	}

	public ArrayList<Cliente> showFilterClientes(String column, String value)
			throws ClassNotFoundException, SQLException {

		String query = "SELECT * FROM CLIENTE WHERE " + column + " LIKE '%"
				+ value + "%' ORDER BY nome ASC;";
		
		PreparedStatement stmt = con.prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Cliente cRetorno = null;
		ArrayList<Cliente> listaClientes = new ArrayList<>();

		while (rs.next()) {

			cRetorno = new Cliente();

			cRetorno.setId(rs.getInt("id"));
			cRetorno.setNome(rs.getString("nome"));
			cRetorno.setCpf(rs.getString("cpf"));
			cRetorno.setEmail(rs.getString("email"));
			cRetorno.setTelefone(rs.getString("telefone"));

			listaClientes.add(cRetorno);
		}
		return listaClientes;
	}
}
