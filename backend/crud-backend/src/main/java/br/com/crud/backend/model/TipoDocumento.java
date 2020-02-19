package br.com.crud.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "crud_tipo_documento")
public class TipoDocumento {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "seq_crud_tipo_documento", allocationSize = 1)
	@Column(name = "ID_TIPO_DOCUMENTO", nullable = false, precision = 8, scale = 0)
	private Integer id;
	
	@Column(name = "CHAVE_TIPO_DOCUMENTO", nullable = false, length = 255)
	private String chave;

	@Column(name = "VALOR_TIPO_DOCUMENTO", nullable = false, length = 255)
	private String valor;
	
	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	// Methods
	@Override
	public String toString() {
		return "Tipo Documento [id=" + id + ", chave=" + chave + ", valor=" + valor + "]";
	}
}
