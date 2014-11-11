package br.senai.sc.model;

import java.util.Date;

public class Orcamento {
	private Cliente cliente;
	private OrcamentoHasServico orcamentoHasServico;
	private Integer id;
	private Date data;
	private String descricao;
	private Double valorTotal;
	private Integer status;

	public Orcamento() {
	}

	public Orcamento(Cliente cliente, OrcamentoHasServico orcamentoHasServico,
			Integer id, Date data, String descricao, Double valorTotal,
			Integer status) {
		this.cliente = cliente;
		this.orcamentoHasServico = orcamentoHasServico;
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public OrcamentoHasServico getOrcamentoHasServico() {
		return orcamentoHasServico;
	}

	public void setOrcamentoHasServico(OrcamentoHasServico orcamentoHasServico) {
		this.orcamentoHasServico = orcamentoHasServico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
