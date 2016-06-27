package sei.experiencias;

import sei.*;

public class ExperimentandoClasses {

	public static void main(String[] args) {
		Professor professor = new Professor();
		Disciplina disciplina = new Disciplina();
		Turma turma = new Turma();
		Aluno aluno1 = new Aluno();
		Aluno aluno2 = new Aluno();

		professor.setTipoUsuario('p');
		professor.setCodUsuario(1);

		turma.setCodTurma(1);
		turma.setAno(2016);
		turma.setSerie(3);
		turma.setTurno('m');

		disciplina.setCodDisciplina(1);
		disciplina.setNome("Ingles");
		disciplina.setProfResponsavel(professor);
		disciplina.setSerie(1);
		disciplina.setSigla("ING");
		
		aluno1.setNome("Aluno 1");
		aluno1.setCodUsuario(3);
		aluno1.setTipoUsuario('a');
		aluno1.setCodTurmaAtual(turma.getCodTurma());

		aluno2.setNome("Aluno 2");
		aluno2.setCodUsuario(4);
		aluno1.setTipoUsuario('a');
		aluno1.setCodTurmaAtual(turma.getCodTurma());

		turma.addAluno(aluno1);
		turma.addAluno(aluno2);
		
		/*
		disciplina.addNota(1, aluno1.getCodUsuario(), 10);
		disciplina.addNota(1, aluno2.getCodUsuario(), 8.5);

		disciplina.imprimeNotas(1);
		*/
	}
}
