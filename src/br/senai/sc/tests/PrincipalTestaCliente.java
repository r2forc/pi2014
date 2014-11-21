package br.senai.sc.tests;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.senai.sc.control.ClienteControl;
import br.senai.sc.model.Cliente;

public class PrincipalTestaCliente {
	/**
	 * @wbp.parser.entryPoint
	 */
	// testando
	public static void main(String args[]) throws SQLException,
			ClassNotFoundException {
		Cliente cInsert = new Cliente();
		Cliente cEdit = new Cliente();
		ClienteControl cc = new ClienteControl();

		String opc = "";
		do {
			opc = JOptionPane.showInputDialog("1 - CLIENTE / 10 - SAIR");
			switch (opc) {

			case "1":
				opc = JOptionPane.showInputDialog("1 - INSERIR / "
						+ "2 - EDITAR / " + "3 - DELETAR / "
						+ "4 - LISTAR TODOS / " + "5 - LISTA C/ FILTRO");
				switch (opc) {
				case "1":
					// INSERT
					cInsert.setNome("TESTE NOME");
					cInsert.setCpf("TESTE CPF");
					cInsert.setEmail("TESTE EMAIL");
					cInsert.setTelefone("TESTE TELEFONE");
					cc.insertCliente(cInsert);
					System.out.println("INSERIDO com sucesso!");
					break;
				case "2":
					// EDIT
					cEdit.setNome("TROCA NOME");
					cEdit.setCpf("TROCA CPF");
					cEdit.setEmail("TROCA EMAIL");
					cEdit.setTelefone("TROCA TELEFONE");
					cEdit.setId(1);
					cc.editCliente(cEdit);
					System.out.println("EDITADO com sucesso!");
					break;
				case "3":
					// DELETE
					int cDel = Integer.parseInt(JOptionPane
							.showInputDialog("ID PARA SER DELETADO!"));
					cc.deleteCliente(cDel);
					System.out.println("DELETADO com sucesso!");
					break;
				case "4":
					// LISTAR
					for (Cliente cliente : cc.showAllClientes()) {
						System.out.println("ID: " + cliente.getId() + " Nome: "
								+ cliente.getNome() + " CPF: "
								+ cliente.getCpf() + " E-mail: "
								+ cliente.getEmail() + " Telefone: "
								+ cliente.getTelefone());
					}
				case "5":
					// LISTAR COM FILTRO
					String column = JOptionPane
							.showInputDialog("Nome coluna no BANCO DE DADOS");
					String value = JOptionPane
							.showInputDialog("VALOR a ser pesquisado no BANCO DE DADOS");

					for (Cliente cliente : cc.showFilterClientes(column, value, false)) {
						System.out.println("ID: " + cliente.getId() + " Nome: "
								+ cliente.getNome() + " CPF: "
								+ cliente.getCpf() + " E-mail: "
								+ cliente.getEmail() + " Telefone: "
								+ cliente.getTelefone());
					}
				}
			}
		} while (!opc.equals("10"));
	}
}
