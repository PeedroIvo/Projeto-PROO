package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DadosPessoais {
	private String cpf;
	private String rg;
	private String email;
	private String telefone;
	private String celular;
	private Endereco endereco = new Endereco();

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void criar (Connection conexao, int cod, DadosPessoais dados) {
		String sql = "insert into dadosPessoais values (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, cod);
			stmt.setString(2, dados.getCpf());
			stmt.setString(3, dados.getRg());
			stmt.setString(4, dados.getEmail());
			stmt.setString(5, dados.getTelefone());
			stmt.setString(6, dados.getCelular());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dados.getEndereco().criar(conexao, cod, dados.getEndereco());
	}
}
