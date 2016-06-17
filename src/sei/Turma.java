package sei;

import java.util.HashMap;
import java.util.Map;

public class Turma {
	private int codTurma;
	private int serie;
	private char turno;
	private int ano;
	private String sala;
	private Map<Integer, String> alunos = new HashMap<>();

	public int getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(int codTurma) {
		this.codTurma = codTurma;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public void addAluno(int cod, String nome) {
		alunos.put(cod, nome);
	}

	public Map<Integer, String> getAlunos() {
		return alunos;
	}
}
