package br.senai.sc.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.model.OrdemServico;

public class OrdemServicoTableModel extends AbstractTableModel {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private static final int COL_DESCRICAO = 0;
	private static final int COL_VALORUNT = 1;
	private static final int COL_QUANTIDADEORIGINAL = 2;
	private static final int COL_COPIAS = 3;
	private static final int COL_VALORTOTAL = 4;

	private List<OrdemServico> valores;

	// Esse é um construtor, que recebe a nossa lista de servicos
	public OrdemServicoTableModel(List<OrdemServico> valores) {
		this.valores = new ArrayList<OrdemServico>(valores);
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, só 2.
		return 5;
	}

	public String getColumnName(int column) {
		// Qual é o nome das nossas colunas?
		if (column == COL_DESCRICAO)
			return "Descricao";
		if (column == COL_VALORUNT)
			return "Valor Unitário";
		if (column == COL_QUANTIDADEORIGINAL)
			return "Quantidade Original";
		if (column == COL_COPIAS)
			return "Cópias";
		if (column == COL_VALORTOTAL)
			return "Valor Total";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		OrdemServico os = valores.get(row);
		if (column == COL_DESCRICAO)
			return os.getServico().getDescricao();
		else if (column == COL_VALORUNT)
			return os.getServico().getValorUnt();
		else if (column == COL_QUANTIDADEORIGINAL)
			return os.getQuantidadeOriginal();
		else if (column == COL_COPIAS)
			return os.getCopias();
		else if (column == COL_VALORTOTAL)
			return os.getServico().getValorUnt();
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		OrdemServico os = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		if (columnIndex == COL_DESCRICAO)
			os.getServico().setDescricao(aValue.toString());
		else if (columnIndex == COL_VALORUNT)
			os.getServico().setValorUnt(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_QUANTIDADEORIGINAL)
			os.setQuantidadeOriginal(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_COPIAS)
			os.setCopias(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_VALORTOTAL)
			os.setValorTotal(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, é
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a célula da rowIndex e da columnIndex é editável. Nossa
		// tabela toda é.
		return true;
	}

	// Já que esse tableModel é de servicos, vamos fazer um get que retorne um
	// objeto servico inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public OrdemServico get(int row) {
		return valores.get(row);
	}
}