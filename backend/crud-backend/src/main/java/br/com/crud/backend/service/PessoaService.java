package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.CepInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.GeneroInvalidoException;
import br.com.crud.backend.interfaces.ServiceUtilsInterface;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Endereco;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.PessoaRepository;

@Service // Expoe a classe como um serviço do spring
@Transactional // Marca a classe com gerenciamento de transações (SPRING)
public class PessoaService implements ServiceUtilsInterface {

	// Attributes
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private DocumentoService documentoService;
	@Autowired
	private EnderecoService enderecoService;

	public List<Pessoa> find(String filter) {
		if (filter != null) {
			removeDoubleQuotes(filter);
		}
		
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

	public Pessoa save(Pessoa pessoa) throws DocumentoInvalidoException, GeneroInvalidoException, CepInvalidoException {
		List<Documento> documentoList = new ArrayList<Documento>();
		List<Endereco> enderecoList = new ArrayList<Endereco>();
		
		documentoList = pessoa.getDocumentos();
		enderecoList = pessoa.getEnderecos();
		
		try {
			validateGenero(pessoa.getGenero());
			
			pessoa.setEnderecos(new ArrayList<Endereco>());
			pessoa.setDocumentos(new ArrayList<Documento>());
			Integer idPessoa = pessoaRepository.save(pessoa).getId();
			
			saveDocumentosPessoa(documentoList, idPessoa);
			saveEnderecosPessoa(enderecoList, idPessoa);
			

			pessoa.setDocumentos(documentoList);
			pessoa.setEnderecos(enderecoList);
			return pessoa;

		} catch (DocumentoInvalidoException e) {
			throw new DocumentoInvalidoException();
		} catch (GeneroInvalidoException e) {
			throw new GeneroInvalidoException();
		} catch (CepInvalidoException e) {
			throw new CepInvalidoException();
		}
	}
	
	public Pessoa removeById(Integer id) {
		Pessoa pessoaToRemove = findById(id);
		return pessoaRepository.remove(pessoaToRemove);
	}
	
	public Pessoa updateById (Integer id, Pessoa pessoa) {
		pessoa.setId(id);
		for (int i = 0; i < pessoa.getDocumentos().size(); i++) {
			documentoService.updateById(pessoa.getDocumentos().get(i).getId(), pessoa.getDocumentos().get(i));
		}
		
		return pessoaRepository.update(pessoa);
	}
	
	private void saveDocumentosPessoa (List<Documento> documentosToSave, Integer idOwner) throws DocumentoInvalidoException {
		documentoService.validateDocumentos(documentosToSave);
		for (int i = 0; i < documentosToSave.size(); i++) {
			Pessoa documentoOwner = new Pessoa();
			documentoOwner.setId(idOwner);
			documentosToSave.get(i).setPessoa(documentoOwner);
			documentosToSave.set(i, documentoService.save(documentosToSave.get(i)));
		}
	}
	
	private void saveEnderecosPessoa (List<Endereco> enderecosToSave, Integer idOwner) throws CepInvalidoException {
		for (int i = 0; i < enderecosToSave.size(); i++) {
			Pessoa enderecoOwner = new Pessoa();
			enderecoOwner.setId(idOwner);
			List<Pessoa> enderecoOwnerList = new ArrayList<Pessoa>();
			enderecoOwnerList.add(enderecoOwner);
			enderecosToSave.get(i).setPessoas(enderecoOwnerList);
			enderecosToSave.set(i, enderecoService.save(enderecosToSave.get(i)));
		}
	}

	private void validateGenero(String generoToValidate) throws GeneroInvalidoException {
		if ((!generoToValidate.equals("Masculino") && !generoToValidate.equals("Feminino"))
				|| generoToValidate.isEmpty()) {
			throw new GeneroInvalidoException();
		}
	}

	public String getModeSearch(String filterQuery) {
		if (filterQuery != null) {
			return filterQuery.substring(0, filterQuery.indexOf("="));
		}
		return "default";
	}

	public String getParamSearch(String filterQuery) {
		if (filterQuery != null) {
			return filterQuery.substring(filterQuery.indexOf("=") + 1, filterQuery.length());
		}
		return "";
	}
	
	public void removeDoubleQuotes(String stringWithDoubleQuotes) {
		stringWithDoubleQuotes = stringWithDoubleQuotes.replace("\"", "");
	}
}
