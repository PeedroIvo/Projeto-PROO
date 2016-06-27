package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sei.Aluno;
import sei.Usuario;

public class AlunoCRUD extends UsuarioCRUD{
	private DadosPessoaisCRUD dadosCRUD = new DadosPessoaisCRUD();
	
	public void criar (Connection conexao, Aluno aluno) {
		this.criarUsuario(conexao, (Usuario)aluno);
		
		aluno.setCodUsuario(this.pegarCodigo(conexao, aluno.getLogin()));
		
		String sql = "insert into aluno (matricAluno, codTurmaAtual, idade, sexo) values (?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, aluno.getCodUsuario());
			stmt.setInt(2, aluno.getCodTurmaAtual());
			stmt.setInt(3, aluno.getIdade());
			stmt.setString(4, String.valueOf(aluno.getSexo()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dadosCRUD.criar(conexao, aluno.getCodUsuario(), aluno.getDadosPessoais());
	}
	
	public void apagar (Connection conexao, int cod) {
		try (PreparedStatement stmt = conexao.prepareStatement("delete from aluno where matricAluno='" + cod + "'");) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.dadosCRUD.apagar(conexao, cod);
		
		this.apagarUsuario(conexao, cod);
	}
	
	public String procuraNomeAluno(Connection conexao, int cod) {
		String sql = "select * from aluno, usuario where aluno.matricAluno='" + cod + "' and usuario.codUsuario='" + cod + "'";
		
		Aluno aluno = this.selectAluno(conexao, sql);
		
		if (aluno != null) {
			return aluno.getNome();
		}
		
		return null;
	}
	
	public Aluno procuraAluno(Connection conexao, int cod) {
		String sql = "select * from aluno, usuario where aluno.matricAluno='" + cod + "' and usuario.codUsuario='" + cod + "'";
		
		Aluno aluno = this.selectAluno(conexao, sql);
		
		if (aluno != null) {
			return aluno;
		}
		
		return null;
	}
	
	public Aluno selectAluno(Connection conexao, String sql) {
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				Aluno aluno = new Aluno();
				
				aluno.setCodUsuario(rs.getInt("codUsuario"));
				aluno.setDadosPessoais(dadosCRUD.procuraDadosPessoais(conexao, rs.getInt("codUsuario")));
				aluno.setLogin(rs.getString("login"));
				aluno.setNome(rs.getString("nome"));
				aluno.setSenha("senha");
				aluno.setTipoUsuario(rs.getString("tipoUsuario").charAt(0));
				aluno.setCodTurmaAtual(rs.getInt("codTurmaAtual"));
				aluno.setIdade(rs.getInt("idade"));
				aluno.setSexo(rs.getString("sexo").charAt(0));
				
				return aluno;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Aluno> listarPorTurma(Connection conexao, int codTurma) {
		String sql = "select * from aluno, usuario where aluno.matricAluno=usuario.codUsuario and aluno.codTurmaAtual='" + codTurma + "'";
		
		List<Aluno> alunos = this.selectListAluno(conexao, sql);
		
		return alunos;
	}
	
	public List<Aluno> selectListAluno(Connection conexao, String sql){
		List<Aluno> alunos = new ArrayList<>();
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if(rs.next()) {
				do {
					Aluno aluno = new Aluno();
					
					aluno.setCodUsuario(rs.getInt("codUsuario"));
					aluno.setDadosPessoais(dadosCRUD.procuraDadosPessoais(conexao, rs.getInt("codUsuario")));
					aluno.setLogin(rs.getString("login"));
					aluno.setNome(rs.getString("nome"));
					aluno.setSenha("senha");
					aluno.setTipoUsuario(rs.getString("tipoUsuario").charAt(0));
					aluno.setCodTurmaAtual(rs.getInt("codTurmaAtual"));
					aluno.setIdade(rs.getInt("idade"));
					aluno.setSexo(rs.getString("sexo").charAt(0));
					
					alunos.add(aluno);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alunos;
	}
}
