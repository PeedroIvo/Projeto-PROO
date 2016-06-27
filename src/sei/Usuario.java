package sei;

import java.sql.Connection;
import java.util.Scanner;

import sei.persistencia.crud.*;

public abstract class Usuario {	
	protected UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
	protected AlunoCRUD alunoCRUD = new AlunoCRUD();
	protected ProfessorCRUD professorCRUD = new ProfessorCRUD();
	protected DisciplinaCRUD disciplinaCRUD = new DisciplinaCRUD();
	protected TurmaCRUD turmaCRUD = new TurmaCRUD();
	protected NotaCRUD notaCRUD = new NotaCRUD();
	
	protected Scanner input = new Scanner(System.in);
	protected Connection conexao;
	
	private int codUsuario;
	private String login;
	private String senha;
	private String nome;
	private char tipoUsuario;
	
	public abstract void menu(Connection conexao);
	
	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public void mudarSenha() {
		String novaSenha;
		
		do {
			System.out.print("Digite a sua nova senha: ");
			novaSenha = input.next();
			
			if (novaSenha.length() < 6) {
				System.out.println("A senha deve ter no mínimo 6 caracteres!");
			}
		} while (novaSenha.length() < 6);
		
		this.usuarioCRUD.updateSenha(conexao, this.getLogin(), novaSenha);
		
		System.out.println("\nSenha alterada com sucesso!\n");
	}
}
