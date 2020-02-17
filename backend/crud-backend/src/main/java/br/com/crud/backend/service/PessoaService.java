package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.GeneroInvalidoException;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.PessoaRepository;

@Service // Expoe a classe como um serviço do spring
@Transactional // Marca a classe com gerenciamento de transações (SPRING)
public class PessoaService {

	// Attributes
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private DocumentoService documentoService;

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

	public Pessoa save(Pessoa pessoa) throws DocumentoInvalidoException, GeneroInvalidoException {
		List<Documento> documentoList = new ArrayList<Documento>();
		documentoList = pessoa.getDocumentos();
		
		try {
			validateGenero(pessoa.getGenero());
			documentoService.validateDocumentos(documentoList);
			
			pessoa.setDocumentos(new ArrayList<Documento>());
			Integer idPessoa = pessoaRepository.save(pessoa).getId();

			for (int i = 0; i < documentoList.size(); i++) {
				Pessoa documentoOwner = new Pessoa();
				documentoOwner.setId(idPessoa);
				documentoList.get(i).setPessoa(documentoOwner);
				documentoList.set(i, documentoService.save(documentoList.get(i)));
			}

			pessoa.setDocumentos(documentoList);
			return pessoa;

		} catch (DocumentoInvalidoException e) {
			throw new DocumentoInvalidoException();
		} catch (GeneroInvalidoException e ) {
			throw new GeneroInvalidoException();
		}
	}

	private void validateGenero(String generoToValidate) throws GeneroInvalidoException {
		if ((!generoToValidate.equals("Masculino") && !generoToValidate.equals("Feminino"))
				|| generoToValidate.isEmpty()) {
			throw new GeneroInvalidoException();
		}
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
