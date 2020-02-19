package br.com.crud.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
import br.com.crud.backend.model.TipoDocumento;
import br.com.crud.backend.repository.TipoDocumentoRepository;

@Service
@Transactional
public class TipoDocumentoService {

	// Attribute
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	public List<TipoDocumento> find(String filter) throws TipoDocumentoInvalidoException {
		switch(getModeSearch(filter)) {
			case "type":
			try {
				return (List<TipoDocumento>)findTipoDocumentoByType(getParamSearch(filter));
			} catch (TipoDocumentoInvalidoException e) {
				throw new TipoDocumentoInvalidoException();
			}
				
			default:
				return findAll();
		}
	}

	public List<TipoDocumento> findAll() {
		List<TipoDocumento> tiposDocumento = tipoDocumentoRepository.findAll();

		for (int i = 0; i < tiposDocumento.size(); i++) {
			tiposDocumento.get(i).setDocumentos(null);
		}

		return tipoDocumentoRepository.findAll();
	}

	public TipoDocumento findTipoDocumentoByType(String filter) throws TipoDocumentoInvalidoException {
		try {
			validateTipoDocumento(filter);
			if (tipoDocumentoRepository.findyByType(filter).size() > 0) {
				return tipoDocumentoRepository.findyByType(filter).get(0);
			}	
		} catch (TipoDocumentoInvalidoException e) {
			throw new TipoDocumentoInvalidoException(); 
		}
		return null;
	}
	
	public TipoDocumento findTipoDocumentoById (Integer id) {
		return tipoDocumentoRepository.findTipoDocumentoById(id);
	}
	
	private void validateTipoDocumento(String type) throws TipoDocumentoInvalidoException {
		if (!type.equals("RG") && !type.equals("CPF")) {
			throw new TipoDocumentoInvalidoException();
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
