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
	
	public boolean usuarioExiste(Connection conexao, String loginDigitado) {
		try (PreparedStatement stmt = conexao.prepareStatement("select * from usuario where login='" + loginDigitado + "'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void updateSenha(Connection conexao, String login, String novaSenha){
		String sql = "update usuario set senha = '" + novaSenha + "' where login='" + login + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
