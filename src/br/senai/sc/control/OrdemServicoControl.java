package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.OrdemServico;

public class OrdemServicoControl {

	public void insertOrcamento(OrdemServico os) {
		if (os.getId() == null) {
			JOptionPane.showMessageDialog(null, "ID ORCAMENTO Obrigatorio!");
		} else if (os.getCliente().getId() == null) {
			JOptionPane.showMessageDialog(null,
					"ID ORDEM DE SERVICO Obrigatorio!");
		} else if (os.getOrcamento().getServico().getId().equals("")) {
			JOptionPane.showMessageDialog(null, "ID SERVIÇO Obrigatorio!");
		} else if (os.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "E-MAIL CLIENTE Obrigatorio!");
		} else if (os.getData() == null) {
			JOptionPane.showMessageDialog(null, "DATA ORÇAMENTO Obrigatorio!");
		} else if (os.getValorTotal() == null) {
			JOptionPane.showMessageDialog(null,
					"VALOR TOTAL ORÇAMENTO Obrigatorio!");
		} else if (os.getStatus() == null) {
			JOptionPane
					.showMessageDialog(null, "STATUS ORÇAMENTO Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().insertOrcamento(os);
		}
	}

	public void editOrcamento(OrdemServico os) {
		if (os.getCliente().getId() == null) {
			JOptionPane.showMessageDialog(null,
					"ID ORDEM DE SERVICO Obrigatorio!");
		} else if (os.getOrcamento().getServico().getId().equals("")) {
			JOptionPane.showMessageDialog(null, "ID SERVICO Obrigatorio!");
		} else if (os.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "E-MAIL CLIENTE Obrigatorio!");
		} else if (os.getData() == null) {
			JOptionPane.showMessageDialog(null, "DATA ORÇAMENTO Obrigatorio!");
		} else if (os.getValorTotal() == null) {
			JOptionPane.showMessageDialog(null,
					"VALOR TOTAL ORÇAMENTO Obrigatorio!");
		} else if (os.getStatus() == null) {
			JOptionPane
					.showMessageDialog(null, "STATUS ORÇAMENTO Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().insertOrcamento(os);
		}
	}

	public void deleteOrcamento(OrdemServico os) {
		OrdemServicoDAO.getInstace().deleteOrcamento(os);
	}

	public ArrayList<OrdemServico> showAllOrcamentos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showAllOrdemServicos();
	}
}
