package br.com.crud.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
