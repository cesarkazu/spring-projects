package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Aluno;

public interface AlunoService {

	public boolean incluirAluno(String nome, String ra);

	public List<Aluno> listarAlunos();

	public Aluno buscarTop1(String nome);

}