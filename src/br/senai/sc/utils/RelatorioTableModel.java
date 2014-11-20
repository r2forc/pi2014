package br.senai.sc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.model.RelatorioStatus;

public class RelatorioTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final int COL_CLIENTE = 0;
	private static final int COL_STATUS = 1;
	private static final int COL_DATA = 2;
	private static final int COL_VALOR = 3;

	private List<RelatorioStatus> valores;

	// Esse é um construtor, que recebe a nossa lista de servicos
	public RelatorioTableModel(List<RelatorioStatus> valores) {
		this.valores = new ArrayList<RelatorioStatus>(valores);
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
		if (column == COL_CLIENTE)
			return "Cliente";
		if (column == COL_STATUS)
			return "Status";
		if (column == COL_DATA)
			return "Data";
		if (column == COL_VALOR)
			return "Valor";
		return ""; // Nunca deve ocorrer
	}

	public Object getValueAt(int row, int column) {
		// Precisamos retornar o valor da coluna column e da linha row.
		RelatorioStatus rs = valores.get(row);
		if (column == COL_CLIENTE)
			return rs.getCliente().getNome();
		else if (column == COL_STATUS)
			return rs.getStatus();
		else if (column == COL_DATA)
			return rs.getData();
		else if (column == COL_VALOR)
			return rs.getValor();
		return ""; // Nunca deve ocorrer
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		RelatorioStatus rs = valores.get(rowIndex);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		if (columnIndex == COL_CLIENTE)
			rs.getCliente().setNome(aValue.toString());
		else if (columnIndex == COL_STATUS)
			rs.setStatus(aValue.toString());
		else if (columnIndex == COL_DATA)
			try {
				rs.setData(sdf.parse(aValue.toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (columnIndex == COL_VALOR)
			rs.setValor(Double.parseDouble(aValue.toString()));

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
	public RelatorioStatus get(int row) {
		return valores.get(row);
	}
}
