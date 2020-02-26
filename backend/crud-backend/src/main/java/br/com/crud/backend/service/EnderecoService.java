package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.CepInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Endereco;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.EnderecoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaService pessoaService;

	public List<Endereco> find(String filter) throws CepInvalidoException {
		if (filter != null) {
			filter = ServiceUtils.removeDoubleQuotes(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "CEP":
			return findByCep(ServiceUtils.getParamSearch(filter));

		case "Estado":
			return findByEstado(ServiceUtils.getParamSearch(filter));

		case "Cidade":
			return findByCidade(ServiceUtils.getParamSearch(filter));

		case "Id":
			Endereco endereco = findById(Integer.parseInt(ServiceUtils.getParamSearch(filter)));
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

	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id);
	}

	public List<Endereco> findByCep(String cepToFind) throws CepInvalidoException {
		validateCep(cepToFind);
		return enderecoRepository.findByCEP(cepToFind);
	}

	public List<Endereco> findByEstado(String estadoToFind) {
		return enderecoRepository.findByEstado(estadoToFind);
	}

	public List<Endereco> findByCidade(String cidadeToFind) {
		return enderecoRepository.findByCidade(cidadeToFind);
	}

	public Endereco save(Endereco endereco) throws CepInvalidoException {
		validateCep(endereco.getCep());
		endereco.setPessoas(findPessoa(endereco.getPessoa()));
		return enderecoRepository.save(endereco);
	}
	
	public Endereco removeById(Integer id) {
		Endereco enderecoToRemove = findById(id);
		return enderecoRepository.remove(enderecoToRemove);
	}
	
	public Endereco updateById(Integer id, Endereco endereco) {
		if (endereco.getPessoa() == null) {
			List<Pessoa> enderecoOwner = findById(id).getPessoa();
			endereco.setPessoas(enderecoOwner);
		}
		
		endereco.setId(id);
		return enderecoRepository.update(endereco);
	}

	private List<Pessoa> findPessoa(List<Pessoa> pessoas) {
		List<Pessoa> pessoaEnderecoList = new ArrayList<Pessoa>();

		for (int i = 0; i < pessoas.size(); i++) {
			Pessoa pessoaToAdd = pessoaService.findById(pessoas.get(i).getId());
			pessoaEnderecoList.add(pessoaToAdd);
		}

		return pessoaEnderecoList;
	}

	public void validateEnderecos(List<Endereco> enderecoList) throws CepInvalidoException {
		for (int i = 0; i < enderecoList.size(); i++) {
			validateCep(enderecoList.get(i).getCep());
		}
	}

	public void validateCep(String cep) throws CepInvalidoException {
		if (cep.length() != 8) {
			throw new CepInvalidoException();
		}
	}
}
