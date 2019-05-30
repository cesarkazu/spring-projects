package br.gov.sp.fatec.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	public Aluno findByNome(String nome);

	public Aluno findTop1ByNomeContains(String nome);

	public List<Aluno> findByIdGreaterThan(Long id);
}