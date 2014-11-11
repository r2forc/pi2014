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
	private static final int COL_VALORUNITARIO = 1;
	private static final int COL_QUANTIDADE = 2;
	private static final int COL_VALORTOTAL = 3;

	private List<OrdemServico> valores;

	// Esse � um construtor, que recebe a nossa lista de clientes
	public OrdemServicoTableModel(List<OrdemServico> valores) {
		this.valores = new ArrayList<OrdemServico>(valores);
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem a tabela? Nesse exemplo, s� 2.
		return 4;
	}

	public String getColumnName(int column) {
		// Qual � o nome das nossas colunas?
		if (column == COL_DESCRICAO)
			return "Descri��o";
		if (column == COL_VALORUNITARIO)
			return "Valor Unit�rio";
		if (column == COL_QUANTIDADE)
			return "Quantidade";
		if (column == COL_VALORTOTAL)
			return "Valor Total";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		OrdemServico os = valores.get(row);
		if (column == COL_DESCRICAO)
			return os.getOrcamento().getServico()
					.getDescricao();
		else if (column == COL_VALORUNITARIO)
			return os.getOrcamento().getServico()
					.getValorUnt();
		else if (column == COL_QUANTIDADE)
			return os.getOrcamento().getQuantidadeOriginal();
		else if (column == COL_VALORTOTAL)
			return os.getValorTotal();
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		OrdemServico orcamento = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no par�metro.
		// Note que vc poderia alterar 2 campos ao inv�s de um s�.
		if (columnIndex == COL_DESCRICAO)
			orcamento.getOrcamento().getServico()
					.setDescricao(aValue.toString());
		else if (columnIndex == COL_VALORUNITARIO)
			orcamento.getOrcamento().getServico()
					.setValorUnt(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_QUANTIDADE)
			orcamento.getOrcamento().setQuantidadeOriginal(
					Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_VALORTOTAL)
			orcamento.setValorTotal(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, �
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// Indicamos se a c�lula da rowIndex e da columnIndex � edit�vel. Nossa
		// tabela toda �.
		return true;
	}

	// J� que esse tableModel � de clientes, vamos fazer um get que retorne um
	// objeto cliente inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public OrdemServico get(int row) {
		return valores.get(row);
	}
}