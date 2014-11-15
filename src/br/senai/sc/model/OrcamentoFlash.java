package br.senai.sc.model;

import java.util.ArrayList;
import java.util.Date;

public class OrcamentoFlash extends OrdemServico {
	private Cliente cliente = new Cliente();
	private static ArrayList<Servico> servicos = new ArrayList<Servico>();
	private Date data;
	private Double valorTotal;

	public OrcamentoFlash() {
	}

	public OrcamentoFlash(Cliente cliente,
			Date data, Double valorTotal) {
		this.cliente = cliente;
		this.data = data;
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void inserirServico(Servico servicoAdd){
		this.servicos.add(servicoAdd);
	}
	
	public static Servico getServico(int posicao) {
		return servicos.get(posicao);
	}
	
	public static ArrayList<Servico>  getServicos() {
		return servicos;
	}

	public void setServicos(ArrayList<Servico> servico) {
		this.servicos = servicos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void removerServico(int i){
		this.servicos.remove(i);
	}

}
