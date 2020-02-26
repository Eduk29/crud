package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
import br.com.crud.backend.model.TipoDocumento;
import br.com.crud.backend.repository.TipoDocumentoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class TipoDocumentoService {

	// Attribute
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	public List<TipoDocumento> find(String filter) throws TipoDocumentoInvalidoException {
		if (filter != null) {
			filter = ServiceUtils.removeDoubleQuotes(filter);
		}

		switch (ServiceUtils.getModeSearch(filter)) {
		case "type":

			TipoDocumento tipoDocumento = findTipoDocumentoByType(ServiceUtils.getParamSearch(filter));
			List<TipoDocumento> tipoDocumentoFoundList = new ArrayList<TipoDocumento>();
			tipoDocumentoFoundList.add(tipoDocumento);
			return tipoDocumentoFoundList;

		default:
			return this.findAll();
		}
	}

	public List<TipoDocumento> findAll() {
		List<TipoDocumento> tiposDocumento = new ArrayList<TipoDocumento>();
		tiposDocumento = this.tipoDocumentoRepository.findAll();

//		for (int i = 0; i < tiposDocumento.size(); i++) {
//			tiposDocumento.get(i).setDocumentos(null);
//		}

		return tiposDocumento;
	}

	public TipoDocumento findTipoDocumentoByType(String filter) throws TipoDocumentoInvalidoException {
		validateTipoDocumento(filter);
		return tipoDocumentoRepository.findyByType(filter);
	}

	public TipoDocumento findTipoDocumentoById(Integer id) {
		return tipoDocumentoRepository.findTipoDocumentoById(id);
	}

	private void validateTipoDocumento(String type) throws TipoDocumentoInvalidoException {
		List<TipoDocumento> tipoDocumentoList = findAll();
		boolean listHasType = false;
		
		for (int i = 0; i < tipoDocumentoList.size(); i++) {
			if (tipoDocumentoList.get(i).getChave().equals(type)) {
				listHasType = true;
				break;
			}
		}
		
		if (!listHasType) {
			throw new TipoDocumentoInvalidoException();
		}
	}
}
