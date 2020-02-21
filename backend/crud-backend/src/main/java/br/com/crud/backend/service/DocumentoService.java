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
import br.com.crud.backend.util.ServiceUtils;

@Service
@Transactional
public class DocumentoService  {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@Autowired
	private PessoaService pessoaService;

	public List<Documento> find(String filter) throws TipoDocumentoInvalidoException {
		Documento documentoResult;
		List<Documento> listDocumento;
		
		if (filter != null) {
			ServiceUtils.removeDoubleQuotes(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "type":
			try {
				return findByType(ServiceUtils.getParamSearch(filter));
			} catch (TipoDocumentoInvalidoException e) {
				// TODO Auto-generated catch block
				throw new TipoDocumentoInvalidoException();
			}

		case "value":
			documentoResult = findByValue(ServiceUtils.getParamSearch(filter));
			if (documentoResult == null) {
				return new ArrayList<Documento>();
			}
			listDocumento = new ArrayList<Documento>();
			listDocumento.add(documentoResult);
			return listDocumento;

		case "id":
			documentoResult = findById(Integer.parseInt(ServiceUtils.getParamSearch(filter)));
			listDocumento = new ArrayList<>();
			listDocumento.add(documentoResult);
			return listDocumento;

		default:
			return findAll();
		}
	}

	public List<Documento> findAll() {
		return documentoRepository.findAll();
	}

	public Documento findById(Integer id) {
		return documentoRepository.findById(id);
	}

	public List<Documento> findByType(String filterQuery) throws TipoDocumentoInvalidoException {
		try {
			String typeToFind = ServiceUtils.getParamSearch(filterQuery);
			return tipoDocumentoService.findTipoDocumentoByType(typeToFind).getDocumentos();
		} catch (TipoDocumentoInvalidoException e) {
			throw new TipoDocumentoInvalidoException();
		}
	}

	public Documento findByValue(String valueToFind) {
		try {
			return documentoRepository.findByValue(valueToFind);
		} catch (NoResultException e) {
			return null;
		}
	}

	public Documento save(Documento documento) throws DocumentoInvalidoException {
		documento.setTipoDocumento(tipoDocumentoService.findTipoDocumentoById(documento.getTipoDocumento().getId()));
		documento.setPessoa(pessoaService.findById(documento.getPessoa().getId()));
		validateDocumento(documento.getTipoDocumento(), documento.getValorDocumento());
		return documentoRepository.save(documento);
	}

	public Documento removeById(Integer id) {
		Documento documentoToRemove = findById(id);
		return documentoRepository.remove(documentoToRemove);
	}

	public Documento updateById(Integer id, Documento documento) {
		if (documento.getPessoa() == null) {
			Documento documentoToUpdate = findById(id);
			documento.setPessoa(documentoToUpdate.getPessoa());
		}

		documento.setId(id);
		return documentoRepository.update(documento);
	}

	public void validateDocumentos(List<Documento> documentoList) throws DocumentoInvalidoException {
		for (int i = 0; i < documentoList.size(); i++) {
			validateDocumento(documentoList.get(i).getTipoDocumento(), documentoList.get(i).getValorDocumento());
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
