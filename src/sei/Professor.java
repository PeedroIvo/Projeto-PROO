package sei;

import java.sql.Connection;
import java.util.List;

public class Professor extends Usuario {
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
			System.out.println("Ol�, " + this.getNome() + "! O que deseja fazer?");
			System.out.println("[1] Visualizar suas disciplinas");
			System.out.println("[2] Adicionar notas");
			System.out.println("[3] Visualizar dados pessoais");
			System.out.println("[4] Mudar a senha");
			System.out.println("[5] Logout");

			do {
				System.out.print("Digite sua op��o: ");
				opcao = input.nextInt();

				if (opcao <= 0 || opcao > 5) {
					System.out.println("Op��o inv�lida! Tente novamente!");
				}
			} while (opcao <= 0 || opcao > 5);
			
			System.out.println();

			if (opcao == 1) {
				this.visualizarDisciplinas();
			} else if (opcao == 2) {
				this.addNotas();
			} else if (opcao == 3) {
				this.mudarSenha();
			} else if (opcao == 4) {
				
			}
		} while (opcao != 5);
	}
	
	public void visualizarDisciplinas() {
		
		List<Disciplina> disciplinas = disciplinaCRUD.listarPorProfessor(conexao, this.getCodUsuario());
		
		if(disciplinas.isEmpty()){
			System.out.println("No momento, voc� n�o � respons�vel por nenhuma disciplina!");
		} else {
			for (Disciplina disciplina:disciplinas) {
				System.out.println("[" + disciplina.getCodDisciplina() + "] " + disciplina.getSigla() + " (" + disciplina.getNome() + ") | " + disciplina.getSerie() + "� Ano");
			}
		}
		
		System.out.println();
	}
	
	public void addNotas() {
		int codDisciplina;
		
		System.out.print("Digite o c�gido da disciplina: ");
		codDisciplina = input.nextInt();
		
		if(this.verificaDisciplinaProfessor(codDisciplina)) {
			Disciplina disciplina = disciplinaCRUD.procuraDisciplina(conexao, codDisciplina);
			
			char turno;
			
			do {
				System.out.print("Digite o turno da turma (M ou V): ");
				turno = input.next().charAt(0);
				
				if (turno != 'M' && turno != 'V'){
					System.out.println("Turno inv�lido! Selecione Matutino ou Vespertino");
				}
			} while (turno != 'M' && turno != 'V');
			
			int bimestre;
			
			do {
				System.out.print("Digite o bimestre correspondente as notas: (1, 2, 3 ou 4): ");
				bimestre = input.nextInt();
				
				if (bimestre != 1 && bimestre != 2 && bimestre != 3 && bimestre != 4){
					System.out.println("Bimestre inv�lido! Selecione 1, 2, 3 ou 4");
				}
			} while (bimestre != 1 && bimestre != 2 && bimestre != 3 && bimestre != 4);
			
			int codTurma = turmaCRUD.procuraCodTurma(conexao, disciplina.getSerie(), turno);
			
			List<Aluno> alunos = alunoCRUD.listarPorTurma(conexao, codTurma);
			
			System.out.println();
			
			if(alunos.isEmpty()){
				System.out.println("No momento, esta turma n�o tem nenhum aluno matriculado!");
			} else {
				for(Aluno aluno:alunos){
					double nota;
					
					do {
						System.out.print("[" + aluno.getCodUsuario() + "] Nome do aluno: " + aluno.getNome() + " | Digite a nota: ");
						nota = input.nextDouble();
						
						if (nota < 0 || nota > 10) {
							System.out.println("Nota inv�lida! A nota dever� ser de 0 � 10");
						}
					} while (nota < 0 || nota > 10);
					
					notaCRUD.criar(conexao, aluno.getCodUsuario(), codDisciplina, bimestre, nota);
					
					System.out.println("As notas foram adicionadas com sucesso!");
				}
			}
		} else {
			System.out.println("Voc� n�o � respons�vel por esta disciplina!");
		}
		
		System.out.println();
	}
	
	public boolean verificaDisciplinaProfessor(int codDisciplina) {
		List<Disciplina> disciplinas = disciplinaCRUD.listarPorProfessor(conexao, this.getCodUsuario());
		
		for (Disciplina disciplina:disciplinas) {
			if(disciplina.getCodDisciplina() == codDisciplina) {
				return true;
			}
		}
		
		return false;
	}
}
