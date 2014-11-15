package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrcamentoDAO;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.dao.ServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.Servico;

public class OrcamentoFlashControl {

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

	public OrcamentoFlash showAllOrcamento() {
		return OrcamentoFlashDAO.getInstace().mostrarOrcamento();
	}
	
	public String mostrarCliente(){
		return OrcamentoFlashDAO.getInstace().mostrarCliente();
	}
	
	public void inserirCliente(Cliente cli){
		if(cli == null){
			JOptionPane.showMessageDialog(null, "Selecione um cliente");
		}else{
			OrcamentoFlashDAO.getInstace().insertCliente(cli);
		}
	}
	
	public static void inserirServico(Servico servico){
		OrcamentoFlashDAO.getInstace().InserirServico(servico);
	}
	
	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
	
	public ArrayList<Servico> showAllServicos()  {
		return OrcamentoFlashDAO.getInstace().mostrarServicos();
	}



}
