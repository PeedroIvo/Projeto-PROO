package sei;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	public void menu(Connection conexao) throws SQLException {
		this.setConexao(conexao);
		int opcao;
		
		do {
			System.out.println("-------------------------------------");
			System.out.println("Olá, " + this.getNome() + "! O que deseja fazer?");
			System.out.println("[1] Visualizar boletim de notas");
			System.out.println("[2] Visualizar dados pessoais");
			System.out.println("[3] Mudar a senha");
			System.out.println("[4] Logout");

			do {
				System.out.print("Digite sua opção: ");
				opcao = input.nextInt();

				if (opcao <= 0 || opcao > 4) {
					System.out.println("Opção inválida! Tente novamente!");
				}
			} while (opcao <= 0 || opcao > 4);
			
			System.out.println();

			if (opcao == 1) {
				this.visualizarBoletim();
			} else if (opcao == 2) {
				this.visualizarDadosPessoais();
			} else if (opcao == 3) {
				this.mudarSenha();
			}
		} while (opcao != 4);
	}
	
	public void visualizarBoletim () throws SQLException {
		this.setCodTurmaAtual(alunoCRUD.procuraAluno(conexao, getCodUsuario()).getCodTurmaAtual());
		int serieDoAluno = turmaCRUD.procuraTurma(conexao, this.getCodTurmaAtual()).getSerie();
		
		List<Disciplina> disciplinas = disciplinaCRUD.listarPorSerie(conexao, serieDoAluno);
		List<Nota> notas = notaCRUD.procuraListNotasAluno(conexao, this.getCodUsuario());
		
		System.out.println("[" + this.getCodUsuario() + "] Aluno: " + this.getNome() + " | " + serieDoAluno + "º Ano");
		
		if(disciplinas.isEmpty()) {
			System.out.println("Você não está matriculado em nenhuma disciplina!");
		} else {
			for(Disciplina disciplina:disciplinas) {
				double notaB1=0, notaB2=0, notaB3=0, notaB4=0;
				
				for(Nota nota:notas){
					if(nota.getMatricAluno() == this.getCodUsuario() && nota.getCodDisciplina() == disciplina.getCodDisciplina()) {
						if(nota.getBimestre() == 1) {
							notaB1 = nota.getNota();
						} else if(nota.getBimestre() == 2) {
							notaB2 = nota.getNota();
						} else if(nota.getBimestre() == 3) {
							notaB3 = nota.getNota();
						} else if(nota.getBimestre() == 4) {
							notaB4 = nota.getNota();
						}
					}
				}
				
				double media = (notaB1 + notaB2 + notaB3 + notaB4)/4;
				String mediaF = formataNota(media);
				
				String notaB1F = formataNota(notaB1);
				String notaB2F = formataNota(notaB2);
				String notaB3F = formataNota(notaB3);
				String notaB4F = formataNota(notaB4);
				
				System.out.println("Disciplina: " + disciplina.getSigla() + " (" + disciplina.getNome() + ") | N1: " + notaB1F + " | N2: " + notaB2F + " | N3: " + notaB3F + " | N4: " + notaB4F + " | Média: " + mediaF);
			}
		}
		
		System.out.println();
	}
}
