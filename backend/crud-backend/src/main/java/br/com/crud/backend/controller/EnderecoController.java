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
import br.com.crud.backend.model.Endereco;
import br.com.crud.backend.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	// Attributes
	@Autowired
	private EnderecoService enderecoService;
	
	// Methods
	@GetMapping("")
	public List<Endereco> findAll(@RequestParam(value="$filter", required = false) String filter) {
		return enderecoService.find(filter);
	}
	
	@GetMapping("/{id}")
	public Endereco findById(@PathVariable("id") Integer id) {
		return enderecoService.findById(id);
	}
	
	@PostMapping(path = "/novo", consumes = "application/json")
	public Endereco save (@RequestBody Endereco endereco) {
		return enderecoService.save(endereco);
	}
}
