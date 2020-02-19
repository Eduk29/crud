package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class TipoDocumentoInvalidoException extends Exception {

	@Autowired
	public TipoDocumentoInvalidoException(String message) {
		super(message);
	}
	
	@Autowired
	public TipoDocumentoInvalidoException() {
		super();
	}
}
