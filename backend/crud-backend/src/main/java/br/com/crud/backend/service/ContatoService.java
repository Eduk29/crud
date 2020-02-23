package br.com.crud.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.model.Contato;
import br.com.crud.backend.repository.ContatoRepository;
import br.com.crud.backend.utils.ServiceUtils;

@Service
@Transactional
public class ContatoService {

	// Attributes
	@Autowired
	private ContatoRepository contatoRepository;
	
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
			
			case "Value":
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
}
