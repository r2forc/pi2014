package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ServicoDAO;
import br.senai.sc.model.Servico;

public class ServicoControl {
	public ArrayList<Servico> showAllServicos() throws ClassNotFoundException,
			SQLException {
		return ServicoDAO.getInstace().showAllServicos();
	}
	public ArrayList<Servico> showFilterServicos(String value)
			throws ClassNotFoundException, SQLException {
		return ServicoDAO.getInstace().showFilterServicos(value);
	}
	
	public void deleteServico(Integer id) throws SQLException {
		ServicoDAO.getInstace().deleteServico(id);
	}
	
	public boolean insertServico(Servico servico) throws SQLException {
		try{
			if (servico.getDescricao().equals("") || servico.getDescricao().length() < 3 || servico.getDescricao().length() > 45) 
				throw new Exception("Descri��o inv�lida");
			if (servico.getValorUnt().equals("") || servico.getValorUnt().equals("0.0"))
				throw new NumberFormatException("Valor inv�lido");
			ServicoDAO.getInstace().insertServico(servico);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	public boolean editServico(Servico servico) throws SQLException {
		try{
			if (servico.getDescricao().equals("") || servico.getDescricao().length() < 3 || servico.getDescricao().length() > 45) 
				throw new Exception("Descri��o inv�lida");
			if (servico.getValorUnt().equals("") || servico.getValorUnt().equals("0.0"))
				throw new NumberFormatException("Valor inv�lido");
		
			ServicoDAO.getInstace().editServico(servico);
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
}
