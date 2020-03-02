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
import br.com.crud.backend.exception.ContatoInvalidoException;
import br.com.crud.backend.exception.DocumentoInvalidoException;
import br.com.crud.backend.exception.GeneroInvalidoException;
import br.com.crud.backend.exception.TipoContatoInvalidoException;
import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
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
	public List<Pessoa> find(@RequestParam(value = "$filter", required = false) String filter)
			throws GeneroInvalidoException, TipoContatoInvalidoException, CepInvalidoException {
		try {
			return this.pessoaService.find(filter);
		} catch (GeneroInvalidoException e) {
			throw new GeneroInvalidoException(ExceptionMessagesEnun.GENERO_ERROR.toString());
		} catch (TipoDocumentoInvalidoException e) {
			throw new TipoContatoInvalidoException(ExceptionMessagesEnun.TIPO_DOCUMENTO_ERROR.toString());
		} catch (CepInvalidoException e) {
			throw new CepInvalidoException(ExceptionMessagesEnun.CEP_ERROR.toString());
		}
	}

	@GetMapping("/{id}") // Mapeia o endpoint /{id} dentro do /pessoas
	public Pessoa findById(@PathVariable("id") Integer id) {
		return this.pessoaService.findById(id);
	}

	@PostMapping(path = "/novo", consumes = "application/json")
	public Pessoa save(@RequestBody Pessoa pessoa) throws DocumentoInvalidoException, GeneroInvalidoException,
			CepInvalidoException, ContatoInvalidoException, TipoContatoInvalidoException, TipoDocumentoInvalidoException {
		try {
			return this.pessoaService.save(pessoa);
		} catch (DocumentoInvalidoException e) {
			throw new DocumentoInvalidoException(ExceptionMessagesEnun.DOCUMENTO_ERROR.toString());
		} catch (GeneroInvalidoException e) {
			throw new GeneroInvalidoException(ExceptionMessagesEnun.GENERO_ERROR.toString());
		} catch (CepInvalidoException e) {
			throw new CepInvalidoException(ExceptionMessagesEnun.CEP_ERROR.toString());
		} catch (ContatoInvalidoException e) {
			throw new ContatoInvalidoException(ExceptionMessagesEnun.CONTATO_ERROR.toString());
		} catch (TipoContatoInvalidoException e) {
			throw new TipoContatoInvalidoException(ExceptionMessagesEnun.TIPO_CONTATO_ERROR.toString());
		} catch (TipoDocumentoInvalidoException e) {
			throw new TipoDocumentoInvalidoException(ExceptionMessagesEnun.TIPO_DOCUMENTO_ERROR.toString());
		}
	}
	
	@DeleteMapping(path = "/{id}/remover")
	public Pessoa removeById(@PathVariable("id") Integer id) {
		return this.pessoaService.removeById(id);
	}
	
	@PutMapping(path = "/{id}/alterar")
	public Pessoa updateById(@RequestBody Pessoa pessoa, @PathVariable("id") Integer id) throws TipoContatoInvalidoException, ContatoInvalidoException, GeneroInvalidoException {
		try {
			return this.pessoaService.updateById(id, pessoa);
		} catch (TipoDocumentoInvalidoException e) {
			throw new TipoContatoInvalidoException(ExceptionMessagesEnun.TIPO_DOCUMENTO_ERROR.toString());
		} catch (DocumentoInvalidoException e) {
			throw new ContatoInvalidoException(ExceptionMessagesEnun.CONTATO_ERROR.toString());
		}
	}
}
