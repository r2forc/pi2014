package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrcamentoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.Servico;

public class OrcamentoControl {

	public void insertOrcamento(Orcamento orcamento) {
		if (orcamento.getId() == null) {
			print("ID ORCAMENTO Obrigatorio!");
		} else if (orcamento.getData() == null) {
			print("DATA Obrigatoria!");
		} else if (orcamento.getServico().getId() == null) {
			print("ID SERVIÇO Obrigatorio!");
		} else if (orcamento.getCliente().getId() == null) {
			print("ID CLIENTE Obrigatorio!");
		} else if (orcamento.getDescricao().equals("")) {
			print("DESCRICAO Obrigatorio!");
		} else if (orcamento.getQuantidadeOriginal() == null) {
			print("QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (orcamento.getCopias() == null) {
			print("COPIAS Obrigatorio!");
		} else if (orcamento.getValorTotal() == null) {
			print("VALOR TOTAL Obrigatorio!");
		} else if (orcamento.getStatus() == null) {
			print("VALOR TOTAL Obrigatorio!");
		}
		OrcamentoDAO.getInstace().insertOrcamento(orcamento);
	}

	public void editOrcamento(Orcamento orcamento) {
		if (orcamento.getId() == null) {
			print("ID ORCAMENTO Obrigatorio!");
		} else if (orcamento.getData() == null) {
			print("DATA Obrigatoria!");
		} else if (orcamento.getServico().getId() == null) {
			print("ID SERVIÇO Obrigatorio!");
		} else if (orcamento.getCliente().getId() == null) {
			print("ID CLIENTE Obrigatorio!");
		} else if (orcamento.getDescricao().equals("")) {
			print("DESCRICAO Obrigatorio!");
		} else if (orcamento.getQuantidadeOriginal() == null) {
			print("QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (orcamento.getCopias() == null) {
			print("COPIAS Obrigatorio!");
		} else if (orcamento.getValorTotal() == null) {
			print("VALOR TOTAL Obrigatorio!");
		} else if (orcamento.getStatus() == null) {
			print("VALOR TOTAL Obrigatorio!");
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

	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
}
