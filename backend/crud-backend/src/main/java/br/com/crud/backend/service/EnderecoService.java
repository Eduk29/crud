package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.model.Endereco;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.EnderecoRepository;

@Service
@Transactional
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaService pessoaService;

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	public Endereco save(Endereco endereco) {
		endereco.setPessoas(findPessoa(endereco.getPessoa()));
		return enderecoRepository.save(endereco);
	}

	private List<Pessoa> findPessoa(List<Pessoa> pessoas) {
		List<Pessoa> pessoaEnderecoList = new ArrayList<Pessoa>();
		
		for (int i = 0; i < pessoas.size(); i++) {
			Pessoa pessoaToAdd = pessoaService.findById(pessoas.get(i).getId());
			pessoaEnderecoList.add(pessoaToAdd);
		}
		
		return pessoaEnderecoList;
	}
}
