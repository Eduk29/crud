package br.com.crud.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.enun.ExceptionMessagesEnun;
import br.com.crud.backend.exception.TipoContatoInvalidoException;
import br.com.crud.backend.model.TipoContato;
import br.com.crud.backend.service.TipoContatoService;

@RestController
@RequestMapping("/tipo-contato")
public class TipoContatoController {

	@Autowired
	private TipoContatoService tipoContatoService;

	@GetMapping(path = "")
	public List<TipoContato> find(@RequestParam(value = "$filter", required = false) String filter) throws TipoContatoInvalidoException {
		try {
			return tipoContatoService.find(filter);
		} catch (TipoContatoInvalidoException e) {
			throw new TipoContatoInvalidoException(ExceptionMessagesEnun.TIPO_CONTATO_ERROR.toString());
		}
	}
}
