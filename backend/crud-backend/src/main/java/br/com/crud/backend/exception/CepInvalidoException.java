package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class CepInvalidoException extends Exception {

	@Autowired
	public CepInvalidoException(String message) {
		super(message);
	}
	
	@Autowired
	public CepInvalidoException() {
		super();
	}
}
