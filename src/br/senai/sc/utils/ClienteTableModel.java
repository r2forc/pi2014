package br.senai.sc.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.model.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_CPF = 1;
	private static final int COL_EMAIL = 2;
	private static final int COL_TELEFONE = 3;

	private List<Cliente> valores;

	// Esse é um construtor, que recebe a nossa lista de clientes
	public ClienteTableModel(List<Cliente> valores) {
		this.valores = new ArrayList<Cliente>(valores);
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, só 2.
		return 4;
	}

	public String getColumnName(int column) {
		// Qual é o nome das nossas colunas?
		if (column == COL_NOME)
			return "Nome";
		if (column == COL_CPF)
			return "CPF / CNPJ";
		if (column == COL_EMAIL)
			return "E-mail";
		if (column == COL_TELEFONE)
			return "Telefone";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		Cliente cliente = valores.get(row);
		if (column == COL_NOME)
			return cliente.getNome();
		else if (column == COL_CPF)
			return cliente.getCpf();
		else if (column == COL_EMAIL)
			return cliente.getEmail();
		else if (column == COL_TELEFONE)
			return cliente.getTelefone();
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		if (columnIndex == COL_NOME)
			cliente.setNome(aValue.toString());
		else if (columnIndex == COL_CPF)
			cliente.setCpf(aValue.toString());
		else if (columnIndex == COL_EMAIL)
			cliente.setEmail(aValue.toString());
		else if (columnIndex == COL_TELEFONE)
			cliente.setTelefone(aValue.toString());
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, é
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a célula da rowIndex e da columnIndex é editável. Nossa
		// tabela toda é.
		return false;
	}

	// Já que esse tableModel é de clientes, vamos fazer um get que retorne um
	// objeto cliente inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Cliente get(int row) {
		return valores.get(row);
	}
}