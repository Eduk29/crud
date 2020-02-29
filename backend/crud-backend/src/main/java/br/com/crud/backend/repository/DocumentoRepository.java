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
		return this.entityManager.createQuery("from Documento").getResultList();
	}
	
	public Documento findById(Integer id) {
		return this.entityManager.find(Documento.class, id);
	}
	
	public List<Documento> findByType (String typeToFind) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Documento> criteriaQuery = criteriaBuilder.createQuery(Documento.class);
		Root<Documento> documento = criteriaQuery.from(Documento.class);
		
		criteriaQuery.where(criteriaBuilder.equal(documento.<String>get("tipoDocumento"), typeToFind));
		TypedQuery<Documento> typedQuery = this.entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public Documento findByValue (String valueToFind) throws NoResultException {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Documento> criteriaQuery = criteriaBuilder.createQuery(Documento.class);
		Root<Documento> documento = criteriaQuery.from(Documento.class);
		
		criteriaQuery.where(criteriaBuilder.equal(documento.<String>get("valorDocumento"), valueToFind));
		TypedQuery<Documento> typedQuery = this.entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getSingleResult();
	}
	
	public Documento save (Documento documento) {
		this.entityManager.persist(documento);
		return documento;
	}
	
	public Documento remove (Documento documento) {
		this.entityManager.remove(documento);
		return documento;
	}
	
	public Documento update (Documento documento) {
		return this.entityManager.merge(documento);
	}
}
