package br.com.crud.backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.TipoContato;

@Repository
public class TipoContatoRepository {

	// Attributes
	@Autowired
	private EntityManager entityManager;
	
	// Methods
	public List<TipoContato> findAll() {
		return entityManager.createQuery("from TipoContato").getResultList();
	}
	
	public List<TipoContato> findByType(String typeToFind) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoContato> criteriaQuery = criteriaBuilder.createQuery(TipoContato.class);
		Root<TipoContato> tipoContato = criteriaQuery.from(TipoContato.class);
		
		criteriaQuery.where(criteriaBuilder.equal(tipoContato.get("chave"), typeToFind));
		TypedQuery<TipoContato> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public TipoContato findById(Integer id) {
		return entityManager.find(TipoContato.class, id);
	}
}
