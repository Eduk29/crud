package br.com.crud.backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.crud.backend.model.Documento;
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
	
	public Pessoa findById(Integer id) {
		return entityManager.find(Pessoa.class, id);
	}
	
	// Source => https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-select.html
	public List<Pessoa> findByName(String nameToFind) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> pessoa = criteriaQuery.from(Pessoa.class);
		
		criteriaQuery.where(criteriaBuilder.like(pessoa.<String>get("nome"), "%" + nameToFind + "%"));
		TypedQuery<Pessoa> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public List<Pessoa> findByGender(String genderToFind) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> pessoa = criteriaQuery.from(Pessoa.class);
		
		criteriaQuery.where(criteriaBuilder.like(pessoa.<String>get("genero"), "%" + genderToFind + "%"));
		TypedQuery<Pessoa> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public Pessoa save(Pessoa pessoa) {
		entityManager.persist(pessoa);
		return pessoa;
	}
	
	public Pessoa remove(Pessoa pessoa) {
		entityManager.remove(pessoa);
		return pessoa;
	}
	
	public Pessoa update (Pessoa pessoa) {
		entityManager.merge(pessoa);
		return pessoa;
	}
}
