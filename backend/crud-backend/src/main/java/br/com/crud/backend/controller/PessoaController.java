package br.com.crud.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.exception.CepInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.GeneroInvalidoException;
import br.com.crud.backend.model.Pessoa;
import br.com.crud.backend.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	// Attributes
	@Autowired // Injeta o serviço declarado
	private PessoaService pessoaService;

	// API Methods
	@GetMapping("") // Mapeia o endpoint '/' dentro do '/pessoas'
	public List<Pessoa> find(@RequestParam(value = "$filter", required = false) String filter) throws GeneroInvalidoException {
		try {
			return pessoaService.find(filter);
		} catch (GeneroInvalidoException e) {
			throw new GeneroInvalidoException("Genero Invalido! Favor Verificar");
		}
	}

	@GetMapping("/{id}") // Mapeia o endpoint /{id} dentro do /pessoas
	public Pessoa findById(@PathVariable("id") Integer id) {
		return pessoaService.findById(id);
	}

	@PostMapping(path = "/novo", consumes = "application/json")
	public Pessoa save(@RequestBody Pessoa pessoa) throws DocumentoInvalidoException, GeneroInvalidoException, CepInvalidoException {
		try {
			return pessoaService.save(pessoa);
		} catch (DocumentoInvalidoException e) {
			throw new DocumentoInvalidoException("Documento Invalido! Favor Verificar");
		} catch (GeneroInvalidoException e) {
			throw new GeneroInvalidoException("Genero Invalido! Favor Verificar");
		} catch (CepInvalidoException e) {
			throw new CepInvalidoException("Cep Inválido! Favor Verificar");
		}
	}
	
	@DeleteMapping(path = "/{id}/remover")
	public Pessoa removeById(@PathVariable("id") Integer id) {
		return pessoaService.removeById(id);
	}
	
	@PutMapping(path = "/{id}/alterar")
	public Pessoa updateById(@RequestBody Pessoa pessoa, @PathVariable("id") Integer id) {
		return pessoaService.updateById(id, pessoa);
	}
}
