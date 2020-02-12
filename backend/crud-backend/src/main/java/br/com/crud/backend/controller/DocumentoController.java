package br.com.crud.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.model.Documento;
import br.com.crud.backend.service.DocumentoService;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

	// Attributes
	@Autowired
	private DocumentoService documentoService;
	
	// API Methods
	@GetMapping("")
	public List<Documento> findAll() {
		return documentoService.findAll(); 
	}
}
