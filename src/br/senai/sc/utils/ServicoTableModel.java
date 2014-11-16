package br.senai.sc.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.model.Servico;

public class ServicoTableModel extends AbstractTableModel {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private static final int COL_DESCRICAO = 0;
	private static final int COL_VALORUNT = 1;


	private List<Servico> valores;

	// Esse � um construtor, que recebe a nossa lista de servicos
	public ServicoTableModel(List<Servico> valores) {
		this.valores = new ArrayList<Servico>(valores);
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, s� 2.
		return 2;
	}

	public String getColumnName(int column) {
		// Qual � o nome das nossas colunas?
		if (column == COL_DESCRICAO)
			return "Descricao";
		if (column == COL_VALORUNT)
			return "Valor Unit�rio";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		Servico servico = valores.get(row);
		if (column == COL_DESCRICAO)
			return servico.getDescricao();
		else if (column == COL_VALORUNT)
			return servico.getValorUnt();
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Servico servico = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no par�metro.
		// Note que vc poderia alterar 2 campos ao inv�s de um s�.
		if (columnIndex == COL_DESCRICAO)
			servico.setDescricao(aValue.toString());
		else if (columnIndex == COL_VALORUNT)
			servico.setValorUnt(Double.parseDouble(aValue.toString()));
		
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, �
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a c�lula da rowIndex e da columnIndex � edit�vel. Nossa
		// tabela toda �.
		return false;
	}

	// J� que esse tableModel � de servicos, vamos fazer um get que retorne um
	// objeto servico inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Servico get(int row) {
		return valores.get(row);
	}
}