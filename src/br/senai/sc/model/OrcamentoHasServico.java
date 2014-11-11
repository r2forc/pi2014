package br.senai.sc.model;

public class OrcamentoHasServico {
	private Servico servico;
	private Orcamento orcamento;
	private Integer quantidadeOriginal;
	private Integer copias;
	private Double valorTotal;

	public OrcamentoHasServico() {
	}

	public OrcamentoHasServico(Servico servico, Orcamento orcamento,
			Integer quantidadeOriginal, Integer copias, Double valorTotal) {
		this.servico = servico;
		this.orcamento = orcamento;
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

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
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
