/**
 * 
 */
package br.sp.gov.fatec;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.gov.sp.fatec.model.*;
import br.gov.sp.fatec.repository.*;

/**
 * @author Emanuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/CursoRepositoryTest-context.xml" })
@Rollback
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepo;

	public void setCursoRepo(CursoRepository cursoRepo) {
		this.cursoRepo = cursoRepo;
	}

	@Test
	@Rollback
	public void testeInsercao() {
		Curso curso = new Curso();
		curso.setNome("Análise e Desenvolvimento de Sistemas");
		curso.setSigla("ADS");
		cursoRepo.save(curso);
		assertNotNull(cursoRepo.findTop1BySiglaContains("ADS"));
		cursoRepo.delete(cursoRepo.findTop1BySiglaContains("ADS"));
	}

	@Test
	@Rollback
	public void testeDeleta() {
		Curso curso = new Curso();
		curso.setNome("Banco de Dados");
		curso.setSigla("BD");
		cursoRepo.save(curso);
		cursoRepo.delete(cursoRepo.findTop1BySiglaContains("BD"));
		assertNull(cursoRepo.findTop1BySiglaContains("BD"));
	}
}