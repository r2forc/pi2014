package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.model.Cliente;

public class ClienteControl {

	public boolean insertCliente(Cliente cliente) throws SQLException {
			try{
				if (cliente.getNome().equals(""))
					throw new Exception("Digite o campo Cliente");
				if (cliente.getCpf().equals(""))
					throw new Exception("Digite o campo CPF");
				if (cliente.getEmail().equals(""))
					throw new Exception("Digite o campo E-mail");
				
				ClienteDAO.getInstace().insertCliente(cliente);
				return true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
				return false;
			}
	}

	public void editCliente(Cliente cliente) throws SQLException {
		if (cliente.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "NOME CLIENTE Obrigatorio!");
		} else if (cliente.getCpf().equals("")) {
			JOptionPane.showMessageDialog(null, "CPF CLIENTE Obrigatorio!");
		} else if (cliente.getEmail().equals("")) {
			JOptionPane.showMessageDialog(null, "E-MAIL CLIENTE Obrigatorio!");
		} else if (cliente.getTelefone().equals("")) {
			JOptionPane
					.showMessageDialog(null, "TELEFONE CLIENTE Obrigatorio!");
		} else {
			ClienteDAO.getInstace().editCliente(cliente);
		}
	}

	public void deleteCliente(Integer id) throws SQLException {
		ClienteDAO.getInstace().deleteCliente(id);
	}

	public ArrayList<Cliente> showAllClientes() throws ClassNotFoundException,
			SQLException {
		return ClienteDAO.getInstace().showAllClientes();
	}

	public ArrayList<Cliente> showFilterClientes(String column, String value)
			throws ClassNotFoundException, SQLException {
		return ClienteDAO.getInstace().showFilterClientes(column, value);
	}
}
