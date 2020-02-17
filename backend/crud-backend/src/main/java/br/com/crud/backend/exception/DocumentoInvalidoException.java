package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class DocumentoInvalidoException extends Exception {

	@Autowired
	public DocumentoInvalidoException(String message) {
		super(message);
	}
	
	@Autowired
	public DocumentoInvalidoException() {
		super();
	}
}
