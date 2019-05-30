package br.gov.sp.fatec.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Contato;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.ContatoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("contatoService")
@Transactional
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	private ContatoRepository contatoRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	public void setAnotacaoRepo(ContatoRepository contatoRepo) {
		this.contatoRepo = contatoRepo;
	}

	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Contato salvar(Contato contato) {
		if(contato.getUsuario() != null) {
			Usuario usuario = contato.getUsuario();
			if(usuario.getId() != null) {
				usuario = usuarioRepo.findById(usuario.getId()).get();
			}
			else {
				usuario = usuarioRepo.save(usuario);
			}
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		contato.setData(simpleDateFormat.format(new Date()));
		return contatoRepo.save(contato);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean excluir(Long idContato) {
		Optional<Contato> contato =  contatoRepo.findById(idContato);
		if(contato.isPresent()) {
			contatoRepo.delete(contato.get());
			return true;
		}
		return false;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean alterar(Long idContato, String nome, String telefone, String endereco, String email) {
		Optional<Contato> contato = contatoRepo.findById(idContato);
		if(contato.isPresent()) {
			Contato c = contato.get();
			c.setNome(nome);
			c.setTelefone(telefone);
			c.setEndereco(endereco);
			c.setEmail(email);
			contatoRepo.save(c);
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Contato> todos() {
		List<Contato> retorno = new ArrayList<Contato>();
		for(Contato contato: contatoRepo.findAll()) {
			retorno.add(contato);
		}
		return retorno;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Contato> todosPorUsuario(Long idUsuario) {
		List<Contato> retorno = new ArrayList<Contato>();
		for(Contato contato: contatoRepo.findAll()) {
			if(contato.getUsuario().getId()==idUsuario) retorno.add(contato);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Contato> buscarPorUsuario(String nome) {
		if(nome == null || nome.isEmpty()) {
			return todos();
		}
		return contatoRepo.findByUsuarioNome(nome);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Contato buscarPorId(Long idContato) {
		Optional<Contato> contato = contatoRepo.findById(idContato);
		if(contato.isPresent()) {
			return contato.get();
		}
		return null;
	}
}