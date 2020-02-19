package br.com.crud.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Pessoa;

@Repository
public class DocumentoRepository {

	// Attributes
	@Autowired
	private EntityManager entityManager;
	
	// Methods
	public List<Documento> findAll() {
		return entityManager.createQuery("from Documento").getResultList();
	}
	
	public Documento findById(Integer id) {
		return entityManager.find(Documento.class, id);
	}
	
	public List<Documento> findByType (String typeToFind) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Documento> criteriaQuery = criteriaBuilder.createQuery(Documento.class);
		Root<Documento> documento = criteriaQuery.from(Documento.class);
		
		criteriaQuery.where(criteriaBuilder.equal(documento.<String>get("tipoDocumento"), typeToFind));
		TypedQuery<Documento> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public Documento findByValue (String valueToFind) throws NoResultException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Documento> criteriaQuery = criteriaBuilder.createQuery(Documento.class);
		Root<Documento> documento = criteriaQuery.from(Documento.class);
		
		criteriaQuery.where(criteriaBuilder.equal(documento.<String>get("valorDocumento"), valueToFind));
		TypedQuery<Documento> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getSingleResult();
	}
	
	public Documento save (Documento documento) {
		entityManager.persist(documento);
		return documento;
	}
	
	public Documento remove (Documento documento) {
		entityManager.remove(documento);
		return documento;
	}
	
	public Documento update (Documento documento) {
		entityManager.merge(documento);
		return documento;
	}
}
