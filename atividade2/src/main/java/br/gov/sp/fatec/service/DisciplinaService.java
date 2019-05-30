package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Aluno;
import br.gov.sp.fatec.model.Curso;
import br.gov.sp.fatec.model.Disciplina;
import br.gov.sp.fatec.model.Professor;

public interface DisciplinaService {

	public boolean incluirDisciplina(String nome, Curso curso, Professor professor, List<Aluno> alunos);

	public List<Disciplina> listarDisciplinas();

	public Disciplina buscarTop1(String nome);

	public Disciplina incluirAlunoDisciplina(String nomeDisciplina, String nomeAluno);

}