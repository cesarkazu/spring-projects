package br.gov.sp.fatec.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Contato;

public interface ContatoRepository extends CrudRepository<Contato, Long> {

	public List<Contato> findByUsuarioNome(String nome);
	
	public List<Contato> findByUsuarioId(String nome);

}