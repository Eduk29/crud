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
import br.com.crud.backend.model.TipoDocumento;
import br.com.crud.backend.repository.DocumentoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private PessoaService pessoaService;

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

	public Documento save(Documento documento) throws DocumentoInvalidoException {
		documento.setTipoDocumento(this.tipoDocumentoService.findTipoDocumentoById(documento.getTipoDocumento().getId()));
		documento.setPessoa(this.pessoaService.findById(documento.getPessoa().getId()));
		this.validateDocumento(documento.getTipoDocumento(), documento.getValorDocumento());
		return this.documentoRepository.save(documento);
	}

	public Documento removeById(Integer id) {
		Documento documentoToRemove = findById(id);
		return this.documentoRepository.remove(documentoToRemove);
	}

	public Documento updateById(Integer id, Documento documento) {
		if (documento.getPessoa() == null) {
			Documento documentoToUpdate = this.findById(id);
			documento.setPessoa(documentoToUpdate.getPessoa());
		}

		documento.setId(id);
		return this.documentoRepository.update(documento);
	}

	public void validateDocumentos(List<Documento> documentoList) throws DocumentoInvalidoException {
		for (int i = 0; i < documentoList.size(); i++) {
			this.validateDocumento(documentoList.get(i).getTipoDocumento(), documentoList.get(i).getValorDocumento());
		}
	}

	private void validateDocumento(TipoDocumento type, String value) throws DocumentoInvalidoException {
		if (type.getChave().equals("RG") && value.length() != 9) {
			throw new DocumentoInvalidoException();
		}
		if (type.getChave().equals("CPF") && value.length() != 11) {
			throw new DocumentoInvalidoException();
		}
		if (!type.getChave().equals("RG") && !type.getChave().equals("CPF")) {
			throw new DocumentoInvalidoException();
		}
	}
}
