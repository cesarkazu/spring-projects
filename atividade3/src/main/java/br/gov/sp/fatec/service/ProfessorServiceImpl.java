package br.gov.sp.fatec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Professor;
import br.gov.sp.fatec.repository.ProfessorRepository;

@Service("professorService")
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorRepository professorRepo;

	public void setProfessorRepo(ProfessorRepository professorRepo) {
		this.professorRepo = professorRepo;
	}

	@Override
	@Transactional
	public boolean incluirProfessor(String nome) {
		Professor professor = new Professor();
		professor.setNome(nome);
		try {
			professorRepo.save(professor);
			return true;

		} catch (DataIntegrityViolationException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Professor> listarProfessores() {
		return (List<Professor>) professorRepo.findAll();
	}

	@Override
	@Transactional
	public Professor ultimoProfessor() {
		return professorRepo.findFirstByOrderByIdDesc();
	}

	@Override
	@Transactional
	public Professor buscarTop1(String nome) {
		return professorRepo.findTop1ByNomeContains(nome);
	}

	@Override
	@Transactional
	public Professor buscarProfessorNome(String nome) {
		return professorRepo.buscaProfessorNome(nome);
	}

}