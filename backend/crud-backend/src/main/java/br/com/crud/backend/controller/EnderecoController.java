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

import br.com.crud.backend.enun.ExceptionMessagesEnun;
import br.com.crud.backend.exception.CepInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
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
	public List<Endereco> findAll(@RequestParam(value = "$filter", required = false) String filter)
			throws CepInvalidoException {
		try {
			return this.enderecoService.find(filter);
		} catch (CepInvalidoException e) {
			throw new CepInvalidoException(ExceptionMessagesEnun.CEP_ERROR.toString());
		}
	}

	@GetMapping("/{id}")
	public Endereco findById(@PathVariable("id") Integer id) {
		return this.enderecoService.findById(id);
	}

	@PostMapping(path = "/novo", consumes = "application/json")
	public Endereco save(@RequestBody Endereco endereco) throws CepInvalidoException {
		try {
			return this.enderecoService.save(endereco);
		} catch (CepInvalidoException e) {
			throw new CepInvalidoException(ExceptionMessagesEnun.CEP_ERROR.toString());
		}
	}

	@DeleteMapping(path = "/{id}/remover")
	public Endereco removeById(@PathVariable("id") Integer id) {
		return this.enderecoService.removeById(id);
	}

	@PutMapping(path = "/{id}/alterar")
	public Endereco updateById(@RequestBody Endereco endereco, @PathVariable("id") Integer id) {
		return enderecoService.updateById(id, endereco);
	}
}
