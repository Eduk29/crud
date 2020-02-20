package br.com.crud.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.Documento;
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
		
		public List<Endereco> findByCEP (String cepToFind) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
			Root<Endereco> endereco = criteriaQuery.from(Endereco.class);
			
			criteriaQuery.where(criteriaBuilder.equal(endereco.<String>get("cep"), cepToFind));
			TypedQuery<Endereco> typedQuery = entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
		}
		
}
