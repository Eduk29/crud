package br.com.crud.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.Contato;
import br.com.crud.backend.model.Documento;

@Repository
public class ContatoRepository {

	// Attributes
	@Autowired
	private EntityManager entityManager;
	
	// Methods
	public List<Contato> findAll() {
		return entityManager.createQuery("from Contato").getResultList();
	}
	
	public Contato findById(Integer id) {
		return entityManager.find(Contato.class, id);
	}
	
	public List<Contato> findByValue(String valueToFind) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Contato> criteriaQuery = criteriaBuilder.createQuery(Contato.class);
		Root<Contato> contato = criteriaQuery.from(Contato.class);
		criteriaQuery.where(criteriaBuilder.equal(contato.get("valor"), valueToFind));
		
		TypedQuery<Contato> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public Contato save (Contato contato) {
		entityManager.persist(contato);
		return contato;
	}
	
	public Contato remove (Contato contato) {
		entityManager.remove(contato);
		return contato;
	}
	
	public Contato update (Contato contato) {
		return entityManager.merge(contato);
	}
}
