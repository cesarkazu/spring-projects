package br.gov.sp.fatec.repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

	public Disciplina findByNome(String nome);
	
	public Disciplina findTop1ByNomeContains(String nome);
	
	public List<Disciplina> findByIdGreaterThan(Long id);
	
	public List<Disciplina> findByAlunosNomeContainsIgnoreCase(String nome);
	
	public List<Disciplina> findByNomeContainsIgnoreCaseOrAlunosNomeContainsIgnoreCase(String nomeDisciplina, String nomeAluno);
	
	@Query("select d from Disciplina d where d.nome like %?1%")
	public List<Disciplina> buscaDisciplina(String nome);
}