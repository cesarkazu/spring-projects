package br.gov.sp.fatec.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.repository.ProdutoRepository;

@Service("produtoService")
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	public void setProdutoRepo(ProdutoRepository produtoRepo) {
		this.produtoRepo = produtoRepo;
	}

	@Override
	public Produto inlcuir(String nome, String desc, double preco) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDesc(desc);
		produto.setPreco(preco);
		produtoRepo.save(produto);
		return produto;
	}

	@Override
	public Produto excluir(String nome) {
		Produto produto = produtoRepo.findByNome(nome);
		produtoRepo.delete(produto);
		return produto;
	}

	@Override
	public List<Produto> todos() {
		return (List<Produto>) produtoRepo.findAll();
	}

	@Override
	public Produto buscaExata(String nome) {
		return produtoRepo.findByNome(nome);
	}

	@Override
	public Produto buscarUm(String nome) {
		return produtoRepo.findTop1ByNomeContains(nome);
	}

	@Override
	public Produto inlcuir(Produto produto) {
		Produto produtoSave = produto;
		produtoRepo.save(produtoSave);
		return produtoSave;
	}

	@Override
	public Produto searchById(Long id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if(produto.isPresent()) {
			return produto.get();
		}
		return null;
	}

	@Override
	public List<Produto> buscarNomeMaiorPreco(String nome, double preco) {
		return produtoRepo.findByNomeContainsIgnoreCaseAndPrecoGreaterThan(nome, preco);
	}

	@Override
	public List<Produto> buscarNomeMenorPreco(String nome, double preco) {
		return produtoRepo.findByNomeContainsIgnoreCaseAndPrecoLessThan(nome, preco);
	}

	@Override
	public List<Produto> buscarMaiorPreco(double preco) {
		return produtoRepo.findByPrecoGreaterThan(preco);
	}

	@Override
	public List<Produto> buscarMenorPreco(double preco) {
		return produtoRepo.findByPrecoLessThan(preco);
	}
}