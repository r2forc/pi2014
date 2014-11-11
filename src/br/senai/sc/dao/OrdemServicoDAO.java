package br.senai.sc.dao;

import java.util.ArrayList;

import br.senai.sc.model.Orcamento;

public class OrdemServicoDAO {

	private static OrdemServicoDAO instance;
	private ArrayList<Orcamento> listaOrcamentos = new ArrayList<Orcamento>();

	public static OrdemServicoDAO getInstace() {
		if (instance == null) {
			instance = new OrdemServicoDAO();
		}
		return instance;
	}

	public void insertOrcamento(Orcamento orcamento) {
		this.listaOrcamentos.add(orcamento);
	}

	public void editCliente(Orcamento orcamento) {
		this.listaOrcamentos.set(verificaExistencia(orcamento), orcamento);
	}

	public void deleteOrcamento(Orcamento orcamento) {
		// this.listaClientes.remove(verificaExistencia(orcamento));
		this.listaOrcamentos.remove(orcamento);
	}

	public ArrayList<Orcamento> showAllOrcamentos() {
		return this.listaOrcamentos;
	}

	public Integer verificaExistencia(Orcamento orcamento) {
		for (int i = 0; i < this.listaOrcamentos.size(); i++) {
			if (orcamento.getId().equals(this.listaOrcamentos.get(i).getId())) {
				return i;
			}
		}
		return null;
	}
}
