package br.com.crud.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.backend.model.Documento;
import br.com.crud.backend.repository.DocumentoRepository;

@Service
@Transactional 
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;
	
	public List<Documento> findAll() {
		return documentoRepository.findAll();
	}
}
