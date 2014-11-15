package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.ValidaCpf;

public class ClienteControl {

	public boolean insertCliente(Cliente cliente) throws SQLException {
			try{
				if (cliente.getNome().equals(""))
					throw new Exception("Digite o campo Nome");
				
				ValidaCpf validacaoCPF = new ValidaCpf();
				if (validacaoCPF.isCPF(cliente.getCpf()) == false)
					throw new Exception("Digite um cpf válido");
				
				 Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
				 Matcher m = p.matcher(cliente.getEmail()); 
				
				if (m.find() == false)
					throw new Exception("Digite um Email válido");
				
				if (cliente.getTelefone().equals("(  )     -    "))	
					throw new Exception("Digite o campo Telefone");
				
				if (cliente.getTelefone().length() != 14)	
					throw new Exception("Digite o campo Telefone");
				
				ArrayList<Cliente> resultadoClientes = new ArrayList<Cliente>();
				resultadoClientes =  ClienteDAO.getInstace().showFilterClientes("cpf", cliente.getCpf());
				System.out.println(resultadoClientes.size());
				
				if(resultadoClientes.size() != 0){
					throw new Exception("CPF já cadastrado, digite um CPF válido");
				}
				ClienteDAO.getInstace().insertCliente(cliente);
				return true;

			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
				return false;
			}
	}

	public boolean editCliente(Cliente cliente) throws SQLException {
		try{
			if (cliente.getNome().equals(""))
				throw new Exception("Digite o campo Nome");
			
			ValidaCpf validacaoCPF = new ValidaCpf();
			if (validacaoCPF.isCPF(cliente.getCpf()) == false)
				throw new Exception("Digite um cpf válido");
			
			 Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
			 Matcher m = p.matcher(cliente.getEmail()); 
			
			if (m.find() == false)
				throw new Exception("Digite um Email válido");
			
			if (cliente.getTelefone().equals("(  )     -    "))	
				throw new Exception("Digite o campo Telefone");
			
			if (cliente.getTelefone().length() != 14)	
				throw new Exception("Digite o campo Telefone");
		
			ClienteDAO.getInstace().editCliente(cliente);
			return true;

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
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
