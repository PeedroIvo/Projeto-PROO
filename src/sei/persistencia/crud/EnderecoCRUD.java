package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sei.Endereco;

public class EnderecoCRUD {
	public void criar(Connection conexao, int cod, Endereco endereco) {
		String sql = "insert into endereco values (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, cod);
			stmt.setString(2, endereco.getCep());
			stmt.setString(3, endereco.getRua());
			stmt.setString(4, endereco.getnCasa());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setString(7, endereco.getCidade());
			stmt.setString(8, endereco.getEstado());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void apagar (Connection conexao, int cod) {
		try (PreparedStatement stmt = conexao.prepareStatement("delete from endereco where codUsuario='" + cod + "'");) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Endereco procuraEndereco(Connection conexao, int cod) {
		String sql = "select * from endereco where codUsuario='" + cod + "'";
		
		Endereco endereco = this.selectEndereco(conexao, sql);
		
		if (endereco != null) {
			return endereco;
		}
		
		return null;
	}
	
	public Endereco selectEndereco(Connection conexao, String sql) {
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				Endereco endereco = new Endereco();
				
				endereco.setBairro("bairro");
				endereco.setCep("cep");
				endereco.setCidade("cidade");
				endereco.setComplemento("complemento");
				endereco.setEstado("estado");
				endereco.setnCasa("nCasa");
				endereco.setRua("rua");
								
				return endereco;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
