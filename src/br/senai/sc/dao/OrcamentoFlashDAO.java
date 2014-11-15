package br.senai.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




import com.mysql.jdbc.Statement;

import br.senai.sc.model.Cliente;
import br.senai.sc.model.ConnectionUtil;
import br.senai.sc.model.OrcamentoFlash;
import br.senai.sc.model.Servico;

public class OrcamentoFlashDAO {
	private static OrcamentoFlashDAO instance;
	private static OrcamentoFlash orcamentoFlash = new OrcamentoFlash();
	private Connection con = ConnectionUtil.getConnection();

	public static OrcamentoFlashDAO getInstace() {
		if (instance == null) {
			instance = new OrcamentoFlashDAO();
		}
		return instance;
	}
	
	public void insertCliente(Cliente cliente) {
		orcamentoFlash.setCliente(cliente);
	}
	
	public String mostrarCliente() {
		return orcamentoFlash.getCliente().getNome();
	}
	
	public ArrayList<Servico> mostrarServicos(){
		return orcamentoFlash.getServicos();
	}
	
	public OrcamentoFlash mostrarOrcamento(){
		return orcamentoFlash;
	}
	
	public void InserirServico(Servico serivo){
		orcamentoFlash.inserirServico(serivo);
	}
	
	public void RemoverServico(int i){
		orcamentoFlash.removerServico(i);
	}
	
	public void SalvarNoBanco(Double valorTotal) throws SQLException{
		try {
			String query = "INSERT INTO orcamento (data, cliente_id, usuario_id, descricao, valorTotal, status) "
					+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date data = new Date();
			stmt.setString(1, sdf.format(data));
			stmt.setInt(2, orcamentoFlash.getCliente().getId());
			stmt.setInt(3, 1);
			stmt.setString(4, "Descrição");
			stmt.setDouble(5, valorTotal);
			stmt.setInt(6, 0);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();  
			int id = 0;  
		    if(rs.next()){  
		        id = rs.getInt(1);  
		    }         
		  
		   Double total = 0.0;
		   for(int i = 0; i < orcamentoFlash.getServicos().size(); i++){
			   query = "INSERT INTO orcamento_has_servico (orcamento_id, servico_id, quantidadeOriginal, copias, valorTotal) "
						+ "values (?, ?, ?, ?, ?)";
			   stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			   stmt.setInt(1, id);
			   stmt.setInt(2, orcamentoFlash.getServicos().get(i).getId());
			   stmt.setInt(3, orcamentoFlash.getServicos() .get(i).getOriginais());
			   stmt.setInt(4, orcamentoFlash.getServicos().get(i).getCopias());
			   total = (orcamentoFlash.getServicos().get(i).getOriginais() * orcamentoFlash.getServicos().get(i).getCopias()) * orcamentoFlash.getServicos().get(i).getValorUnt();
			   stmt.setDouble(5, total);
			   stmt.execute();
		   }
		    
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}
	
}
