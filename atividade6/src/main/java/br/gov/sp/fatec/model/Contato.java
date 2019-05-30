package br.gov.sp.fatec.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "CONTATO")
public class Contato implements Serializable {

	private static final long serialVersionUID = -4175224450033765996L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_CONTATO")
	@JsonView(View.Contato.class)
	private Long id;

	@Column(name = "NOME_CONTATO", length = 50, nullable = false)
	@JsonView(View.Contato.class)
	private String nome;

	@Column(name = "TELEFONE_CONTATO", length = 20, nullable = false)
	@JsonView(View.Contato.class)
	private String telefone;

	@Column(name = "EMAIL_CONTATO", length = 50, nullable = false)
	@JsonView(View.Contato.class)
	private String email;

	@Column(name = "ENDERECO_CONTATO", length = 100, nullable = false)
	@JsonView(View.Contato.class)
	private String endereco;

	@Column(name = "CRIACAO_CONTATO", nullable = false)
	@JsonView(View.Contato.class)
	private String data;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CRIACAO_ID_USUARIO")
	@JsonView(View.Contato.class)
	private Usuario usuario;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}