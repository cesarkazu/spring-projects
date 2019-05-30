package br.gov.sp.fatec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Curso;
import br.gov.sp.fatec.repository.CursoRepository;

@Service("cursoService")
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepo;

	public void setCursoRepo(CursoRepository cursoRepo) {
		this.cursoRepo = cursoRepo;
	}

	@Override
	@Transactional
	public boolean incluirCurso(String nome, String sigla) {
		Curso curso = new Curso();
		curso.setNome(nome);
		curso.setSigla(sigla);
		try {
			cursoRepo.save(curso);
			return true;

		} catch (DataIntegrityViolationException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Curso> listarCursos() {
		return (List<Curso>) cursoRepo.findAll();
	}

	@Override
	@Transactional
	public Curso ultimoCurso() {
		return cursoRepo.findFirstByOrderByIdDesc();
	}

	@Override
	@Transactional
	public Curso buscarTop1Sigla(String sigla) {
		return cursoRepo.findTop1BySiglaContains(sigla);
	}

	@Override
	@Transactional
	public Curso buscarCursoSigla(String sigla) {
		return cursoRepo.buscaCursoSigla(sigla);
	}

}