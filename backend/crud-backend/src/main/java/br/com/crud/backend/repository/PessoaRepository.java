package br.com.crud.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.Pessoa;

@Repository
public class PessoaRepository {

	// Attributes
	@Autowired
	private EntityManager entityManager;
	
	// Methods
	public List<Pessoa> findAll() {
		return entityManager.createQuery("from Pessoa").getResultList();
	}
}
