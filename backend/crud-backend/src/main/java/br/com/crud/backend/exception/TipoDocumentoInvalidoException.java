package br.com.crud.backend.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//https://dzone.com/articles/spring-rest-service-exception-handling-1
//@ResponseStatus(value = HttpStatus.BAD_REQUEST)	
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
