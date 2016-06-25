package sei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

import sei.persistencia.crud.AlunoCRUD;
import sei.persistencia.crud.ProfessorCRUD;

public class Administrador extends Usuario {
	private Scanner input = new Scanner(System.in);
	private AlunoCRUD alunoCRUD = new AlunoCRUD();
	private ProfessorCRUD professorCRUD = new ProfessorCRUD();
	
	@Override
	public void menu(Connection conexao) {
		this.setConexao(conexao);
		int opcao;
		
		do {
			System.out.println("-------------------------------------");
			System.out.println("Olá, " + this.getNome() + "! O que deseja fazer?");
			System.out.println("[1] Matricular aluno");
			System.out.println("[2] Desmatricular aluno");
			System.out.println("[3] Cadastrar professor");
			System.out.println("[4] Quadro de professores");
			System.out.println("[5] Logout");

			do {
				System.out.print("Digite sua opção: ");
				opcao = input.nextInt();

				if (opcao <= 0 || opcao > 5) {
					System.out.println("Opção inválida! Tente novamente!");
				}
			} while (opcao <= 0 || opcao > 5);
			
			System.out.println();

			if (opcao == 1) {
				this.matricularAluno();
			} else if (opcao == 2) {
				this.desmatricularAluno();
			} else if (opcao == 3) {
				this.cadastrarProfessor();
			} else if (opcao == 4) {
				
			}
		} while (opcao != 5);
	}
	
	public void matricularAluno() {
		Aluno novoAluno = new Aluno();	
		
		novoAluno.setTipoUsuario('a');
		
		System.out.println("Digite os dados do novo aluno abaixo:");
		
		input.nextLine();
		System.out.print("Nome: ");
		novoAluno.setNome(input.nextLine());
		
		System.out.print("Idade: ");
		novoAluno.setIdade(input.nextInt());
		
		do {
			System.out.print("Sexo (F ou M): ");
			novoAluno.setSexo(input.next().charAt(0));
			
			if (novoAluno.getSexo() != 'M' && novoAluno.getSexo() != 'F'){
				System.out.println("Sexo Inválido! Selecione Feminino ou Masculino");
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
		
		int serie;
		
		System.out.println();
		
		do {
			System.out.print("Série (1, 2 ou 3): ");
			serie = input.nextInt();
			
			if (serie != 1 && serie != 2 && serie != 3){
				System.out.println("Série inválida! Selecione 1º Ano, 2º Ano ou 3º Ano");
			}
		} while (serie != 1 && serie != 2 && serie != 3);
		
		char turno;
		
		do {
			System.out.print("Turno (M ou V): ");
			turno = input.next().charAt(0);
			
			if (turno != 'M' && turno != 'V'){
				System.out.println("Turno inválido! Selecione Matutino ou Vespertino");
			}
		} while (turno != 'M' && turno != 'V');
		
		novoAluno.setCodTurmaAtual(this.procuraTurma(serie, turno));
		
		System.out.println();
		
		do {
			System.out.print("Novo login do aluno no sistema: ");
			novoAluno.setLogin(input.next().toLowerCase());
			
			if (!this.verificaLogin(novoAluno.getLogin())){
				System.out.println("Este login já está sendo usado, tente novamente!");
			}
		} while (!this.verificaLogin(novoAluno.getLogin()));
		
		alunoCRUD.criar(conexao, novoAluno);
		
		System.out.println("\nAluno matriculado com sucesso!\nLogin: " + novoAluno.getLogin() + "\nSenha Padrão: 123456\n");
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
	
	public int procuraTurma(int serie, char turno) {
		try (PreparedStatement stmt = conexao.prepareStatement("select codTurma from turma where serie='" + serie + "' and turno='" + turno + "'");
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				return rs.getInt("codTurma");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void desmatricularAluno() {
		System.out.println("Atenção! O cancelamento de matrícula não poderá ser desfeito!");
		System.out.print("Digite o código de matrícula do Aluno: ");
		int cod = input.nextInt();
		
		if (procuraAluno(cod)){
			char resposta;
			
			do {
				System.out.print("Tem certeza que deseja cancelar esta matricula (S ou N)? ");
				resposta = input.next().charAt(0);
				
				if (resposta != 'S' && resposta != 'N'){
					System.out.println("Resposta inválida! Digite S ou N");
				}
			} while (resposta != 'S' && resposta != 'N');
			
			if (resposta == 'S') {
				this.alunoCRUD.apagar(conexao, cod);
				
				System.out.println("\nCancelamento de matrícula efetuado com sucesso!");
			}
			
			System.out.println();
		}
	}
	
	public boolean procuraAluno(int cod) {		
		try (PreparedStatement stmt = conexao.prepareStatement("select nome from aluno, usuario where aluno.matricAluno='" + cod + "' and usuario.codUsuario='" + cod + "'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				System.out.println("\nNome do Aluno: " + rs.getString("nome"));
				return true;
			} else {
				System.out.println("\nNenhum aluno foi encontrado com esse código de matrícula!\n");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void cadastrarProfessor() {
		Professor novoProfessor = new Professor();
		
		Calendar cal = Calendar.getInstance(); 
		novoProfessor.setDataAdmissao(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
		
		novoProfessor.setTipoUsuario('p');
		
		System.out.println("Digite os dados do novo professor abaixo:");
		
		input.nextLine();
		System.out.print("Nome: ");
		novoProfessor.setNome(input.nextLine());
		
		System.out.print("CPF: ");
		novoProfessor.getDadosPessoais().setCpf(input.next());
		
		System.out.print("RG: ");
		novoProfessor.getDadosPessoais().setRg(input.next());
		
		System.out.print("Email: ");
		novoProfessor.getDadosPessoais().setEmail(input.next().toLowerCase());
		
		input.nextLine();
		System.out.print("Celular: ");
		novoProfessor.getDadosPessoais().setCelular(input.nextLine());
		
		System.out.print("Telefone: ");
		novoProfessor.getDadosPessoais().setTelefone(input.nextLine());
		
		System.out.println("\nAgora digite os dados do endereço do novo professor:" );
		
		System.out.print("CEP: ");
		novoProfessor.getDadosPessoais().getEndereco().setCep(input.next());
		
		input.nextLine();
		System.out.print("Rua: ");
		novoProfessor.getDadosPessoais().getEndereco().setRua(input.nextLine());
		
		System.out.print("Número: ");
		novoProfessor.getDadosPessoais().getEndereco().setnCasa(input.nextLine());
		
		System.out.print("Bairro: ");
		novoProfessor.getDadosPessoais().getEndereco().setBairro(input.nextLine());
		
		System.out.print("Complemento: ");
		novoProfessor.getDadosPessoais().getEndereco().setComplemento(input.nextLine());
		
		System.out.print("Cidade: ");
		novoProfessor.getDadosPessoais().getEndereco().setCidade(input.nextLine());
		
		System.out.print("Estado: ");
		novoProfessor.getDadosPessoais().getEndereco().setEstado(input.nextLine());
		
		System.out.println();
		
		do {
			System.out.print("Novo login do professor no sistema: ");
			novoProfessor.setLogin(input.next().toLowerCase());
			
			if (!this.verificaLogin(novoProfessor.getLogin())){
				System.out.println("Este login já está sendo usado, tente novamente!");
			}
		} while (!this.verificaLogin(novoProfessor.getLogin()));
		
		professorCRUD.criar(conexao, novoProfessor);
		
		System.out.println("\nProfessor matriculado com sucesso!\nLogin: " + novoProfessor.getLogin() + "\nSenha Padrão: 123456\n");
	}
	
	public void quadroProfessores() {
		
	}
}
