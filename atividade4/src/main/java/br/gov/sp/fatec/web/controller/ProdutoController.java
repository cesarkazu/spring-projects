package br.gov.sp.fatec.web.controller;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.service.ProdutoService;
import br.gov.sp.fatec.view.View;

@RestController
// Um @RequestMapping(value = "/autorizacao") poderia ser incluido como base
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "WEEB PORRA";
	}

	@RequestMapping(value = "/produto/getById", method = RequestMethod.GET)
	@JsonView(View.ProdutoGeral.class)
	public ResponseEntity<Produto> get(@RequestParam(value="id", defaultValue="1") Long id) {
		Produto produto = produtoService.searchById(id);
		if(produto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/getByNomeAndPrecoMaior", method = RequestMethod.GET)
	@JsonView(View.ProdutoGeral.class)
	public ResponseEntity<Collection<Produto>> getByNomeAndPrecoMaior(@RequestParam(value="nome", defaultValue="") String nome, @RequestParam(value="preco", defaultValue="0") double preco) {
		if(nome.equals("")) {
			List<Produto> produtos = produtoService.buscarMaiorPreco(preco);
			if(produtos.isEmpty()) return new ResponseEntity<Collection<Produto>>(HttpStatus.NOT_FOUND);
			else return new ResponseEntity<Collection<Produto>>(produtos, HttpStatus.OK);
		}
		else {
			List<Produto> produtos = produtoService.buscarNomeMaiorPreco(nome, preco);
			if(produtos.isEmpty()) return new ResponseEntity<Collection<Produto>>(HttpStatus.NOT_FOUND);
			else return new ResponseEntity<Collection<Produto>>(produtos, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produto/getByNomeAndPrecoMenor", method = RequestMethod.GET)
	@JsonView(View.ProdutoGeral.class)
	public ResponseEntity<Collection<Produto>> getByNomeAndPrecoMenor(@RequestParam(value="nome", defaultValue="") String nome, @RequestParam(value="preco", defaultValue="0") double preco) {
		if(nome.equals("")) {
			List<Produto> produtos = produtoService.buscarMenorPreco(preco);
			if(produtos.isEmpty()) return new ResponseEntity<Collection<Produto>>(HttpStatus.NOT_FOUND);
			else return new ResponseEntity<Collection<Produto>>(produtos, HttpStatus.OK);
		}
		else {
			List<Produto> produtos = produtoService.buscarNomeMenorPreco(nome, preco);
			if(produtos.isEmpty()) return new ResponseEntity<Collection<Produto>>(HttpStatus.NOT_FOUND);
			else return new ResponseEntity<Collection<Produto>>(produtos, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/produto/getAll", method = RequestMethod.GET)
	@JsonView(View.ProdutoCompleto.class)
	public ResponseEntity<Collection<Produto>> getAll() {
		return new ResponseEntity<Collection<Produto>>(produtoService.todos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/cadastrar", method = RequestMethod.POST)
	@JsonView(View.ProdutoCompleto.class)
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto, HttpServletRequest request, HttpServletResponse response) {
		try {
			produto = produtoService.inlcuir(produto);
			response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/produto/get/?nome=" + produto.getNome());
			return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
		}
		catch(DataIntegrityViolationException e){
			return new ResponseEntity<Produto>(HttpStatus.CONFLICT);
		}
	}
}