package br.gov.sp.fatec.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idDisciplina")
	private Long id;

	@Column(name = "nomeDisciplina", unique=true, length = 50, nullable = false)
	private String nome;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "idProfessor", nullable = true)
	private Professor professor;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "idCurso", nullable = true)
	private Curso curso;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "alunosDisciplinas", 
	joinColumns = { @JoinColumn(name = "idDisciplina") }, 
	inverseJoinColumns = { @JoinColumn(name = "idAluno") })
	private List<Aluno> alunos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}