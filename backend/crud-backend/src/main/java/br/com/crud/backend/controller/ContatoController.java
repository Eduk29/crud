package br.com.crud.backend.controller;

import java.util.ArrayList;
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
import br.com.crud.backend.exception.ContatoInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.TipoContatoInvalidoException;
import br.com.crud.backend.model.Contato;
import br.com.crud.backend.model.Documento;
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

	@PostMapping(path = "/novo", consumes = "application/json")
	public Contato save(@RequestBody Contato contato) throws TipoContatoInvalidoException, ContatoInvalidoException {
		try {
			return contatoService.save(contato);
		} catch (TipoContatoInvalidoException e) {
			throw new TipoContatoInvalidoException(ExceptionMessagesEnun.TIPO_CONTATO_ERROR.toString());
		} catch (ContatoInvalidoException e) {
			throw new ContatoInvalidoException(ExceptionMessagesEnun.CONTATO_ERROR.toString());
		}
	}

	@DeleteMapping(path = "/{id}/remover")
	public Contato removeById(@PathVariable("id") Integer id) {
		return contatoService.removeById(id);
	}

	@PutMapping(path = "/{id}/alterar")
	public Contato updateById(@RequestBody Contato contato, @PathVariable("id") Integer id) throws TipoContatoInvalidoException, ContatoInvalidoException {
		try {
			return contatoService.updateById(id, contato);
		} catch (TipoContatoInvalidoException e) {
			throw new TipoContatoInvalidoException(ExceptionMessagesEnun.TIPO_CONTATO_ERROR.toString());
		} catch (ContatoInvalidoException e) {
			throw new ContatoInvalidoException(ExceptionMessagesEnun.CONTATO_ERROR.toString());
		}
	}
}
