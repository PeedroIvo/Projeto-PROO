package sei;

import java.util.HashMap;
import java.util.Map;

public class Disciplina {
	private int codDisciplina;
	private String nome;
	private String sigla;
	private Professor profResponsavel;
	private int codTurma;

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

	public int getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(int codTurma) {
		this.codTurma = codTurma;
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
}
