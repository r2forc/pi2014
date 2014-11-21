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
	
	public String mostrarDescricao() {
		return orcamentoFlash.getDescricao();
	}
	
	public ArrayList<Servico> mostrarServicos(){
		return orcamentoFlash.getServicos();
	}
	
	public Servico mostrarServico(int i){
		return orcamentoFlash.getServico(i);
	}
	
	public OrcamentoFlash mostrarOrcamento(){
		return orcamentoFlash;
	}
	
	public void InserirServico(Servico serivo){
		orcamentoFlash.inserirServico(serivo);
	}
	
	public void InserirServicos(ArrayList<Servico> servico){
		orcamentoFlash.setServicos(null);
	}
	
	public void RemoverServico(int i){
		orcamentoFlash.removerServico(i);
	}
	
	public void SalvarNoBanco(Double valorTotal, String descricao) throws SQLException{
		try {
			String query = "INSERT INTO orcamento (data, cliente_id, usuario_id, descricao, valorTotal, status) "
					+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date data = new Date();
			stmt.setString(1, sdf.format(data));
			stmt.setInt(2, orcamentoFlash.getCliente().getId());
			stmt.setInt(3, 1);
			stmt.setString(4, descricao);
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
			   PreparedStatement stmt2 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			   stmt2.setInt(1, id);
			   stmt2.setInt(2, orcamentoFlash.getServicos().get(i).getId());
			   stmt2.setInt(3, orcamentoFlash.getServicos() .get(i).getOriginais());
			   stmt2.setInt(4, orcamentoFlash.getServicos().get(i).getCopias());
			   total = (orcamentoFlash.getServicos().get(i).getOriginais() * orcamentoFlash.getServicos().get(i).getCopias()) * orcamentoFlash.getServicos().get(i).getValorUnt();
			   stmt2.setDouble(5, total);
			   stmt2.execute();
		   }
		    
			con.commit();
			con.commit();
//			con.close();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
	}
	
}
