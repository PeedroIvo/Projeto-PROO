package sei;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Disciplina {
	private int codDisciplina;
	private String nome;
	private String sigla;
	private Professor profResponsavel;
	private Turma turma;

	private Map<Integer, Double> notasB1 = new HashMap<>();
	private Map<Integer, Double> notasB2 = new HashMap<>();
	private Map<Integer, Double> notasB3 = new HashMap<>();
	private Map<Integer, Double> notasB4 = new HashMap<>();

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Professor getProfResponsavel() {
		return profResponsavel;
	}

	public void setProfResponsavel(Professor profResponsavel) {
		this.profResponsavel = profResponsavel;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Map<Integer, Double> getNotasB1() {
		return notasB1;
	}

	public Map<Integer, Double> getNotasB2() {
		return notasB2;
	}

	public Map<Integer, Double> getNotasB3() {
		return notasB3;
	}

	public Map<Integer, Double> getNotasB4() {
		return notasB4;
	}

	public void addNota(int bimestre, int matricAluno, double nota) {
		if (bimestre == 1) {
			notasB1.put(matricAluno, nota);
		} else if (bimestre == 2) {
			notasB2.put(matricAluno, nota);
		} else if (bimestre == 3) {
			notasB3.put(matricAluno, nota);
		} else if (bimestre == 4) {
			notasB4.put(matricAluno, nota);
		}
	}

	public void imprimeNotas(int bimestre) {
		if (bimestre == 1) {
			Set<Integer> codigos = this.getNotasB1().keySet();
			for (int cod : codigos) {
				System.out.println(this.getTurma().getAlunos().get(cod) + ": " + this.getNotasB1().get(cod));
			}
		} else if (bimestre == 2) {
			Set<Integer> codigos = this.getNotasB2().keySet();
			for (int cod : codigos) {
				System.out.println(this.getTurma().getAlunos().get(cod) + ": " + this.getNotasB2().get(cod));
			}
		} else if (bimestre == 3) {
			Set<Integer> codigos = this.getNotasB3().keySet();
			for (int cod : codigos) {
				System.out.println(this.getTurma().getAlunos().get(cod) + ": " + this.getNotasB3().get(cod));
			}
		} else if (bimestre == 4) {
			Set<Integer> codigos = this.getNotasB4().keySet();
			for (int cod : codigos) {
				System.out.println(this.getTurma().getAlunos().get(cod) + ": " + this.getNotasB4().get(cod));
			}
		}
	}
}
