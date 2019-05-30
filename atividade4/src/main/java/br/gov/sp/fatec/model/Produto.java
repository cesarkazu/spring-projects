package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.view.View;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "PRODUTO")
public class Produto {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idProduto")
	@JsonView(View.ProdutoCompleto.class)
	private Long id;

	@Column(name = "nomeProduto", unique=true, length = 50, nullable = false)
	@JsonView(View.ProdutoNome.class)
	private String nome;

	@Column(name = "descProduto", length = 255, nullable = false)
	@JsonView({View.ProdutoDesc.class, View.ProdutoGeral.class})
	private String desc;

	@Column(name = "precoProduto", nullable = false)
	@JsonView({View.ProdutoPreco.class, View.ProdutoVenda.class})
	private double preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}