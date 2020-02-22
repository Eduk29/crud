package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
import br.com.crud.backend.model.TipoDocumento;
import br.com.crud.backend.repository.TipoDocumentoRepository;
import br.com.crud.backend.util.ServiceUtils;

@Service
@Transactional
public class TipoDocumentoService {

	// Attribute
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	public List<TipoDocumento> find(String filter) throws TipoDocumentoInvalidoException {
		if (filter != null) {
			ServiceUtils.removeDoubleQuotes(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "type":

			TipoDocumento tipoDocumento = this.findTipoDocumentoByType(ServiceUtils.getParamSearch(filter));

			if (tipoDocumento != null) {
				List<TipoDocumento> listaTipos = new ArrayList<TipoDocumento>();
				listaTipos.add(tipoDocumento);
				return listaTipos;
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
		validateTipoDocumento(filter);
		return tipoDocumentoRepository.findyByType(filter);
	}

	public TipoDocumento findTipoDocumentoById(Integer id) {
		return tipoDocumentoRepository.findTipoDocumentoById(id);
	}

	private void validateTipoDocumento(String type) throws TipoDocumentoInvalidoException {
		if (!type.equals("RG") && !type.equals("CPF")) {
			throw new TipoDocumentoInvalidoException();
		}
	}

}
