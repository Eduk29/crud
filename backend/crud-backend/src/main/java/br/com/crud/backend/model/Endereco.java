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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "crud_endereco")
public class Endereco {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_generator")
	@SequenceGenerator(name = "seq_generator", sequenceName = "seq_crud_endereco", allocationSize = 1)
	@Column(name = "ID_ENDERECO", nullable = false, precision = 8, scale = 0)
	private Integer id;

	@JsonIgnoreProperties(value = "enderecos", allowSetters = true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "crud_pessoa_endereco", joinColumns = { @JoinColumn(name = "id_endereco") }, inverseJoinColumns = { @JoinColumn(name = "id_pessoa") })
	private List<Pessoa> pessoa;
	
	@Column(name = "CEP_ENDERECO", nullable = false, length = 255)
	private String cep;
	
	@Column(name = "COMPLEMENTO_ENDERECO", nullable = true, length = 255)
	private String complemento;
	
	@Column(name = "CIDADE_ENDERECO", nullable = false, length = 255)
	private String cidade;
	
	@Column(name = "ESTADO_ENDERECO", nullable = false, length = 255)
	private String estado;
	
	@Column(name = "LOGRADOURO_ENDERECO", nullable = false, length = 255)
	private String logradouro;
	
	@Column(name = "NUMERO_ENDERECO", nullable = false, length = 255)
	private String numero;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoa = pessoas;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	// Methods
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", Rua:" + logradouro + ", " + numero + " - " + cidade + " - " + estado + " CEP: " + cep + "]";
	}
}
