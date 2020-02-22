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
import br.com.crud.backend.model.TipoDocumento;

@Repository
public class TipoDocumentoRepository {

	// Attributes
	@Autowired
	private EntityManager entityManager;
	
	// Methods
	public List<TipoDocumento> findAll() {
		return entityManager.createQuery("from TipoDocumento").getResultList();
	}
	
	public TipoDocumento findyByType(String typeToFind) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoDocumento> criteriaQuery = criteriaBuilder.createQuery(TipoDocumento.class);
		Root<TipoDocumento> tipoDocumento = criteriaQuery.from(TipoDocumento.class);
		
		criteriaQuery.where(criteriaBuilder.equal(tipoDocumento.<String>get("chave"), typeToFind));
		TypedQuery<TipoDocumento> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getSingleResult();
	}
	
	public TipoDocumento findTipoDocumentoById (Integer id) {
		return entityManager.find(TipoDocumento.class, id);
	}
}
