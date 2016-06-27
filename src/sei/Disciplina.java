package sei;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	private int codDisciplina;
	private String nome;
	private String sigla;
	private int serie;
	private Professor profResponsavel;
	
	private List<Nota> notasB1 = new ArrayList<>();
	private List<Nota> notasB2 = new ArrayList<>();
	private List<Nota> notasB3 = new ArrayList<>();
	private List<Nota> notasB4 = new ArrayList<>();

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

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public List<Nota> getNotasB1() {
		return notasB1;
	}

	public void setNotasB1(List<Nota> notasB1) {
		this.notasB1 = notasB1;
	}

	public List<Nota> getNotasB2() {
		return notasB2;
	}

	public void setNotasB2(List<Nota> notasB2) {
		this.notasB2 = notasB2;
	}

	public List<Nota> getNotasB3() {
		return notasB3;
	}

	public void setNotasB3(List<Nota> notasB3) {
		this.notasB3 = notasB3;
	}

	public List<Nota> getNotasB4() {
		return notasB4;
	}

	public void setNotasB4(List<Nota> notasB4) {
		this.notasB4 = notasB4;
	}

}
