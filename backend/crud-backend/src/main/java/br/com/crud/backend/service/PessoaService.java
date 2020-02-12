package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.PessoaRepository;

@Service				// Expoe a classe como um serviço do spring
@Transactional 			// Marca a classe com gerenciamento de transações (SPRING)
public class PessoaService {

	// Attributes
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> find(String filter) {
		switch (getModeSearch(filter)) {
			case "name":
				return findByName(getParamSearch(filter));
				
			case "gender":
				return findByGender(getParamSearch(filter));

			case "id":
				Pessoa pessoaResult = findById(Integer.parseInt(getParamSearch(filter)));
				List<Pessoa> listPessoa = new ArrayList<>();
				listPessoa.add(pessoaResult);
				return listPessoa;
			
			default:
				return findAll();
		}
	}
	
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}
	
	public Pessoa findById(Integer id) {
		return pessoaRepository.findById(id);
	}
	
	public List<Pessoa> findByName(String nameToFind) {
		return pessoaRepository.findByName(nameToFind);
	}
	
	public List<Pessoa> findByGender(String genderToFind) {
		return pessoaRepository.findByGender(genderToFind);
	}

	private String getModeSearch(String filterQuery) {
		if (filterQuery != null) {
			return filterQuery.substring(0, filterQuery.indexOf("="));	
		}
		return "default";
	}
	
	private String getParamSearch(String filterQuery) {
		if (filterQuery != null) {
			return filterQuery.substring(filterQuery.indexOf("=") + 1, filterQuery.length());	
		}
		return "";
	}
}
