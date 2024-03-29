package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.ContatoInvalidoException;
import br.com.crud.backend.exception.TipoContatoInvalidoException;
import br.com.crud.backend.model.Contato;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.model.TipoContato;
import br.com.crud.backend.repository.ContatoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class ContatoService {

	// Attributes
	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private TipoContatoService tipoContatoService;

	@Autowired
	private PessoaService pessoaService;

	public List<Contato> find(String filter) {
		List<Contato> contatoList = new ArrayList<Contato>();

		if (filter != null) {
			filter = ServiceUtils.removeDoubleQuotes(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "Id":
			Contato contato = findById(Integer.parseInt(ServiceUtils.getParamSearch(filter)));
			contatoList.add(contato);
			break;

		case "contact":
			contatoList = findByValue(ServiceUtils.getParamSearch(filter));
			break;

		default:
			contatoList = findAll();
		}

		return contatoList;
	}

	public List<Contato> findAll() {
		return contatoRepository.findAll();
	}

	public Contato findById(Integer id) {
		return contatoRepository.findById(id);
	}

	public List<Contato> findByValue(String valueToFind) {
		return contatoRepository.findByValue(valueToFind);
	}

	public Contato save(Contato contato) throws TipoContatoInvalidoException, ContatoInvalidoException {
		String typeToFind = contato.getTipoContato().getChave();
		TipoContato tipoContato = tipoContatoService.findTipoDocumentoByType(typeToFind);
		contato.setTipoContato(tipoContato);
		
		validateContato(contato);

		Integer idContatoOwner = contato.getPessoa().getId();
		Pessoa ownerContato = pessoaService.findById(idContatoOwner);
		contato.setPessoa(ownerContato);

		return this.contatoRepository.save(contato);
	}

	public Contato removeById(Integer id) {
		Contato contatoToRemove = this.findById(id);
		return this.contatoRepository.remove(contatoToRemove);
	}

	public Contato updateById(Integer id, Contato contatoUpdate) throws TipoContatoInvalidoException, ContatoInvalidoException {
		if (contatoUpdate.getPessoa() == null) {
			Pessoa contatoOwner = this.contatoRepository.findById(id).getPessoa();
			contatoUpdate.setPessoa(contatoOwner);
		}
		
		if (contatoUpdate.getTipoContato().getId() == null) {
			String typeToFind = contatoUpdate.getTipoContato().getChave();
			TipoContato tipoContato = tipoContatoService.findTipoDocumentoByType(typeToFind);
			contatoUpdate.setTipoContato(tipoContato);
		}
		
		contatoUpdate.setId(id);
		return this.contatoRepository.update(contatoUpdate);
	}

	private void validateContato(Contato contatoToValidate) throws ContatoInvalidoException {
		if (contatoToValidate.getTipoContato().getChave().equals("CELULAR")
				&& contatoToValidate.getValor().length() != 11) {
			throw new ContatoInvalidoException();
		}
		
		if (contatoToValidate.getTipoContato().getChave().equals("TELEFONE") && contatoToValidate.getValor().length() != 10) {
			throw new ContatoInvalidoException();
		}
	}
}
