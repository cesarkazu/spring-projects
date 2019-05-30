package br.gov.sp.fatec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

	public Professor findByNome(String nome);

	public Professor findTop1ByNomeContains(String nome);

	public Professor findFirstByOrderByIdDesc();

	@Query("select p from Professor p where p.nome = ?1")
	public Professor buscaProfessorNome(String nome);
}