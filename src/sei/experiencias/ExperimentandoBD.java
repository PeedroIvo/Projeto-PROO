package sei.experiencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sei.database.ConexaoMySQL;

public class ExperimentandoBD {
	
	public static void main(String[] args) {
		ConexaoMySQL mysql = new ConexaoMySQL();

		Connection conexao = mysql.getConexao("jdbc:mysql", "localhost:3306", "sei", "root", "");

		try (PreparedStatement stmt = conexao.prepareStatement("insert into usuario (login, senha, nome, tipoUsuario) values ('adm123', 'adm123', 'Administrador', 'a')");) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (PreparedStatement stmt = conexao.prepareStatement("select * from usuario");
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				System.out.println(rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (PreparedStatement stmt = conexao.prepareStatement("delete from usuario");) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mysql.fecharConexao();
	}

}
