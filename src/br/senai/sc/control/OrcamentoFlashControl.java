package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrcamentoDAO;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.dao.ServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.Orcamento;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.Servico;

public class OrcamentoFlashControl {


	public OrcamentoFlash showAllOrcamento() {
		return OrcamentoFlashDAO.getInstace().mostrarOrcamento();
	}
	
	public String mostrarCliente(){
		return OrcamentoFlashDAO.getInstace().mostrarCliente();
	}
	
	public void inserirCliente(Cliente cli){
		if(cli == null){
			JOptionPane.showMessageDialog(null, "Selecione um cliente");
		}else{
			OrcamentoFlashDAO.getInstace().insertCliente(cli);
		}
	}
	
	public static void inserirServico(Servico servico){
		OrcamentoFlashDAO.getInstace().InserirServico(servico);
	}
	
	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
	
	public ArrayList<Servico> showAllServicos()  {
		return OrcamentoFlashDAO.getInstace().mostrarServicos();
	}
	
	public void removerServico(int i){
		OrcamentoFlashDAO.getInstace().RemoverServico(i);
	}

	public void salvarNoBanco(Double valorTotal){
		try {
			OrcamentoFlashDAO.getInstace().SalvarNoBanco(valorTotal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


}
