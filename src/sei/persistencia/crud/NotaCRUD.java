package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotaCRUD {
	public void criar (Connection conexao, int matricAluno, int codDisciplina, int bimestre, double nota) {
		String sql = "insert into nota values (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, matricAluno);
			stmt.setInt(2, codDisciplina);
			stmt.setInt(3, 2016);
			stmt.setInt(4, bimestre);
			stmt.setDouble(5, nota);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
