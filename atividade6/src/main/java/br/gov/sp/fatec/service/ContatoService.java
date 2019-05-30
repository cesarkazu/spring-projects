package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Contato;

public interface ContatoService {

	public Contato salvar(Contato autorizacao);

	public boolean excluir(Long idAnotacao);

	public List<Contato> todos();

	public List<Contato> buscarPorUsuario(String nome);

	public Contato buscarPorId(Long idAnotacao);
	
	public List<Contato> todosPorUsuario(Long idUsuario);
	
	public boolean alterar(Long idContato, String nome, String telefone, String endereco, String email);

}