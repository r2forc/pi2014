package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrcamentoDAO;
import br.senai.sc.model.Orcamento;

public class OrcamentoControl {
	public void insertOrcamento(Orcamento orcamento) {
		if (orcamento.getOrdemServico().getId() == null) {
			JOptionPane
					.showMessageDialog(null, "ID ORDEM SERVICO Obrigatorio!");
		} else if (orcamento.getServico().getId() == null) {
			JOptionPane.showMessageDialog(null, "ID SERVICO Obrigatorio!");
		} else if (orcamento.getQuantidadeOriginal() == null) {
			JOptionPane.showMessageDialog(null,
					"QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (orcamento.getCopias() == null) {
			JOptionPane.showMessageDialog(null, "COPIAS Obrigatorio!");
		} else if (orcamento.getValorTotal() == null) {
			JOptionPane.showMessageDialog(null, "VALOR TOTAL Obrigatorio!");
		} else {
			OrcamentoDAO.getInstace().insertOrcamento(orcamento);
		}
	}

	public void editOrcamento(Orcamento orcamento) {
		if (orcamento.getOrdemServico().getId() == null) {
			JOptionPane
					.showMessageDialog(null, "ID ORDEM SERVICO Obrigatorio!");
		} else if (orcamento.getServico().getId() == null) {
			JOptionPane.showMessageDialog(null, "ID SERVICO Obrigatorio!");
		} else if (orcamento.getQuantidadeOriginal() == null) {
			JOptionPane.showMessageDialog(null,
					"QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (orcamento.getCopias() == null) {
			JOptionPane.showMessageDialog(null, "COPIAS Obrigatorio!");
		} else if (orcamento.getValorTotal() == null) {
			JOptionPane.showMessageDialog(null, "VALOR TOTAL Obrigatorio!");
		} else {
			OrcamentoDAO.getInstace().editOrcamento(orcamento);
		}
	}

	public void deleteOrcamento(Orcamento orcamento) {
		OrcamentoDAO.getInstace().deleteOrcamento(orcamento);
	}

	public ArrayList<Orcamento> showAllOrcamento()
			throws ClassNotFoundException, SQLException {
		return OrcamentoDAO.getInstace().showAllOrcamentos();
	}
}
