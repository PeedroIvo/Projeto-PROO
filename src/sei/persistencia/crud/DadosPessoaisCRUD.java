package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sei.DadosPessoais;

public class DadosPessoaisCRUD {
	private EnderecoCRUD enderecoCRUD = new EnderecoCRUD();
	
	public void criar (Connection conexao, int cod, DadosPessoais dados) {
		String sql = "insert into dadosPessoais values (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, cod);
			stmt.setString(2, dados.getCpf());
			stmt.setString(3, dados.getRg());
			stmt.setString(4, dados.getEmail());
			stmt.setString(5, dados.getTelefone());
			stmt.setString(6, dados.getCelular());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.enderecoCRUD.criar(conexao, cod, dados.getEndereco());
	}
	
	public void apagar (Connection conexao, int cod) {
		this.enderecoCRUD.apagar(conexao, cod);
		
		try (PreparedStatement stmt = conexao.prepareStatement("delete from dadosPessoais where codUsuario='" + cod + "'");) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DadosPessoais procuraDadosPessoais(Connection conexao, int cod) {
		String sql = "select * from dadospessoais where codUsuario='" + cod + "'";
		
		DadosPessoais dados = this.selectDadosPessoais(conexao, sql);
		
		if (dados != null) {
			return dados;
		}
		
		return null;
	}
	
	public DadosPessoais selectDadosPessoais(Connection conexao, String sql) {
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				DadosPessoais dados = new DadosPessoais();
				
				dados.setCelular(rs.getString("celular"));
				dados.setCpf(rs.getString("cpf"));
				dados.setEmail(rs.getString("email"));
				dados.setEndereco(enderecoCRUD.procuraEndereco(conexao, rs.getInt("codUsuario")));
				dados.setRg("rg");
				dados.setTelefone("telefone");
								
				return dados;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
