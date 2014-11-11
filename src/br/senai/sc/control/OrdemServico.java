package br.senai.sc.control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.Orcamento;

public class OrdemServico {

	public void insertOrcamento(Orcamento orcamento) {
		if (orcamento.getId() == null) {
			JOptionPane.showMessageDialog(null, "ID ORÇAMENTO Obrigatorio!");
		} else if (orcamento.getCliente().getId() == null) {
			JOptionPane.showMessageDialog(null, "ID CLIENTE Obrigatorio!");
		} else if (orcamento.getOrcamentoHasServico().getServico().getId()
				.equals("")) {
			JOptionPane.showMessageDialog(null, "ID SERVIÇO Obrigatorio!");
		} else if (orcamento.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "E-MAIL CLIENTE Obrigatorio!");
		} else if (orcamento.getData() == null) {
			JOptionPane.showMessageDialog(null, "DATA ORÇAMENTO Obrigatorio!");
		} else if (orcamento.getValorTotal() == null) {
			JOptionPane.showMessageDialog(null,
					"VALOR TOTAL ORÇAMENTO Obrigatorio!");
		} else if (orcamento.getStatus() == null) {
			JOptionPane
					.showMessageDialog(null, "STATUS ORÇAMENTO Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().insertOrcamento(orcamento);
		}
	}

	public void editOrcamento(Orcamento orcamento) {
		if (orcamento.getCliente().getId() == null) {
			JOptionPane.showMessageDialog(null, "ID CLIENTE Obrigatorio!");
		} else if (orcamento.getOrcamentoHasServico().getServico().getId()
				.equals("")) {
			JOptionPane.showMessageDialog(null, "ID SERVICO Obrigatorio!");
		} else if (orcamento.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "E-MAIL CLIENTE Obrigatorio!");
		} else if (orcamento.getData() == null) {
			JOptionPane.showMessageDialog(null, "DATA ORÇAMENTO Obrigatorio!");
		} else if (orcamento.getValorTotal() == null) {
			JOptionPane.showMessageDialog(null,
					"VALOR TOTAL ORÇAMENTO Obrigatorio!");
		} else if (orcamento.getStatus() == null) {
			JOptionPane
					.showMessageDialog(null, "STATUS ORÇAMENTO Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().insertOrcamento(orcamento);
		}
	}

	public void deleteOrcamento(Orcamento orcamento) {
		OrdemServicoDAO.getInstace().deleteOrcamento(orcamento);
	}

	public ArrayList<Orcamento> showAllOrcamentos() {
		return OrdemServicoDAO.getInstace().showAllOrcamentos();
	}
}
