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
	
	public void insertServico(Servico servico) throws SQLException {
		if (servico.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "Descrição Obrigatorio!");
		} else if (servico.getValorUnt().equals("")) {
			JOptionPane.showMessageDialog(null, "Valor Unitário Obrigatorio!");
		}  else {
			ServicoDAO.getInstace().insertServico(servico);
		}
	}

	public void editServico(Servico servico) throws SQLException {
		if (servico.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "Descrição Obrigatorio!");
		} else if (servico.getValorUnt().equals("")) {
			JOptionPane.showMessageDialog(null, "Valor Unitário Obrigatorio!");
		}  else {
			ServicoDAO.getInstace().editServico(servico);
		}
	}
}
