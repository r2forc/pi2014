package br.senai.sc.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sc.dao.ClienteDAO;
import br.senai.sc.dao.OrdemServicoDAO;
import br.senai.sc.model.Cliente;
import br.senai.sc.model.OrdemServico;

public class OrdemServicoControl {

	public void insertOrdemServico(OrdemServico os)
			throws ClassNotFoundException, SQLException {
		// if (os.getId() == null) {
		// print("ID ORCAMENTO Obrigatorio!");
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

	public void editOrdemServico(OrdemServico os) {
		if (os.getId() == null) {
			print("ID ORCAMENTO Obrigatorio!");
		} else if (os.getData() == null) {
			print("DATA Obrigatoria!");
		} else if (os.getServico().getId() == null) {
			print("ID SERVIÇO Obrigatorio!");
		} else if (os.getCliente().getId() == null) {
			print("ID CLIENTE Obrigatorio!");
		} else if (os.getDescricao().equals("")) {
			print("DESCRICAO Obrigatorio!");
		} else if (os.getQuantidadeOriginal() == null) {
			print("QUANTIDADE ORIGINAL Obrigatorio!");
		} else if (os.getCopias() == null) {
			print("COPIAS Obrigatorio!");
		} else if (os.getValorTotal() == null) {
			print("VALOR TOTAL Obrigatorio!");
		} else if (os.getStatus() == null) {
			print("VALOR TOTAL Obrigatorio!");
		} else {
			OrdemServicoDAO.getInstace().editOrdemServico(os);
		}
	}

	public void deleteServicoOrdemServico(OrdemServico os) {
		OrdemServicoDAO.getInstace().deleteServicoOrdemServico(os);
	}

	public ArrayList<OrdemServico> showItensServicoOrdemServicos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showItensServicoOrdemServicos();
	}

	public ArrayList<OrdemServico> showOrdemServicos()
			throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showOrdemServicos();
	}

	public ArrayList<OrdemServico> showFilterClientes(String column,
			String value) throws ClassNotFoundException, SQLException {
		return OrdemServicoDAO.getInstace().showFilterOrdemServico(column,
				value);
	}

	public static String print(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		return msg;
	}
}
