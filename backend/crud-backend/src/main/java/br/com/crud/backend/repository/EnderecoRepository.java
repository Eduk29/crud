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
			return this.entityManager.createQuery("from Endereco").getResultList();
		}
		
		public Endereco save(Endereco endereco) {
			this.entityManager.persist(endereco);
			return endereco;
		}
		
		public Endereco remove(Endereco endereco) {
			this.entityManager.remove(endereco);
			return endereco;
		}
		
		public Endereco update(Endereco endereco) {
			this.entityManager.merge(endereco);
			return endereco;
		}
		
		public Endereco findById(Integer id) {
			return this.entityManager.find(Endereco.class, id);
		}
		
		public List<Endereco> findByCEP (String cepToFind) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
			Root<Endereco> endereco = criteriaQuery.from(Endereco.class);
			
			criteriaQuery.where(criteriaBuilder.equal(endereco.<String>get("cep"), cepToFind));
			TypedQuery<Endereco> typedQuery = this.entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
		}
		
		public List<Endereco> findByEstado (String estadoToFind) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
			Root<Endereco> endereco = criteriaQuery.from(Endereco.class);
			
			criteriaQuery.where(criteriaBuilder.like(endereco.<String>get("estado"), "%" + estadoToFind + "%"));
			TypedQuery<Endereco> typedQuery = this.entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
		}
		
		public List<Endereco> findByCidade (String cidadeToFind) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
			Root<Endereco> endereco = criteriaQuery.from(Endereco.class);
			
			criteriaQuery.where(criteriaBuilder.like(endereco.<String>get("cidade"), "%" + cidadeToFind + "%"));
			TypedQuery<Endereco> typedQuery = this.entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
		}
}
