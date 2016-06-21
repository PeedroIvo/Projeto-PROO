package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno extends Usuario {
	
	private int idade;
	private char sexo;
	private int codTurmaAtual;
	private DadosPessoais dadosPessoais = new DadosPessoais();

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getCodTurmaAtual() {
		return codTurmaAtual;
	}

	public void setCodTurmaAtual(int codTurmaAtual) {
		this.codTurmaAtual = codTurmaAtual;
	}
	
	public void criar (Connection conexao, Aluno aluno) {
		String sql = "insert into usuario (login, nome, tipoUsuario) values (?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, aluno.getLogin());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, "a");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "select codUsuario from usuario where login='" + aluno.getLogin() + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				aluno.setCodUsuario(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "insert into aluno (matricAluno, codTurmaAtual, idade, sexo) values (?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, aluno.getCodUsuario());
			stmt.setInt(2, aluno.getCodTurmaAtual());
			stmt.setInt(3, aluno.getIdade());
			stmt.setString(4, String.valueOf(aluno.getSexo()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		aluno.getDadosPessoais().criar(conexao, aluno.getCodUsuario(), aluno.getDadosPessoais());
	}
}
