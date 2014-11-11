package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.model.Cliente;

public class ClienteControl {

	public void insertCliente(Cliente cliente) throws SQLException {
		if (cliente.getId() == null) {
			JOptionPane.showMessageDialog(null, "ID CLIENTE Obrigatorio!");
		} else if (cliente.getNome().equals("")) {
			JOptionPane.showMessageDialog(null, "NOME CLIENTE Obrigatorio!");
		} else if (cliente.getCpf().equals("")) {
			JOptionPane.showMessageDialog(null, "CPF CLIENTE Obrigatorio!");
		} else if (cliente.getEmail().equals("")) {
			JOptionPane.showMessageDialog(null, "E-MAIL CLIENTE Obrigatorio!");
		} else if (cliente.getTelefone().equals("")) {
			JOptionPane
					.showMessageDialog(null, "TELEFONE CLIENTE Obrigatorio!");
		} else {
			ClienteDAO.getInstace().insertCliente(cliente);
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
			ClienteDAO.getInstace().insertCliente(cliente);
		}
	}

	public void deleteCliente(Integer id) throws SQLException {
		ClienteDAO.getInstace().deleteCliente(id);
	}

	public ArrayList<Cliente> showAllClientes() throws ClassNotFoundException,
			SQLException {
		return ClienteDAO.getInstace().showAllClientes();
	}
}
