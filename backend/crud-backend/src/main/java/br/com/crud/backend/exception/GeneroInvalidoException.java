package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class GeneroInvalidoException extends Exception {

	@Autowired
	public GeneroInvalidoException(String message) {
		super(message);
	}
	
	@Autowired
	public GeneroInvalidoException() {
		super();
	}
}
