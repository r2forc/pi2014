package br.senai.sc.utils;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.Servico;

public class OrcamentoFlashTableModel extends AbstractTableModel {
	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private static final int COL_DESCRICAO = 0;
	private static final int COL_VALORUNT = 1;
	private static final int COL_ORIGINAIS = 2;
	private static final int COL_COPIAS = 3;
	private static final int COL_VALORTOAL = 4;
	
	private OrcamentoFlash valores;

	// Esse é um construtor, que recebe a nossa lista de servicos
	public OrcamentoFlashTableModel(OrcamentoFlash valores) {
		this.valores = valores;
	}

	public int getRowCount() {
		// Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.getServicos().size();
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
		if (column == COL_ORIGINAIS)
			return "Originais";
		if (column == COL_COPIAS)
			return "Cópias";
		if (column == COL_VALORTOAL)
			return "Valor Total";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		//Servico orcamentoFlash = valores.getServicos().get(row);
		OrcamentoFlashDAO of = new OrcamentoFlashDAO();
		
		if (column == COL_DESCRICAO)
			return of.getInstace().mostrarServico(row).getDescricao();
		else if (column == COL_VALORUNT)
			return of.getInstace().mostrarServico(row).getValorUnt();
		else if (column == COL_ORIGINAIS)
			return of.getInstace().mostrarServico(row).getOriginais();
		else if (column == COL_COPIAS)
			return of.getInstace().mostrarServico(row).getCopias();
		else if (column == COL_VALORTOAL)
			return ((of.getInstace().mostrarServico(row).getCopias() * of.getInstace().mostrarServico(row).getOriginais()) * of.getInstace().mostrarServico(row).getValorUnt());
		
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Servico orcamentoFlash = valores.getServicos().get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		OrcamentoFlash of = new OrcamentoFlash();
		if (columnIndex == COL_DESCRICAO)
			of.getServico(rowIndex).setDescricao(aValue.toString());
		else if (columnIndex == COL_VALORUNT)
			of.getServico(rowIndex).setValorUnt(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_ORIGINAIS)
			of.getServico(rowIndex).setOriginais(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_COPIAS)
			of.getServico(rowIndex).setCopias(Integer.parseInt(aValue.toString()));
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

	// Já que esse tableModel é de servicos, vamos fazer um get que retorne um
	// objeto servico inteiro.
	// Isso elimina a necessidade de chamar o getValueAt() nas telas.
	public Servico get(int row) {
		return valores.getServicos().get(row);
	}
}