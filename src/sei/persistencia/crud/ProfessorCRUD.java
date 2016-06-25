package sei.persistencia.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sei.Professor;
import sei.Usuario;

public class ProfessorCRUD extends UsuarioCRUD{
	private DadosPessoaisCRUD dadosCRUD = new DadosPessoaisCRUD(); 
	
	public void criar (Connection conexao, Professor professor) {
		this.criarUsuario(conexao, (Usuario)professor);
		
		professor.setCodUsuario(this.pegarCodigo(conexao, professor.getLogin()));
		
		String sql = "insert into professor (codProfessor, dataAdmissao) values (?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, professor.getCodUsuario());
			stmt.setString(2, professor.getDataAdmissao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dadosCRUD.criar(conexao, professor.getCodUsuario(), professor.getDadosPessoais());
	}
}
