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
import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
import br.com.crud.backend.model.Contato;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Endereco;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.model.TipoContato;
import br.com.crud.backend.repository.PessoaRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service // Expoe a classe como um servi�o do spring
@Transactional // Marca a classe com gerenciamento de transa��es (SPRING)
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
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	@Autowired
	private TipoContatoService tipoContatoService;

	public List<Pessoa> find(String filter)
			throws GeneroInvalidoException, TipoDocumentoInvalidoException, TipoContatoInvalidoException,
			CepInvalidoException {
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
			
			if (pessoaResult != null) {
				pessoaList.add(pessoaResult);	
			}

			break;

		case "documentoValue":
			Documento documento = this.documentoService.findByValue(param);

			if (documento != null) {
				Integer idToFind = documento.getPessoa().getId();
				Pessoa pessoa = this.findById(idToFind);
				pessoaList.add(pessoa);
			}
			break;

		case "tipoDocumento":
			List<Documento> documentoList = this.tipoDocumentoService.findTipoDocumentoByType(param).getDocumentos();

			for (int i = 0; i < documentoList.size(); i++) {
				Integer idToFind = documentoList.get(i).getPessoa().getId();
				Pessoa pessoa = this.findById(idToFind);
				pessoaList.add(pessoa);
			}
			break;

		case "documentoId":
			Documento documentoToFindById = this.documentoService.findById(Integer.parseInt(param));

			if (documentoToFindById != null) {
				Integer idToFind = documentoToFindById.getPessoa().getId();
				Pessoa pessoa = this.findById(idToFind);
				pessoaList.add(pessoa);
			}
			break;

		case "contact":
			List<Contato> contatoList = this.contatoService.findByValue(param);

			for (int i = 0; i < contatoList.size(); i++) {
				Integer idToFind = contatoList.get(i).getPessoa().getId();
				Pessoa pessoa = this.findById(idToFind);
				pessoaList.add(pessoa);
			}
			break;

		case "tipoContato":
			List<Contato> contatoListToFind = this.tipoContatoService.findTipoDocumentoByType(param).getContatos();

			for (int i = 0; i < contatoListToFind.size(); i++) {
				Integer idToFind = contatoListToFind.get(i).getPessoa().getId();
				Pessoa pessoa = this.findById(idToFind);
				pessoaList.add(pessoa);
			}
			break;
			
		case "idContato": 
			Contato contato = this.contatoService.findById(Integer.parseInt(param));
			
			if (contato != null) {
				Integer idToFind = contato.getPessoa().getId();
				Pessoa pessoa = this.findById(idToFind);
				pessoaList.add(pessoa);
			}
			break;
			
		case "CEP":
			List<Endereco> enderecoList = this.enderecoService.findByCep(param);
			
			for (int i = 0; i < enderecoList.size(); i++) {
				List<Pessoa> pessoaListWithSameCEP = enderecoList.get(i).getPessoa();
				for (int j = 0; j < pessoaListWithSameCEP.size(); j++) {
					Integer idToFind = pessoaListWithSameCEP.get(j).getId();
					Pessoa pessoa = this.findById(idToFind);
					pessoaList.add(pessoa);
				}
			}
			break;
			
		case "Cidade":
			List<Endereco> enderecoListWithSameCity = this.enderecoService.findByCidade(param);
			
			for (int i = 0; i < enderecoListWithSameCity.size(); i++) {
				List<Pessoa> pessoaListWithSameCity = enderecoListWithSameCity.get(i).getPessoa();
				for (int j = 0; j < pessoaListWithSameCity.size(); j++) {
					Integer idToFind = pessoaListWithSameCity.get(j).getId();
					Pessoa pessoa = this.findById(idToFind);
					pessoaList.add(pessoa);
				}
			}
			break;
			
		case "Estado":
			List<Endereco> enderecoListWithSameState = this.enderecoService.findByEstado(param);
			
			for (int i = 0; i < enderecoListWithSameState.size(); i++) {
				List<Pessoa> pessoaListWithSameState = enderecoListWithSameState.get(i).getPessoa();
				for (int j = 0; j < pessoaListWithSameState.size(); j++) {
					Integer idToFind = pessoaListWithSameState.get(j).getId();
					Pessoa pessoa = this.findById(idToFind);
					pessoaList.add(pessoa);
				}
			}
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
			TipoContatoInvalidoException, ContatoInvalidoException, TipoDocumentoInvalidoException {

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

	public Pessoa updateById(Integer id, Pessoa pessoa) throws TipoDocumentoInvalidoException,
			DocumentoInvalidoException, TipoContatoInvalidoException, ContatoInvalidoException {
		Pessoa pessoaToUpdate = pessoa;
		pessoaToUpdate.setId(id);

		List<Documento> documentoList = pessoa.getDocumentos();
		List<Contato> contatoList = pessoa.getContatos();
		List<Endereco> enderecoList = pessoa.getEnderecos();

		this.updateDocumentosPessoa(documentoList, id);
		this.updateContatosPessoa(contatoList, id);
		this.updateEnderecosPessoa(enderecoList, id);

		return this.pessoaRepository.update(pessoa);
	}

	private void saveDocumentosPessoa(List<Documento> documentosToSave, Integer idOwner)
			throws DocumentoInvalidoException, TipoDocumentoInvalidoException {
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

	private void updateDocumentosPessoa(List<Documento> documentosToUpdateList, Integer idOwner)
			throws TipoDocumentoInvalidoException, DocumentoInvalidoException {
		for (int i = 0; i < documentosToUpdateList.size(); i++) {
			Pessoa documentoOwner = new Pessoa();
			documentoOwner.setId(idOwner);
			documentosToUpdateList.get(i).setPessoa(documentoOwner);
			this.documentoService.updateById(documentosToUpdateList.get(i).getId(), documentosToUpdateList.get(i));
		}
	}

	private void updateContatosPessoa(List<Contato> contatosToUpdateList, Integer idOwner)
			throws TipoContatoInvalidoException, ContatoInvalidoException {
		for (int i = 0; i < contatosToUpdateList.size(); i++) {
			Pessoa contatoOwner = new Pessoa();
			contatoOwner.setId(idOwner);
			contatosToUpdateList.get(i).setPessoa(contatoOwner);
			this.contatoService.updateById(contatosToUpdateList.get(i).getId(), contatosToUpdateList.get(i));
		}
	}

	private void updateEnderecosPessoa(List<Endereco> enderecosToUpdateList, Integer idOwner) {
		for (int i = 0; i < enderecosToUpdateList.size(); i++) {
			this.enderecoService.updateById(enderecosToUpdateList.get(i).getId(), enderecosToUpdateList.get(i));
		}
	}

	private void validateGenero(String generoToValidate) throws GeneroInvalidoException {
		if ((!generoToValidate.equals("Masculino") && !generoToValidate.equals("Feminino"))
				|| generoToValidate.isEmpty()) {
			throw new GeneroInvalidoException();
		}
	}
}
