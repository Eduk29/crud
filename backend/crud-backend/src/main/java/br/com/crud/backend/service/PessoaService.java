package br.com.crud.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.PessoaRepository;

@Service // Expoe a classe como um serviço do spring
@Transactional // Marca a classe com gerenciamento de transações (SPRING)
public class PessoaService {

	// Attributes
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

}
