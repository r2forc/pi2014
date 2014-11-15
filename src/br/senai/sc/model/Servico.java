package br.senai.sc.model;

public class Servico {
	private Integer id;
	private String descricao;
	private Double valorUnt;
	private int originais;
	private int copias;

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
	
	public int getOriginais() {
		return originais;
	}

	public void setOriginais(int originais) {
		this.originais = originais;
	}

	public int getCopias() {
		return copias;
	}

	public void setCopias(int copias) {
		this.copias = copias;
	}

	public void setValorUnt(Double valorUnt) {
		this.valorUnt = valorUnt;
	}

	@Override
	public String toString() {
		return descricao + " R$: " + valorUnt + " UND";
	}
}
