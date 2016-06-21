package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Administrador extends Usuario {
	
	private Connection conexao;
	private Scanner input = new Scanner(System.in);

	public boolean menu(Connection conexao) {
		this.conexao = conexao;
		int opcao;
		
		do {
			System.out.println("[1] Matricular Aluno");
			System.out.println("[2] Criar Nova Turma");
			System.out.println("[3] Sair");

			do {
				System.out.print("Digite sua opção: ");
				opcao = input.nextInt();

				if (opcao <= 0 || opcao > 3) {
					System.out.println("Opção inválida! Tente novamente!");
				}
			} while (opcao <= 0 || opcao > 3);

			if (opcao == 1) {
				this.matricularAluno();
			} else if (opcao == 2) {
				
			}
		} while (opcao != 3);

		return false;
	}
	
	public void matricularAluno() {
		Aluno novoAluno = new Aluno();	
		
		System.out.println("\nDigite os dados do novo aluno abaixo:");
		novoAluno.setTipoUsuario('a');
		
		input.nextLine();
		System.out.print("Nome: ");
		novoAluno.setNome(input.nextLine());
		
		System.out.print("Idade: ");
		novoAluno.setIdade(input.nextInt());
		
		do {
			System.out.print("Sexo (F ou M): ");
			novoAluno.setSexo(input.next().charAt(0));
			
			if (novoAluno.getSexo() != 'M' && novoAluno.getSexo() != 'F'){
				System.out.println("Sexo Inválido! Digite F ou M");
			}
		} while (novoAluno.getSexo() != 'M' && novoAluno.getSexo() != 'F');
		
		System.out.print("CPF: ");
		novoAluno.getDadosPessoais().setCpf(input.next());
		
		System.out.print("RG: ");
		novoAluno.getDadosPessoais().setRg(input.next());
		
		System.out.print("Email: ");
		novoAluno.getDadosPessoais().setEmail(input.next().toLowerCase());
		
		input.nextLine();
		System.out.print("Celular: ");
		novoAluno.getDadosPessoais().setCelular(input.nextLine());
		
		System.out.print("Telefone: ");
		novoAluno.getDadosPessoais().setTelefone(input.nextLine());
		
		System.out.println("\nAgora digite os dados do endereço do novo aluno:" );
		
		System.out.print("CEP: ");
		novoAluno.getDadosPessoais().getEndereco().setCep(input.next());
		
		input.nextLine();
		System.out.print("Rua: ");
		novoAluno.getDadosPessoais().getEndereco().setRua(input.nextLine());
		
		System.out.print("Número: ");
		novoAluno.getDadosPessoais().getEndereco().setnCasa(input.nextLine());
		
		System.out.print("Bairro: ");
		novoAluno.getDadosPessoais().getEndereco().setBairro(input.nextLine());
		
		System.out.print("Complemento: ");
		novoAluno.getDadosPessoais().getEndereco().setComplemento(input.nextLine());
		
		System.out.print("Cidade: ");
		novoAluno.getDadosPessoais().getEndereco().setCidade(input.nextLine());
		
		System.out.print("Estado: ");
		novoAluno.getDadosPessoais().getEndereco().setEstado(input.nextLine());
		
		novoAluno.setCodTurmaAtual(1); //IMPLEMENTAR CRIAR NOVA TURMA!!!!
		
		System.out.println();
		
		do {
			System.out.print("Novo login do aluno no sistema: ");
			novoAluno.setLogin(input.next().toLowerCase());
			
			if (!this.verificaLogin(novoAluno.getLogin())){
				System.out.println("Este login já está sendo usado, tente novamente!");
			}
		} while (!this.verificaLogin(novoAluno.getLogin()));
		
		novoAluno.criar(conexao, novoAluno);
		
		System.out.println("\nAluno matriculado com sucesso!\nLogin: " + novoAluno.getLogin() + "\nSenha: 123456\n");
	}
	
	public boolean verificaLogin(String loginDigitado) {
		try (PreparedStatement stmt = conexao.prepareStatement("select * from usuario where login='" + loginDigitado + "'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
