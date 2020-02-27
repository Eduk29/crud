package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.model.TipoDocumento;
import br.com.crud.backend.repository.DocumentoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class DocumentoService {

	// Attributes
	@Autowired
	private DocumentoRepository documentoRepository;
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	@Autowired
	private PessoaService pessoaService;

	// Methods
	public List<Documento> find(String filter) throws TipoDocumentoInvalidoException {
		List<Documento> listDocumento = new ArrayList<Documento>();
		String param = "";

		if (filter != null) {
			filter = ServiceUtils.removeDoubleQuotes(filter);
			param = ServiceUtils.getParamSearch(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "type":
			listDocumento = this.findByType(param);
			break;

		case "value":
			Documento documentoValueResult = this.findByValue(param);
			listDocumento.add(documentoValueResult);
			break;

		case "id":
			Documento documentoIdResult = this.findById(Integer.parseInt(param));
			listDocumento.add(documentoIdResult);
			break;

		default:
			listDocumento = this.findAll();
			break;
		}

		return listDocumento;
	}

	public List<Documento> findAll() {
		return this.documentoRepository.findAll();
	}

	public Documento findById(Integer id) {
		return this.documentoRepository.findById(id);
	}

	public List<Documento> findByType(String filterQuery) throws TipoDocumentoInvalidoException {
		String typeToFind = ServiceUtils.getParamSearch(filterQuery);
		return this.tipoDocumentoService.findTipoDocumentoByType(typeToFind).getDocumentos();
	}

	public Documento findByValue(String valueToFind) {
		try {
			return this.documentoRepository.findByValue(valueToFind);
		} catch (NoResultException e) {
			return null;
		}
	}

	public Documento save(Documento documento) throws DocumentoInvalidoException, TipoDocumentoInvalidoException {
		String typeToFind = documento.getTipoDocumento().getChave();
		TipoDocumento tipoDocumento = this.tipoDocumentoService.findTipoDocumentoByType(typeToFind);
		documento.setTipoDocumento(tipoDocumento);

		this.validateDocumento(documento);

		Integer idDocumentoOwner = documento.getPessoa().getId();
		Pessoa ownerDocumento = this.pessoaService.findById(idDocumentoOwner);
		documento.setPessoa(ownerDocumento);

		return this.documentoRepository.save(documento);
	}

	public Documento removeById(Integer id) {
		Documento documentoToRemove = findById(id);
		return this.documentoRepository.remove(documentoToRemove);
	}

	public Documento updateById(Integer id, Documento documento)
			throws TipoDocumentoInvalidoException, DocumentoInvalidoException {
		documento.setId(id);

		String typeToFind = documento.getTipoDocumento().getChave();
		TipoDocumento tipoDocumento = this.tipoDocumentoService.findTipoDocumentoByType(typeToFind);
		documento.setTipoDocumento(tipoDocumento);

		this.validateDocumento(documento);

		Integer idDocumentoOwner = documento.getPessoa().getId();
		Pessoa ownerDocumento = this.pessoaService.findById(idDocumentoOwner);
		documento.setPessoa(ownerDocumento);

		return this.documentoRepository.update(documento);
	}

	public void validateDocumentos(List<Documento> documentoList) throws DocumentoInvalidoException {
		for (int i = 0; i < documentoList.size(); i++) {
			this.validateDocumento(documentoList.get(i));
		}
	}

	private void validateDocumento(Documento documentoToValidate) throws DocumentoInvalidoException {
		if (documentoToValidate.getTipoDocumento().getChave().equals("CPF")
				&& documentoToValidate.getValorDocumento().length() != 11) {
			throw new DocumentoInvalidoException();
		}

		if (documentoToValidate.getTipoDocumento().getChave().equals("RG")
				&& documentoToValidate.getValorDocumento().length() != 9) {
			throw new DocumentoInvalidoException();
		}
	}
}
