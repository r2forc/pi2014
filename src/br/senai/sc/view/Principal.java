package br.senai.sc.view;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.model.Cliente;

public class Principal {
	public static void main(String args[]) throws SQLException {
		String opc = JOptionPane.showInputDialog("1 - CLIENTE");
		do {
			switch (opc) {
			case "1":
				Cliente c = new Cliente();
				ClienteControl cc = new ClienteControl();
				opc = JOptionPane.showInputDialog("1 - INSERIR / "
						+ "2 - EDITAR / " + "3 - DELETAR / " + "4 - EXCLUIR / "
						+ "5 - LISTAR TODOS / "
						+ "6 - LISTA C/ FILTRO / 7 - SAIR" + " ");
				switch (opc) {
				case "1":
					c.setNome("TESTE NOME");
					c.setCpf("TESTE CPF");
					c.setEmail("TESTE EMAIL");
					c.setTelefone("TESTE TELEFONE");
					cc.insertCliente(c);
					break;
				}
				break;
			}
		} while (opc.equals("7"));
	}
}
