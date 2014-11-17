package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.dao.OrcamentoFlashDAO;
import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;
import br.senai.sc.model.Servico;

public class OrdemServicoControl {

	public void insertOrdemServico(OrdemServico os)
			throws ClassNotFoundException, SQLException {
		// if (os.getId() == null) {
		// print("ID ORCAMENTO Obrigatorio!");
		// xd
		if (os.getServico().getId() == null) {
			print("ID SERVIÇO Obrigatorio!");
		} else if (os.getQuantidadeOriginal() == null) {
			print("QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (os.getCopias() == null) {
			print("COPIAS Obrigatorio!");
		} else if (os.getValorTotal() == null) {
			print("VALOR TOTAL Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().insertOrdemServico(os);
		}
	}

	public static void inserirServico(Servico servico) {
		/*
		 * try { int id = servico.getId(); int size =
		 * OrdemServico.showAllServicos().size(); boolean contem = false; for
		 * (int i = 0; i < size; i++) { if
		 * (OrdemServicoControl.showServico(i).getId().equals(id)) contem =
		 * true; } if (contem) throw new
		 * SQLException("Serviço já adicionado a lista"); if
		 * (servico.getValorUnt() <= 0) throw new
		 * Exception("Valor unitário inválido"); if (servico.getCopias() <= 0)
		 * throw new Exception("Quantidade de cópias inválidas"); if
		 * (servico.getOriginais() <= 0) throw new
		 * Exception("Quantidade de copias inválidas");
		 * OrdemServico.getInstace().InserirServico(servico); } catch (Exception
		 * e) { JOptionPane.showMessageDialog(null, e.getMessage()); }
		 */
	}

	public static ArrayList<Servico> showAllServicos() {
		return OrdemServicoDAO.getInstace().mostrarServicos();
	}

	public static Servico showServico(int i) {
		return OrdemServicoDAO.getInstace().mostrarServico(i);
	}

	public void removerServico(int i) {
		OrdemServicoDAO.getInstace().RemoverServico(i);
	}

	public void destruirFlash() {
		Cliente novoCliente = new Cliente();
		OrdemServicoDAO.getInstace().insertCliente(novoCliente);
		int i = OrdemServicoDAO.getInstace().mostrarServicos().size() - 1;
		while (i != 0) {
			i = OrdemServicoDAO.getInstace().mostrarServicos().size() - 1;
			OrdemServicoDAO.getInstace().RemoverServico(i);
		}
	}

	public boolean salvarNoBanco(Double valorTotal, String descricao) {
		try {
			if (OrcamentoFlashDAO.getInstace().mostrarCliente() == null)
				throw new Exception("Selecione um Cliente");
			if (OrcamentoFlashDAO.getInstace().mostrarServicos().size() == 0)
				throw new Exception("Nenhum Serviço adicionado ao orçamento");
			if (descricao.equals(""))
				throw new Exception("Digite uma descrição do Orçamento");
			OrcamentoFlashDAO.getInstace().SalvarNoBanco(valorTotal, descricao);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	public ArrayList<OrdemServico> showItensServicoOrdemServicos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showItensServicoOrdemServicos();
	}

	public ArrayList<OrdemServico> showOrdemServicos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showOrdemServicos();
	}

	public ArrayList<OrdemServico> showFilterOS(String column, String value)
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showFilterOrdemServico(column,
				value);
	}

	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
}
