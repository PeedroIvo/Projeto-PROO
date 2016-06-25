package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
