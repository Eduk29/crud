package br.com.crud.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "crud_contato")
public class Contato {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "seq_crud_contato", allocationSize = 1)
	@Column(name = "ID_CONTATO", nullable = false, precision = 8, scale = 0)
	private Integer id;
	
	@JsonIgnoreProperties(value = "contatos", allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	@JsonInclude(Include.NON_NULL)
	private Pessoa pessoa;
	
	@Column(name="ID_TIPO_CONTATO", nullable = false, precision = 8, scale = 0)
	private Integer tipo;
	
	@Column(name = "VALOR_CONTATO", nullable = false, length = 255)
	private String valor;
	
	@Column(name = "IS_PRINCIPAL_CONTATO", nullable = false)
	private Boolean isPrincipal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getIsPrincipal() {
		return isPrincipal;
	}

	public void setIsPrincipal(Boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}
}
