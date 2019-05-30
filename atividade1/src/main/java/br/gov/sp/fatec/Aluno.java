package br.gov.sp.fatec;

public class Aluno implements Info{
	private String nome;
	private String RA;
	private String curso;
	
	public Aluno() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRA() {
		return RA;
	}
	public void setRA(String rA) {
		RA = rA;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String getInfo() {
		return "Nome:\t"+nome+"\nRA:\t"+RA+"\nCurso:\t"+curso;
	}
}