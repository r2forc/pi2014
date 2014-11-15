package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.control.OrcamentoFlashControl;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;

public class OrcamentoFlashDAO {
	private static OrcamentoFlashDAO instance;
	private OrcamentoFlash orcamentoFlash = new OrcamentoFlash();

	public static OrcamentoFlashDAO getInstace() {
		if (instance == null) {
			instance = new OrcamentoFlashDAO();
		}
		return instance;
	}

	public void insertCliente(Cliente cliente) {
		orcamentoFlash.setCliente(cliente);
	}
	
	public String mostrarCliente() {
		return orcamentoFlash.getCliente().getNome();
	}
	
	public ArrayList<Servico> mostrarServicos(){
		return orcamentoFlash.getServicos();
	}
	
	public OrcamentoFlash mostrarOrcamento(){
		return orcamentoFlash;
	}
	
	public void editOrcamento(Orcamento orcamento) {
	}

	public void deleteOrcamento(Orcamento orcamento) {
	}

	public void excluir(OrdemServico produto) {
	}
	

}
