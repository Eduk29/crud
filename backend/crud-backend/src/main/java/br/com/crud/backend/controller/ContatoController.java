package br.com.crud.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.model.Contato;
import br.com.crud.backend.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	// Attributes
	@Autowired
	private ContatoService contatoService;

	// API Methods
	@GetMapping("")
	public List<Contato> find(@RequestParam(value = "$filter", required = false) String filter) {
		return contatoService.find(filter);
	}
	
	@GetMapping("/{id}")
	public List<Contato> findById(@PathVariable Integer id) {
		List<Contato> contatoList = new ArrayList<Contato>();
		Contato contato = contatoService.findById(id);
		contatoList.add(contato);
		return contatoList;
	}
}
