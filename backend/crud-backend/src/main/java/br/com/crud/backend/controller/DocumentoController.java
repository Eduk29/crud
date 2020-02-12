package br.com.crud.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.model.Documento;
import br.com.crud.backend.service.DocumentoService;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

	// Attributes
	@Autowired
	private DocumentoService documentoService;
	
	// API Methods
	@GetMapping("")
	public List<Documento> find(@RequestParam(value="$filter", required = false) String filter) {
		return documentoService.find(filter); 
	}
	
	@GetMapping("/{id}")
	public Documento findById(@PathVariable("id") Integer id) {
		return documentoService.findById(id);
	}
	
	@PostMapping(path = "/novo", consumes = "application/json")
	public boolean save(@RequestBody Documento documento) {
		return documentoService.save(documento);
	}
	
}
