package br.senai.sc.model;

public class Servico {
	private Integer id;
	private String descricao;
	private Double valorUnt;

	public Servico() {
	}

	public Servico(Integer id, String descricao, Double valorUnt) {
		this.id = id;
		this.descricao = descricao;
		this.valorUnt = valorUnt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorUnt() {
		return valorUnt;
	}

	public void setValorUnt(Double valorUnt) {
		this.valorUnt = valorUnt;
	}

	@Override
	public String toString() {
		return descricao + " R$: " + valorUnt + " UND";
	}
}
