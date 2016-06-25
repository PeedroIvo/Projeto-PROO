package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
