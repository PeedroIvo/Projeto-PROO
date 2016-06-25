package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sei.Usuario;

public class UsuarioCRUD {
	public void criarUsuario(Connection conexao, Usuario usuario) {
		String sql = "insert into usuario (login, nome, tipoUsuario) values (?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, String.valueOf(usuario.getTipoUsuario()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void apagarUsuario(Connection conexao, int cod) {
		try (PreparedStatement stmt = conexao.prepareStatement("delete from usuario where codUsuario='" + cod + "'");) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int pegarCodigo(Connection conexao, String login) {
		String sql = "select codUsuario from usuario where login='" + login + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
