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
	
	public List<Endereco> find(String filter) {
		if (filter != null) {
			filter = filter.replace("\"", "");	
		}
		
		switch(getModeSearch(filter)) {
			case "CEP":
				return findByCep(getParamSearch(filter));
				
			case "Estado":
				return findByEstado(getParamSearch(filter));
				
			case "Cidade":
				return findByCidade(getParamSearch(filter));
				
			case "Id":
				Endereco endereco = findById(Integer.parseInt(getParamSearch(filter)));
				List<Endereco> enderecoList = new ArrayList<Endereco>();
				enderecoList.add(endereco);				
				
				return enderecoList;
				
			default:
				return findAll();
		}
	}

	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco findById (Integer id) {
		return enderecoRepository.findById(id);
	}
	
	public List<Endereco> findByCep(String cepToFind) {
		return enderecoRepository.findByCEP(cepToFind);
	}
	
	public List<Endereco> findByEstado(String estadoToFind){
		return enderecoRepository.findByEstado(estadoToFind);
	}
	
	public List<Endereco> findByCidade(String cidadeToFind){
		return enderecoRepository.findByCidade(cidadeToFind);
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
