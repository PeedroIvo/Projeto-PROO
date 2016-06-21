package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Endereco {
	private String cep;
	private String rua;
	private String nCasa;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getnCasa() {
		return nCasa;
	}

	public void setnCasa(String nCasa) {
		this.nCasa = nCasa;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void criar(Connection conexao, int cod, Endereco endereco) {
		String sql = "insert into endereco values (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, cod);
			stmt.setString(2, endereco.getCep());
			stmt.setString(3, endereco.getRua());
			stmt.setString(4, endereco.getnCasa());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setString(7, endereco.getCidade());
			stmt.setString(8, endereco.getEstado());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
