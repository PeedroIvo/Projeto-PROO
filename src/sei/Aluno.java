package sei;

import java.sql.Connection;

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
	
	@Override
	public void menu(Connection conexao) {
		this.setConexao(conexao);
		int opcao;
		
		do {
			System.out.println("-------------------------------------");
			System.out.println("Olá, " + this.getNome() + "! O que deseja fazer?");
			System.out.println("[1] -");
			System.out.println("[2] Logout");

			do {
				System.out.print("Digite sua opção: ");
				opcao = input.nextInt();

				if (opcao <= 0 || opcao > 5) {
					System.out.println("Opção inválida! Tente novamente!");
				}
			} while (opcao <= 0 || opcao > 5);
			
			System.out.println();

			if (opcao == 1) {

			}
		} while (opcao != 2);
	}
}
