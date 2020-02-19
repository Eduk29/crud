package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.repository.DocumentoRepository;

@Service
@Transactional
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	public List<Documento> find(String filter) {
		Documento documentoResult;
		List<Documento> listDocumento;

		switch (getModeSearch(filter)) {
		case "type":
			return findByType(getParamSearch(filter));

		case "value":
			documentoResult = findByValue(getParamSearch(filter));
			if (documentoResult == null) {
				return new ArrayList<Documento>();
			}
			listDocumento = new ArrayList<Documento>();
			listDocumento.add(documentoResult);
			return listDocumento;

		case "id":
			documentoResult = findById(Integer.parseInt(getParamSearch(filter)));
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

	public List<Documento> findByType(String typeToFind) {
		return documentoRepository.findByType(typeToFind);
	}

	public Documento findByValue(String valueToFind) {
		try {
			return documentoRepository.findByValue(valueToFind);
		} catch (NoResultException e) {
			return null;
		}
	}

	public Documento save(Documento documento) throws DocumentoInvalidoException {
		validateDocumento(documento.getTipoDocumento(), documento.getValorDocumento());
		return documentoRepository.save(documento);
	}
	
	public Documento removeById(Integer id) {
		Documento documentoToRemove = findById(id);
		return documentoRepository.remove(documentoToRemove);
	}
	
	public Documento updateById (Integer id, Documento documento) {
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

	private void validateDocumento(String type, String value) throws DocumentoInvalidoException {
		if (type.equals("RG") && value.length() != 9) {
			throw new DocumentoInvalidoException();
		}
		if (type.equals("CPF") && value.length() != 11) {
			throw new DocumentoInvalidoException();
		}
		if (!type.equals("RG") && !type.equals("CPF")) {
			throw new DocumentoInvalidoException();
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
