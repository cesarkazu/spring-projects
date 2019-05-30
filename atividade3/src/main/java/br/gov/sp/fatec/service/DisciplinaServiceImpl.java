package br.gov.sp.fatec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Aluno;
import br.gov.sp.fatec.model.Curso;
import br.gov.sp.fatec.model.Disciplina;
import br.gov.sp.fatec.model.Professor;
import br.gov.sp.fatec.repository.AlunoRepository;
import br.gov.sp.fatec.repository.DisciplinaRepository;

@Service("disciplinaService")
public class DisciplinaServiceImpl implements DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepo;

	@Autowired
	private AlunoRepository alunoRepo;

	public void setDisciplinaRepo(DisciplinaRepository disciplinaRepo) {
		this.disciplinaRepo = disciplinaRepo;
	}

	public void setAlunoRepo(AlunoRepository alunoRepo) {
		this.alunoRepo = alunoRepo;
	}

	@Override
	@Transactional
	public boolean incluirDisciplina(String nome, Curso curso, Professor professor, List<Aluno> alunos) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(nome);
		disciplina.setCurso(curso);
		disciplina.setProfessor(professor);
		disciplina.setAlunos(alunos);
		try {
			disciplinaRepo.save(disciplina);
			return true;

		} catch (DataIntegrityViolationException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Disciplina> listarDisciplinas() {
		return (List<Disciplina>) disciplinaRepo.findAll();
	}

	@Override
	@Transactional
	public Disciplina buscarTop1(String nome) {
		return disciplinaRepo.findTop1ByNomeContains(nome);
	}

	@Override
	@Transactional
	public Disciplina incluirAlunoDisciplina(String nomeDisciplina, String nomeAluno) {
		Disciplina disciplina = disciplinaRepo.findTop1ByNomeContains(nomeDisciplina);
		List<Aluno> alunos = disciplina.getAlunos();
		alunos.add(alunoRepo.findTop1ByNomeContains(nomeAluno));
		disciplina.setAlunos(alunos);
		disciplinaRepo.save(disciplina);
		return disciplina;
	}

}