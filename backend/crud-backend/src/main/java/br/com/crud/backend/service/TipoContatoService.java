package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.exception.TipoContatoInvalidoException;
import br.com.crud.backend.model.TipoContato;
import br.com.crud.backend.repository.TipoContatoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class TipoContatoService {

	// Attributes
	@Autowired
	private TipoContatoRepository tipoContatoRepository;
	
	// Methods
	public List<TipoContato> find(String filter) throws TipoContatoInvalidoException {
		List<TipoContato> tipoContatoList = new ArrayList<TipoContato>();
		String param = "";
		
		if (filter != null) {
			filter = ServiceUtils.removeDoubleQuotes(filter);
			param = ServiceUtils.getParamSearch(filter);
		}
		
		switch (ServiceUtils.getModeSearch(filter)) {
			case "type":
				validateTipoContato(param);			
				tipoContatoList = findTipoDocumentoBy(param);
				break;
				
			case "id":
				TipoContato tipoContato = findById(Integer.parseInt(param));
				tipoContatoList.add(tipoContato);
				break;
				
			default:
				tipoContatoList = findAll();
				break;
		}
		
		return tipoContatoList;
		
	}
	
	public List<TipoContato> findAll() {
		List<TipoContato> tipoContatoList = new ArrayList<TipoContato>();
		tipoContatoList = tipoContatoRepository.findAll();
		
		for (int i = 0; i < tipoContatoList.size(); i++) {
			tipoContatoList.get(i).setContatos(null);
		}
		
		return tipoContatoList ;
	}
	
	public List<TipoContato> findTipoDocumentoBy(String typeToFind) {
		return tipoContatoRepository.findByType(typeToFind);
	}
	
	public TipoContato findById(Integer id) {
		return tipoContatoRepository.findById(id);
	}
	
	private void validateTipoContato(String type) throws TipoContatoInvalidoException {
		List<TipoContato> tipoContatoList = findAll();
		boolean listHasType = false;
		
		for(int i = 0; i < tipoContatoList.size(); i++) {
			if (tipoContatoList.get(i).getChave().equals(type)) {
				listHasType = true;
				break;
			}
		}
		
		if (listHasType == false) {
			throw new TipoContatoInvalidoException();
		}
	}
}
