package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sei.Turma;

public class TurmaCRUD {
	private AlunoCRUD alunoCRUD = new AlunoCRUD();
	private DisciplinaCRUD disciplinaCRUD = new DisciplinaCRUD();
	
	public int procuraCodTurma(Connection conexao, int serie, char turno) throws SQLException {
		try (PreparedStatement stmt = conexao.prepareStatement("select codTurma from turma where serie='" + serie + "' and turno='" + turno + "'");
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				return rs.getInt("codTurma");
			}
		} catch (SQLException e) {
			throw new SQLException("Erro: "+e.getMessage());
		}
		
		return 0;
	}
	
	public Turma procuraTurma(Connection conexao, int cod) throws SQLException {
		String sql = "select * from turma where codTurma='" + cod + "'";
		
		Turma turma = this.selectTurma(conexao, sql);
		
		if (turma != null) {
			return turma;
		}
		
		return null;
	}
	
	public Turma selectTurma(Connection conexao, String sql) throws SQLException {
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				Turma turma = new Turma();
				
				turma.setAno(rs.getInt("ano"));
				turma.setCodTurma(rs.getInt("codTurma"));
				turma.setSala(rs.getString("sala"));
				turma.setSerie(rs.getInt("serie"));
				turma.setTurno(rs.getString("turno").charAt(0));
				turma.setAlunos(alunoCRUD.listarPorTurma(conexao, rs.getInt("codTurma")));
				turma.setDisciplinas(disciplinaCRUD.listarPorSerie(conexao, rs.getInt("serie")));
				
				return turma;
			}
		} catch (SQLException e) {
			throw new SQLException("Erro: "+e.getMessage());
		}
		
		return null;
	}
}
