/**
 * 
 */
package br.gov.sp.fatec;

/**
 * @author Emanuel
 *
 */
public class Print {
	
	/**
	 * Mensagem a ser exibida na placa
	 */
	private Info info;
	
	/**
	 * Construtor
	 */
	public Print() {
	}
	
	/**
	 * Retorna o texto da placa
	 * @return Texto da placa
	 */
	public String ler() {
		return "[ <\nAluno\n" + info.getInfo() + "\n> ]\n";
	}
	
	/**
	 * Setter para mensagem
	 * @param mensagem
	 */
	public void setInfo(Info info) {
		this.info = info;
	}

}
