package sei;

import java.sql.Connection;
import java.util.Scanner;

public class Professor extends Usuario {
	private Scanner input = new Scanner(System.in);
	
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
