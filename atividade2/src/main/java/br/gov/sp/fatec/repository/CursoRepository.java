package br.gov.sp.fatec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

	public Curso findByNome(String nome);

	public Curso findTop1ByNomeContains(String nome);
	
	public Curso findTop1BySiglaContains(String sigla);
	
	public Curso findFirstByOrderByIdDesc();
	
	@Query("select c from Curso c where c.sigla = ?1")
	public Curso buscaCursoSigla(String sigla);
}