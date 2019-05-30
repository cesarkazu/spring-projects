package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Curso;

public interface CursoService {

	public boolean incluirCurso(String nome, String sigla);

	public List<Curso> listarCursos();

	public Curso ultimoCurso();

	public Curso buscarTop1Sigla(String sigla);
	
	public Curso buscarCursoSigla(String sigla);
	
	public void deletaCursoSigla(String sigla);

}