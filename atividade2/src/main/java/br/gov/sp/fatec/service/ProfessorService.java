package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Professor;

public interface ProfessorService {

	public boolean incluirProfessor(String nome);

	public List<Professor> listarProfessores();
	
	public Professor ultimoProfessor();
	
	public Professor buscarTop1(String nome);
	
	public Professor buscarProfessorNome(String nome);

}