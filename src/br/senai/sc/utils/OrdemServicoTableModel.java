package br.senai.sc.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.Orcamento;

public class OrdemServicoTableModel extends AbstractTableModel {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private static final int COL_DESCRICAO = 0;
	private static final int COL_VALORUNITARIO = 1;
	private static final int COL_QUANTIDADE = 2;
	private static final int COL_VALORTOTAL = 3;

	private List<Orcamento> valores;

	// Esse é um construtor, que recebe a nossa lista de clientes
	public OrdemServicoTableModel(List<Orcamento> valores) {
		this.valores = new ArrayList<Orcamento>(valores);
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
		if (column == COL_DESCRICAO)
			return "Descrição";
		if (column == COL_VALORUNITARIO)
			return "Valor Unitário";
		if (column == COL_QUANTIDADE)
			return "Quantidade";
		if (column == COL_VALORTOTAL)
			return "Valor Total";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		Orcamento orcamento = valores.get(row);
		if (column == COL_DESCRICAO)
			return orcamento.getOrcamentoHasServico().getServico()
					.getDescricao();
		else if (column == COL_VALORUNITARIO)
			return orcamento.getOrcamentoHasServico().getServico()
					.getValorUnt();
		else if (column == COL_QUANTIDADE)
			return orcamento.getOrcamentoHasServico().getQuantidadeOriginal();
		else if (column == COL_VALORTOTAL)
			return orcamento.getValorTotal();
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Orcamento orcamento = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		if (columnIndex == COL_DESCRICAO)
			orcamento.getOrcamentoHasServico().getServico()
					.setDescricao(aValue.toString());
		else if (columnIndex == COL_VALORUNITARIO)
			orcamento.getOrcamentoHasServico().getServico()
					.setValorUnt(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_QUANTIDADE)
			orcamento.getOrcamentoHasServico().setQuantidadeOriginal(
					Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_VALORTOTAL)
			orcamento.setValorTotal(Double.parseDouble(aValue.toString()));
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

	// Já que esse tableModel é de clientes, vamos fazer um get que retorne um
	// objeto cliente inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Orcamento get(int row) {
		return valores.get(row);
	}
}