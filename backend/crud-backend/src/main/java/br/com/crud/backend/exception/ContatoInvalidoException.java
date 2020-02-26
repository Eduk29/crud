package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContatoInvalidoException extends Exception {

	@Autowired
	public ContatoInvalidoException(String message) {
		super(message);
	}
	
	@Autowired
	public ContatoInvalidoException() {
		super();
	}
}
