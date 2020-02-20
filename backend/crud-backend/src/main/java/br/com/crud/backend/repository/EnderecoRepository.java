package br.com.crud.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.Endereco;

@Repository
public class EnderecoRepository {

	// Attributes
		@Autowired
		private EntityManager entityManager;
		
		// Methods
		public List<Endereco> findAll() {
			return entityManager.createQuery("from Endereco").getResultList();
		}
		
		public Endereco save(Endereco endereco) {
			entityManager.persist(endereco);
			return endereco;
		}
		
}
