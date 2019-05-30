package br.gov.sp.fatec;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(	"applicationContext.xml");
 
		Print obj = (Print) context.getBean("placa");
		System.out.println(obj.ler());
		
        Aluno aluno2 = (Aluno) context.getBean("aluno2"); 
        System.out.println(aluno2.getInfo());
        System.out.println();
        Aluno aluno3 = (Aluno) context.getBean("aluno3"); 
        System.out.println(aluno3.getInfo());
        System.out.println();
        
        aluno2.setNome("Pedro Luis");
        System.out.println(aluno2.getInfo());
        System.out.println();
		
		context.close();
	}
}
