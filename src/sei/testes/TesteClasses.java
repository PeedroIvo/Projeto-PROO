package sei.testes;

import sei.*;

public class TesteClasses {

	public static void main(String[] args) {
		Professor professor = new Professor();
		Disciplina disciplina = new Disciplina();
		Turma turma = new Turma();
		Responsavel resp = new Responsavel();
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
		disciplina.setTurma(turma);
		disciplina.setSigla("ING");

		resp.setCodUsuario(2);
		resp.setTipoUsuario('r');
		
		aluno1.setNome("Aluno 1");
		aluno1.setCodUsuario(3);
		aluno1.setResponsavel(resp);
		aluno1.setTipoUsuario('a');
		aluno1.setTurmaAtual(turma);

		aluno2.setNome("Aluno 2");
		aluno2.setCodUsuario(4);
		aluno2.setResponsavel(resp);
		aluno1.setTipoUsuario('a');
		aluno1.setTurmaAtual(turma);

		turma.addAluno(aluno1.getCodUsuario(), aluno1.getNome());
		turma.addAluno(aluno2.getCodUsuario(), aluno2.getNome());

		disciplina.addNota(1, aluno1.getCodUsuario(), 10);
		disciplina.addNota(1, aluno2.getCodUsuario(), 8.5);

		disciplina.imprimeNotas(1);
	}
}
