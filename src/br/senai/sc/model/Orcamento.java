package br.senai.sc.model;

public class Orcamento {
	private Servico servico;
	private OrdemServico ordemServico;
	private Integer quantidadeOriginal;
	private Integer copias;
	private Double valorTotal;

	public Orcamento() {
	}

	public Orcamento(Servico servico, OrdemServico ordemServico,
			Integer quantidadeOriginal, Integer copias, Double valorTotal) {
		this.servico = servico;
		this.ordemServico = ordemServico;
		this.quantidadeOriginal = quantidadeOriginal;
		this.copias = copias;
		this.valorTotal = valorTotal;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
