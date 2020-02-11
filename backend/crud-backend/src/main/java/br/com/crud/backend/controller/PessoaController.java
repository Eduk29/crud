package br.com.crud.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	// Attributes
	@Autowired // Injeta o serviço declarado
	private PessoaService pessoaService;

	// API Methods
	@GetMapping("")
	public List<Pessoa> find(@RequestParam(value = "filter", required = false) String filter) {
		return pessoaService.findAll();
	}

}
