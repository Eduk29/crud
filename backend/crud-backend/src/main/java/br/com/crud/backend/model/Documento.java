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
@Table(name = "crud_documento")
public class Documento {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "seq_crud_documento", allocationSize = 1)
	@Column(name = "ID_DOCUMENTO", nullable = false, precision = 8, scale = 0)
	private Integer id;

	@Column(name = "TIPO_DOCUMENTO", nullable = false, length = 255)
	private String tipoDocumento;

	@Column(name = "VALOR_DOCUMENTO", nullable = false, length = 255)
	private String valorDocumento;

	@JsonIgnoreProperties(value = "documentos", allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	@JsonInclude(Include.NON_NULL)
	private Pessoa pessoa;

	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(String valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	// Methods
	@Override
	public String toString() {
		return "Documento [id=" + id + ", type=" + tipoDocumento + ", value=" + valorDocumento + "]";
	}
}
