package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TipoContatoInvalidoException extends Exception {

	@Autowired
	public TipoContatoInvalidoException(String message) {
		super(message);
	}
	
	@Autowired
	public TipoContatoInvalidoException() {
		super();
	}
}
