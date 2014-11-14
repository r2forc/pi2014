package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.OrdemServico;

public class OrcamentoFlashDAO {
	private static OrcamentoFlashDAO instance;
	private Orcamento orcamentoFlash = new Orcamento();

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

	public void editOrcamento(Orcamento orcamento) {
	}

	public void deleteOrcamento(Orcamento orcamento) {
	}

	public void excluir(OrdemServico produto) {
	}
}
