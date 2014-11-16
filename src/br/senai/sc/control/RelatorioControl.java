package br.senai.sc.control;

import java.util.ArrayList;

import br.senai.sc.model.Orcamento;

public class RelatorioControl {
	public ArrayList<Orcamento> showAllOrcamentos(){
		return RelatorioDao.getInstace().showFilterOcamento(value);
	}
}
