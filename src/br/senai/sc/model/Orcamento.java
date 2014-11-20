package br.senai.sc.model;

import java.util.Date;

public class Orcamento extends OrdemServico {
	private Cliente cliente = new Cliente();
	private Servico servico = new Servico();
	private Integer id;
	private Date data;
	private String descricao;
	private Double valorTotal;
	private Integer quantidadeOriginal;
	private Integer copias;
	private String status;

	public Orcamento() {
	}

	public Orcamento(Cliente cliente, Servico servico, Integer id, Date data,
			String descricao, Double valorTotal, Integer quantidadeOriginal,
			Integer copias, String status) {
		this.cliente = cliente;
		this.servico = servico;
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.quantidadeOriginal = quantidadeOriginal;
		this.copias = copias;
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public Integer getQuantidadeOriginal() {
		return quantidadeOriginal;
	}

	public void setQuantidadeOriginal(Integer quantidadeOriginal) {
		this.quantidadeOriginal = quantidadeOriginal;
	}

	public Integer getCopias() {
		return copias;
	}

	public void setCopias(Integer copias) {
		this.copias = copias;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
