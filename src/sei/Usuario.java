package sei;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import sei.persistencia.crud.*;

public abstract class Usuario implements Menu {	
	protected UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
	protected AlunoCRUD alunoCRUD = new AlunoCRUD();
	protected ProfessorCRUD professorCRUD = new ProfessorCRUD();
	protected DadosPessoaisCRUD dadosCRUD = new DadosPessoaisCRUD();
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
	
	public void mudarSenha() throws SQLException {
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
	
	public char confirmar(String pergunta) {
		char resposta;
		
		do {
			System.out.print(pergunta);
			resposta = input.next().charAt(0);
			
			if (resposta != 'S' && resposta != 'N'){
				System.out.println("Resposta inválida! Digite S ou N");
			}
		} while (resposta != 'S' && resposta != 'N');
		
		return resposta;
	}
	
	public String formataNota(double nota) {
		String notaF = String.format("%.2f", nota);
		
		return notaF;
	}
	
	public void visualizarDadosPessoais() throws SQLException {
		DadosPessoais dados = dadosCRUD.procuraDadosPessoais(conexao, this.getCodUsuario());
		
		System.out.println("Nome: " + this.getNome());
		System.out.println("CPF: " + dados.getCpf());
		System.out.println("RG: " + dados.getRg());
		System.out.println("Email: " + dados.getEmail());
		System.out.println("Telefone: " + dados.getTelefone());
		System.out.println("Celular: " + dados.getCelular());
		System.out.println("\nCEP: " + dados.getEndereco().getCep());
		System.out.println("Rua: " + dados.getEndereco().getRua());
		System.out.println("Número: " + dados.getEndereco().getnCasa());
		System.out.println("Bairro: " + dados.getEndereco().getBairro());
		System.out.println("Complemento: " + dados.getEndereco().getComplemento());
		System.out.println("Cidade, Estado: " + dados.getEndereco().getCidade() + ", " + dados.getEndereco().getEstado() + "\n");
	}
}
