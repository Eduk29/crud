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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
		
		// Methods
		@Override
		public String toString() {
			return "Pessoa [id=" + id + ", nome=" + nome + "]";
		}
}
