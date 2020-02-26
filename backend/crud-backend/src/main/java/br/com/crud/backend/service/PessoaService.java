package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.CepInvalidoException;
import br.com.crud.backend.exception.ContatoInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.GeneroInvalidoException;
import br.com.crud.backend.exception.TipoContatoInvalidoException;
import br.com.crud.backend.model.Contato;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Endereco;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.PessoaRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service // Expoe a classe como um serviço do spring
@Transactional // Marca a classe com gerenciamento de transações (SPRING)
public class PessoaService {

	// Attributes
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private DocumentoService documentoService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private ContatoService contatoService;

	public List<Pessoa> find(String filter) throws GeneroInvalidoException {
		List<Pessoa> pessoaList = new ArrayList<Pessoa>();
		String param = null;
		
		if (filter != null) {
			filter = ServiceUtils.removeDoubleQuotes(filter);
			param = ServiceUtils.getParamSearch(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "name":
			pessoaList = this.findByName(param);
			break;

		case "gender":
			pessoaList = this.findByGender(param);
			break;

		case "id":
			Pessoa pessoaResult = this.findById(Integer.parseInt(param));
			pessoaList.add(pessoaResult);
			break;

		default:
			pessoaList = this.findAll();
			break;
		}
		
		return pessoaList;
	}

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	public Pessoa findById(Integer id) {
		return this.pessoaRepository.findById(id);
	}

	public List<Pessoa> findByName(String nameToFind) {
		return this.pessoaRepository.findByName(nameToFind);
	}

	public List<Pessoa> findByGender(String genderToFind) throws GeneroInvalidoException {
		this.validateGenero(genderToFind);
		return this.pessoaRepository.findByGender(genderToFind);
	}

	public Pessoa save(Pessoa pessoa) throws DocumentoInvalidoException, GeneroInvalidoException, CepInvalidoException,
			TipoContatoInvalidoException, ContatoInvalidoException {

		this.validateGenero(pessoa.getGenero());

		List<Documento> documentoList = new ArrayList<Documento>();
		List<Endereco> enderecoList = new ArrayList<Endereco>();
		List<Contato> contatoList = new ArrayList<Contato>();

		documentoList = pessoa.getDocumentos();
		enderecoList = pessoa.getEnderecos();
		contatoList = pessoa.getContatos();

		pessoa.setEnderecos(new ArrayList<Endereco>());
		pessoa.setDocumentos(new ArrayList<Documento>());
		pessoa.setContatos(new ArrayList<Contato>());

		Integer idPessoa = this.pessoaRepository.save(pessoa).getId();

		this.saveDocumentosPessoa(documentoList, idPessoa);
		this.saveEnderecosPessoa(enderecoList, idPessoa);
		this.saveContatosPessoa(contatoList, idPessoa);

		pessoa.setDocumentos(documentoList);
		pessoa.setEnderecos(enderecoList);
		pessoa.setContatos(contatoList);
		return pessoa;
	}

	public Pessoa removeById(Integer id) {
		Pessoa pessoaToRemove = findById(id);
		return this.pessoaRepository.remove(pessoaToRemove);
	}

	public Pessoa updateById(Integer id, Pessoa pessoa) {
		pessoa.setId(id);
		for (int i = 0; i < pessoa.getDocumentos().size(); i++) {
			this.documentoService.updateById(pessoa.getDocumentos().get(i).getId(), pessoa.getDocumentos().get(i));
		}

		return this.pessoaRepository.update(pessoa);
	}

	private void saveDocumentosPessoa(List<Documento> documentosToSave, Integer idOwner)
			throws DocumentoInvalidoException {
		for (int i = 0; i < documentosToSave.size(); i++) {
			Pessoa documentoOwner = new Pessoa();
			documentoOwner.setId(idOwner);
			documentosToSave.get(i).setPessoa(documentoOwner);
			documentosToSave.set(i, this.documentoService.save(documentosToSave.get(i)));
		}
	}

	private void saveEnderecosPessoa(List<Endereco> enderecosToSave, Integer idOwner) throws CepInvalidoException {
		for (int i = 0; i < enderecosToSave.size(); i++) {
			Pessoa enderecoOwner = new Pessoa();
			enderecoOwner.setId(idOwner);
			List<Pessoa> enderecoOwnerList = new ArrayList<Pessoa>();
			enderecoOwnerList.add(enderecoOwner);
			enderecosToSave.get(i).setPessoas(enderecoOwnerList);
			enderecosToSave.set(i, this.enderecoService.save(enderecosToSave.get(i)));
		}
	}

	private void saveContatosPessoa(List<Contato> contatosToSave, Integer idOwner)
			throws TipoContatoInvalidoException, ContatoInvalidoException {
		for (int i = 0; i < contatosToSave.size(); i++) {
			Pessoa contatoOwner = new Pessoa();
			contatoOwner.setId(idOwner);
			contatosToSave.get(i).setPessoa(contatoOwner);
			contatosToSave.set(i, this.contatoService.save(contatosToSave.get(i)));
		}
	}

	private void validateGenero(String generoToValidate) throws GeneroInvalidoException {
		if ((!generoToValidate.equals("Masculino") && !generoToValidate.equals("Feminino"))
				|| generoToValidate.isEmpty()) {
			throw new GeneroInvalidoException();
		}
	}
}
