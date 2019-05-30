package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Produto;

public interface ProdutoService {

	public Produto inlcuir(String nome, String desc, double preco);

	public Produto inlcuir(Produto produto);

	public Produto excluir(String nome);

	public List<Produto> todos();

	public Produto buscaExata(String nome);

	public Produto buscarUm(String nome);

	public Produto searchById(Long id);

	public List<Produto> buscarNomeMaiorPreco(String nome, double preco);

	public List<Produto> buscarNomeMenorPreco(String nome, double preco);
	
	public List<Produto> buscarMaiorPreco(double preco);

	public List<Produto> buscarMenorPreco(double preco);
}