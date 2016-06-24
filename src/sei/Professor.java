package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends Usuario {
	private String dataAdmissao;
	private DadosPessoais dadosPessoais = new DadosPessoais();

	public String getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(String dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}
	
	public void criar (Connection conexao, Professor professor) {
		String sql = "insert into usuario (login, nome, tipoUsuario) values (?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, professor.getLogin());
			stmt.setString(2, professor.getNome());
			stmt.setString(3, "p");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "select codUsuario from usuario where login='" + professor.getLogin() + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				professor.setCodUsuario(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "insert into professor (codProfessor, dataAdmissao) values (?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, professor.getCodUsuario());
			stmt.setString(2, professor.getDataAdmissao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		professor.getDadosPessoais().criar(conexao, professor.getCodUsuario(), professor.getDadosPessoais());
	}
}
