package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.model.Cliente;
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
		try{
			int id =  servico.getId();
			int size = OrcamentoFlashControl.showAllServicos().size();
			boolean contem = false;
			for(int i = 0; i < size; i++){
				if(OrcamentoFlashControl.showServico(i).getId().equals(id))
					contem = true;	
			}
			if(contem)
				throw new SQLException("Serviço já adicionado a lista");
			if(servico.getValorUnt() <= 0 )
				throw new Exception("Valor unitário inválido");
			if(servico.getCopias() <= 0)
				throw new Exception("Quantidade de cópias inválidas");
			if(servico.getOriginais() <= 0)
				throw new Exception("Quantidade de copias inválidas");
			OrcamentoFlashDAO.getInstace().InserirServico(servico);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	}
	
	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
	
	public static ArrayList<Servico> showAllServicos()  {
		return OrcamentoFlashDAO.getInstace().mostrarServicos();
	}
	
	public static Servico showServico(int i)  {
		return OrcamentoFlashDAO.getInstace().mostrarServico(i);
	}
	
	public void removerServico(int i){
		OrcamentoFlashDAO.getInstace().RemoverServico(i);
	}
	
	public void destruirFlash(){
		Cliente novoCliente = new Cliente();
		OrcamentoFlashDAO.getInstace().insertCliente(novoCliente);
		int i = OrcamentoFlashDAO.getInstace().mostrarServicos().size()-1;
		while( i != 0){
			i = OrcamentoFlashDAO.getInstace().mostrarServicos().size()-1;
			OrcamentoFlashDAO.getInstace().RemoverServico(i);
		}
		
	}
	
	public boolean salvarNoBanco(Double valorTotal, String descricao){
		try {
			if(OrcamentoFlashDAO.getInstace().mostrarCliente() == null)
				throw new Exception("Selecione um Cliente");
			if(OrcamentoFlashDAO.getInstace().mostrarServicos().size() == 0)
				throw new Exception("Nenhum Serviço adicionado ao orçamento");
			if(descricao.equals(""))
				throw new Exception("Digite uma descrição do Orçamento");
			OrcamentoFlashDAO.getInstace().SalvarNoBanco(valorTotal, descricao);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
}
