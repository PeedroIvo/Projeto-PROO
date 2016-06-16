package sei;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	private int codDisciplina;
	private int nome;
	private int sigla;
	private Professor profResponsavel;
	private Turma turma;

	private List<Double> notasB1 = new ArrayList<>();
	private List<Double> notasB2 = new ArrayList<>();
	private List<Double> notasB3 = new ArrayList<>();
	private List<Double> notasB4 = new ArrayList<>();

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
	}

	public int getSigla() {
		return sigla;
	}

	public void setSigla(int sigla) {
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
	
	public void addNotaB1(double nota) {
		notasB1.add(nota);
	}

	public void addNotaB2(double nota) {
		notasB2.add(nota);
	}
	
	public void addNotaB3(double nota) {
		notasB3.add(nota);
	}
	
	public void addNotaB4(double nota) {
		notasB4.add(nota);
	}

	public List<Double> getNotasB1() {
		return notasB1;
	}

	public List<Double> getNotasB2() {
		return notasB2;
	}

	public List<Double> getNotasB3() {
		return notasB3;
	}

	public List<Double> getNotasB4() {
		return notasB4;
	}
}
