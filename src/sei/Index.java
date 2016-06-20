package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sei.database.ConexaoMySQL;

public class Index {

	public static void main(String[] args) {
		ConexaoMySQL mysql = new ConexaoMySQL();

		Connection conexao = mysql.getConexao("jdbc:mysql", "localhost", "sei", "root", "");
		
		String sql = "select * from usuario";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			
			while (rs.next()) {
				System.out.println(rs.getString("login"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mysql.fecharConexao();
	}
}
