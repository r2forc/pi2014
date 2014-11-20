package br.senai.sc.model;

import java.util.Date;

public class RelatorioStatus {
	private Cliente cliente = new Cliente();
	private String status;
	private Date data;
	private Double valor;

	public RelatorioStatus(Cliente cliente, String status, Date data,
			Double valor) {

		this.cliente = cliente;
		this.status = status;
		this.data = data;
		this.valor = valor;
	}

	public RelatorioStatus() {

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
