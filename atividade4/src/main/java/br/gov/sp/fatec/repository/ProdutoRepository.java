package br.gov.sp.fatec.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	public Produto findByNome(String nome);

	public List<Produto> findByNomeContainsIgnoreCase(String nome);

	public Produto findTop1ByNomeContains(String nome);

	public List<Produto> findByIdGreaterThan(Long id);

	public List<Produto> findByNomeContainsIgnoreCaseAndPrecoGreaterThan(String nome, double preco);

	public List<Produto> findByNomeContainsIgnoreCaseAndPrecoLessThan(String nome, double preco);
	
	public List<Produto> findByPrecoGreaterThan(double preco);

	public List<Produto> findByPrecoLessThan(double preco);
}
