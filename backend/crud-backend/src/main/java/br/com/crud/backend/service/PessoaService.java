package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.PessoaRepository;

@Service // Expoe a classe como um servi�o do spring
@Transactional // Marca a classe com gerenciamento de transa��es (SPRING)
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

	public boolean save(Pessoa pessoa) {

		if (pessoa.getDocumentos().size() > 0) {
			List<Documento> documentoList = pessoa.getDocumentos();
			for (int i = 0; i < documentoList.size(); i++) {
				String documentoToFind = documentoList.get(i).getValorDocumento();
				if (documentoService.findByValue(documentoToFind) == null) {
					Pessoa pessoaDocumento = new Pessoa();
					pessoa.setId(0);
					documentoList.get(i).setPessoa(pessoaDocumento);
					documentoService.save(documentoList.get(i));
				}
			}
		}

		return pessoaRepository.save(pessoa);
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
