package br.gov.sp.fatec;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import br.gov.sp.fatec.model.Aluno;
import br.gov.sp.fatec.model.Curso;
import br.gov.sp.fatec.model.Disciplina;
import br.gov.sp.fatec.model.Professor;
import br.gov.sp.fatec.service.AlunoService;
import br.gov.sp.fatec.service.CursoService;
import br.gov.sp.fatec.service.DisciplinaService;
import br.gov.sp.fatec.service.ProfessorService;

public class App 
{

	public static void main( String[] args )
	{
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(	"applicationContext.xml");

		AlunoService alunoService = (AlunoService)context.getBean("alunoService");
		try {
			if(alunoService.incluirAluno("Cesar Ando", "1460481511090")) System.out.println("Aluno inserido.");
		}
		catch(Exception e) {
			System.out.println("Erro esperado! Rollback realizado!");
			//e.printStackTrace();
		}
		if(alunoService.incluirAluno("Giovani Renato", "1460481511001")) System.out.println("Aluno inserido.");
		if(alunoService.incluirAluno("Guilherme Aranha", "1460481511002")) System.out.println("Aluno inserido.");
		if(alunoService.incluirAluno("Bárbara Araújo", "1460481511003")) System.out.println("Aluno inserido.");
		if(alunoService.incluirAluno("Pedro Luis", "1460481511004")) System.out.println("Aluno inserido.");
		for(Aluno a: alunoService.listarAlunos()) {
			System.out.println("Aluno encontrado: " + a.getNome());
		}

		CursoService cursoService = (CursoService)context.getBean("cursoService");
		if(cursoService.incluirCurso("Análise e Desenvolvimento de Sistemas", "ADS")) System.out.println("Curso inserido.");
		if(cursoService.incluirCurso("Banco de Dados", "BD")) System.out.println("Curso inserido.");
		for(Curso c: cursoService.listarCursos()) {
			System.out.println("Curso encontrado: " + c.getNome());
		}

		ProfessorService professorService = (ProfessorService)context.getBean("professorService");
		if(professorService.incluirProfessor("Mineda")) System.out.println("Professor inserido.");
		if(professorService.incluirProfessor("Arakaki")) System.out.println("Professor inserido.");
		for(Professor p: professorService.listarProfessores()) {
			System.out.println("Professor encontrado: " + p.getNome());
		}

		List<Aluno> alunos1 = new ArrayList<Aluno>();
		alunos1.add(alunoService.buscarTop1("Cesar"));
		alunos1.add(alunoService.buscarTop1("Giovani"));
		List<Aluno> alunos2 = new ArrayList<Aluno>();
		alunos2.add(alunoService.buscarTop1("Bárbara"));
		alunos2.add(alunoService.buscarTop1("Pedro"));
		DisciplinaService disciplinaService = (DisciplinaService)context.getBean("disciplinaService");
		disciplinaService.incluirDisciplina("Tópicos Especiais em Informática", cursoService.buscarCursoSigla("ADS"), professorService.buscarTop1("Mineda"),alunos1);
		disciplinaService.incluirDisciplina("Matemática Discreta", cursoService.buscarTop1Sigla("ADS"), professorService.buscarTop1("Arakaki"),alunos2);
		for(Disciplina d: disciplinaService.listarDisciplinas()) {
			System.out.println("");
			System.out.println("Disciplina encontrada: ");
			System.out.println("Nome: " + d.getNome());
			System.out.println("Curso: " + d.getCurso().getNome());
			System.out.println("Sigla: " + d.getCurso().getSigla());
			System.out.println("Professor: " + d.getProfessor().getNome());
			for(Aluno a: d.getAlunos()) {
				System.out.println("Aluno: " + a.getNome());
			}
		}
		System.out.println("");

		//disciplinaService.buscarTop1("Tópicos Especiais em Informática");
		Disciplina d = disciplinaService.incluirAlunoDisciplina("Tópicos Especiais em Informática", "Guilherme");
		System.out.println("");
		System.out.println("Disciplina encontrada: ");
		System.out.println("Nome: " + d.getNome());
		System.out.println("Curso: " + d.getCurso().getNome());
		System.out.println("Sigla: " + d.getCurso().getSigla());
		System.out.println("Professor: " + d.getProfessor().getNome());
		for(Aluno a: d.getAlunos()) {
			System.out.println("Aluno: " + a.getNome());
		}

		/*
		// Recupera os repositorios
		AutorizacaoRepository autorizacaoRepo = (AutorizacaoRepository) context.getBean("autorizacaoRepository");
		UsuarioRepository usuarioRepo = (UsuarioRepository) context.getBean("usuarioRepository");

		// Cria autorizacoes
		Autorizacao autorizacao1 = new Autorizacao();
		autorizacao1.setNome("Usuario");

		autorizacaoRepo.save(autorizacao1);

		Autorizacao autorizacao2 = new Autorizacao();
		autorizacao2.setNome("Administrador");

		autorizacaoRepo.save(autorizacao2);

		// Cria um usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Paulo");
		usuario.setSenha("senha");
		usuario.setAutorizacoes(new ArrayList<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao1);
		usuario.getAutorizacoes().add(autorizacao2);

		usuarioRepo.save(usuario);

		System.out.println("Id: " + usuario.getId());

		// Cria usuario usando servico (FORMA CORRETA DE FAZER!)
		UsuarioService usuarioService = (UsuarioService)context.getBean("usuarioService");

		usuario = usuarioService.incluirUsuario("Ana", "123456", "Administrador");

		System.out.println("Id: " + usuario.getId());

		// Realiza varias consultas
		System.out.println("Resultado da busca 1: " + usuarioRepo.findByNome("Paulo").getNome());

		System.out.println("Resultado da busca 2: " + usuarioRepo.findTop1ByNomeContains("au").getNome());

		for(Usuario us: usuarioRepo.findByIdGreaterThan(0l)) {
			System.out.println("Usuario encontrado: " + us.getNome());
		}


		for(Usuario us: usuarioRepo.findByNomeContainsIgnoreCaseOrAutorizacoesNomeContainsIgnoreCase("n", "m")) {
			System.out.println("Usuario encontrado 2 (or): " + us.getNome());
		}

		for(Usuario us: usuarioRepo.findByAutorizacoesNomeContainsIgnoreCase("Usuario")) {
			System.out.println("Usuario encontrado 3: " + us.getNome());
		}

		for(Usuario us: usuarioRepo.buscaUsuario("a")) {
			System.out.println("Usuario encontrado 4: " + us.getNome());
		}

		// Gerando erro de proposito ao tentar criar dois usuarios com mesmo nome
		try {
			usuarioService.incluirDoisUsuarios("Luis", "Luis");
		}
		catch(Exception e) {
			System.out.println("Erro esperado! Rollback realizado!");
			e.printStackTrace();
		}

		// Mesmo o erro ocorrendo somente no segundo insert, nada se grava por conta da transacao
		usuario = usuarioRepo.findByNome("Luis");
		if(usuario == null) {
			System.out.println("Usuario Luis nao foi encontrado!");
		}

		// Exclui usuario
		usuarioRepo.delete(usuarioRepo.findByNome("Paulo"));
		usuarioRepo.delete(usuarioRepo.findByNome("Ana"));
		// Exclui autorizacoes
		autorizacaoRepo.delete(autorizacao1);
		autorizacaoRepo.delete(autorizacao2);
		 */
		context.close();

	}

}