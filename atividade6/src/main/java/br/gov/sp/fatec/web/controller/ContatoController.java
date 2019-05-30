package br.gov.sp.fatec.web.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.gov.sp.fatec.model.Contato;
import br.gov.sp.fatec.service.ContatoService;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/contato")
@CrossOrigin
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	public void setContatoService(ContatoService anotacaoService) {
		this.contatoService = anotacaoService;
	}

	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@JsonView(View.Contato.class)
	public ResponseEntity<Contato> getById(@RequestParam(value="id", defaultValue="1") Long id) {
		Contato retorno = contatoService.buscarPorId(id);
		if(retorno == null) {
			return new ResponseEntity<Contato>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Contato>(retorno, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Contato.class)
	public ResponseEntity<Collection<Contato>> getAll() {
		return new ResponseEntity<Collection<Contato>>(contatoService.todos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllById", method = RequestMethod.GET)
	@JsonView(View.Contato.class)
	public ResponseEntity<Collection<Contato>> getAllById(@RequestParam(value="id", defaultValue="1") Long id) {
		List<Contato> retorno = contatoService.todosPorUsuario(id);
		if(retorno.isEmpty()) {
			return new ResponseEntity<Collection<Contato>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Contato>>(retorno, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@JsonView(View.Contato.class)
	public ResponseEntity<String> delete(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = contatoService.excluir(id);;
		if(success) {
			return new ResponseEntity<String>("Contato: "+id+" deletado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao deletar o contato: "+id, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.GET)
	@JsonView(View.Contato.class)
	public ResponseEntity<String> alterar(@RequestParam(value="id") Long id, @RequestParam(value="nome") String nome, @RequestParam(value="telefone") String telefone, @RequestParam(value="endereco") String endereco, @RequestParam(value="email") String email, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = contatoService.alterar(id, nome, telefone, endereco, email);
		if(success) {
			return new ResponseEntity<String>("Contato: "+id+" alterado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao alterado o contato: "+id, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Contato.class)
	public ResponseEntity<Contato> salvar(@RequestBody Contato contato, UriComponentsBuilder uriComponentsBuilder) {
		contato = contatoService.salvar(contato);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + contato.getId()).build().toUri());
		return new ResponseEntity<Contato>(contato, responseHeaders, HttpStatus.CREATED);
	}
}