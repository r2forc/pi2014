package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.utils.ValidaCnpj;
import br.senai.sc.utils.ValidaCpf;

public class ClienteControl {

	public boolean insertCliente(Cliente cliente) throws SQLException {
			try{
				if (cliente.getNome().equals("") || cliente.getNome().length() > 45 || cliente.getNome().length() < 3)
					throw new Exception("Digite um nome Válido");
				
				ValidaCpf validacaoCPF = new ValidaCpf();
				ValidaCnpj validacaoCnpj = new ValidaCnpj();
				
				if(cliente.getCpf().length() == 14){
					if (validacaoCPF.isCPF(cliente.getCpf()) == false)
						throw new Exception("Digite um CPF válido");
				}else{
					if (validacaoCnpj.isCNPJ(cliente.getCpf()) == false)
						throw new Exception("Digite um CNPJ válido");
				}
				
				 Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
				 Matcher m = p.matcher(cliente.getEmail()); 
				
				if (m.find() == false)
					throw new Exception("Digite um Email válido");
				
				if (cliente.getTelefone().equals("(  )     -    "))	
					throw new Exception("Digite o campo Telefone");
				
				if (cliente.getTelefone().length() != 14)	
					throw new Exception("Digite o campo Telefone");
				
				ArrayList<Cliente> resultadoClientesAtivos = new ArrayList<Cliente>();
				ArrayList<Cliente> resultadoClientesInativos = new ArrayList<Cliente>();
				resultadoClientesAtivos =  ClienteDAO.getInstace().showFilterClientes("cpf", cliente.getCpf(), 0);
				resultadoClientesInativos =  ClienteDAO.getInstace().showFilterClientes("cpf", cliente.getCpf(), 1);
				if(resultadoClientesAtivos.size() != 0 || resultadoClientesInativos.size() != 0)
					throw new Exception("CPF já cadastrado, digite um CPF válido");
				
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

	public void deleteCliente(Integer id) throws SQLException, ClassNotFoundException {
		ClienteDAO.getInstace().deleteCliente(id);
		ClienteDAO.getInstace().showFilterClientes("nome", "", 0);
	}
	public void restauraCliente(Integer id) throws SQLException, ClassNotFoundException {
		ClienteDAO.getInstace().restauraCliente(id);
		ClienteDAO.getInstace().showFilterClientes("nome", "", 1);
	}

	public ArrayList<Cliente> showAllClientes() throws ClassNotFoundException,
			SQLException {
		return ClienteDAO.getInstace().showAllClientes();
	}

	public ArrayList<Cliente> showFilterClientes(String column, String value, boolean excluidos)
			throws ClassNotFoundException, SQLException {

		int exc =  excluidos ?  1 :  0;
		column = column.equals("CPF / CNPJ") ? "CPF" : column;
		return ClienteDAO.getInstace().showFilterClientes(column, value, exc);
		
	}
}
