package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;

public class ClienteDAO {

	private static ClienteDAO instance;
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private Connection con = ConnectionUtil.getConnection();

	public static ClienteDAO getInstace() {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	public void insertCliente(Cliente cliente) {
		this.listaClientes.add(cliente);
	}

	public void editCliente(Cliente cliente) {
		this.listaClientes.set(verificaExistencia(cliente), cliente);
	}

	public void deleteCliente(Cliente cliente) {
		// this.listaClientes.remove(verificaExistencia(cliente));
		this.listaClientes.remove(cliente);
	}

	public ArrayList<Cliente> showAllClientes() throws ClassNotFoundException,
			SQLException {

		String query = "SELECT * FROM Cliente ORDER BY nome ASC;";

		PreparedStatement stmt = obterConexao().prepareStatement(query);

		ResultSet rs = stmt.executeQuery();

		Cliente cRetorno = null;
		ArrayList<Cliente> listaClientes = new ArrayList<>();

		while (rs.next()) {

			cRetorno = new Cliente();

			cRetorno.setId(rs.getInt("id"));
			cRetorno.setNome(rs.getString("nome"));
			cRetorno.setCpf(rs.getString("cpf"));
			cRetorno.setEmail(rs.getString("cpf"));
			cRetorno.setTelefone(rs.getString("cpf"));

			listaClientes.add(cRetorno);
		}
		return listaClientes;
	}

	public Integer verificaExistencia(Cliente cliente) {
		for (int i = 0; i < this.listaClientes.size(); i++) {
			if (cliente.getId().equals(this.listaClientes.get(i).getId())) {
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
