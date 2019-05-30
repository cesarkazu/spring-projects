/**
 * 
 */
package br.sp.gov.fatec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.gov.sp.fatec.model.Curso;
import br.gov.sp.fatec.repository.*;
import br.gov.sp.fatec.service.*;

/**
 * @author Emanuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
@Rollback
public class CursoServiceTest {

	@Autowired
	private CursoRepository cursoRepo;

	@Autowired
	private CursoService cursoService;

	public void setCursoRepo(CursoRepository cursoRepo) {
		this.cursoRepo = cursoRepo;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@Test
	@Rollback
	public void testeIncluirCurso() {
		cursoService.incluirCurso("Análise e Desenvolvimento de Sistemas", "ADS");
		cursoService.incluirCurso("Banco de Dados", "BD");
		assertEquals(cursoRepo.findTop1BySiglaContains("BD").getSigla(),"BD");
		cursoRepo.delete(cursoService.buscarTop1Sigla("ADS"));
		cursoRepo.delete(cursoService.buscarTop1Sigla("BD"));
	}

	@Test
	@Rollback
	public void testeBuscarTop1Sigla() {
		Curso curso = new Curso();
		curso.setNome("Análise e Desenvolvimento de Sistemas");
		curso.setSigla("ADS");
		cursoRepo.save(curso);
		Curso cursoEncontrado = cursoService.buscarTop1Sigla("ADS");
		assertEquals(cursoEncontrado.getNome(), "Análise e Desenvolvimento de Sistemas");
		cursoRepo.delete(cursoEncontrado);
	}

	@Test
	@Rollback
	public void testeDeletaCursoSigla() {
		Curso curso = new Curso();
		curso.setNome("Análise e Desenvolvimento de Sistemas");
		curso.setSigla("ADS");
		cursoRepo.save(curso);
		cursoService.deletaCursoSigla("ADS");
		assertNull(cursoService.buscarTop1Sigla("ADS"));
	}
}