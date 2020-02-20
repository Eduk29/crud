package br.com.crud.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "crud_pessoa")
public class Pessoa {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "seq_crud_pessoa", allocationSize = 1)
	@Column(name = "ID_PESSOA", nullable = false, precision = 8, scale = 0)
	private Integer id;

	@Column(name = "NOME_PESSOA", nullable = false, length = 255)
	private String nome;

	@Column(name = "GENERO_PESSOA", nullable = false, length = 255)
	private String genero;

	@JsonIgnoreProperties(value = "pessoa", allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pessoa")
	@JsonInclude(Include.NON_NULL)
	private List<Documento> documentos;

	@JsonIgnoreProperties(value = "pessoa", allowSetters = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "crud_pessoa_endereco", joinColumns = { @JoinColumn(name = "id_pessoa") }, inverseJoinColumns = { @JoinColumn(name = "id_endereco") })
	private List<Endereco> enderecos;

	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	// Methods
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", genero=" + genero + "documento= " + documentos + "endereco= " + enderecos + "]";
	}
}
