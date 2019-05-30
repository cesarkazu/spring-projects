package br.gov.sp.fatec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Aluno;
import br.gov.sp.fatec.repository.AlunoRepository;

@Service("alunoService")
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepo;

	public void setAlunoRepo(AlunoRepository alunoRepo) {
		this.alunoRepo = alunoRepo;
	}

	@Override
	@Transactional
	public boolean incluirAluno(String nome, String ra) {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setRa(ra);
		try {
			alunoRepo.save(aluno);
			return true;

		} catch (DataIntegrityViolationException e) {
			return false;
		} catch(Exception e) {
			System.out.println("Erro esperado! Rollback realizado!");
			return false;
		}
	}

	@Override
	@Transactional
	public List<Aluno> listarAlunos() {
		return (List<Aluno>) alunoRepo.findAll();
	}

	@Override
	@Transactional
	public Aluno buscarTop1(String nome) {
		return alunoRepo.findTop1ByNomeContains(nome);
	}

}