package br.com.crud.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.backend.enun.ExceptionMessagesEnun;
import br.com.crud.backend.exception.TipoDocumentoInvalidoException;
import br.com.crud.backend.model.Documento;
import br.com.crud.backend.model.TipoDocumento;
import br.com.crud.backend.service.TipoDocumentoService;

@RestController
@RequestMapping("/tipo-documento")
public class TipoDocumentoController {

	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	@GetMapping(path = "")
	public List<TipoDocumento> find(@RequestParam(value="$filter", required = false) String filter) throws TipoDocumentoInvalidoException {
		try {
			List<TipoDocumento> tipoDocumentoList = this.tipoDocumentoService.find(filter);
			for (int i = 0; i < tipoDocumentoList.size(); i++) {
				tipoDocumentoList.get(i).setDocumentos(null);
			}
			
			return 	tipoDocumentoList;
		} catch (TipoDocumentoInvalidoException e) {
			throw new TipoDocumentoInvalidoException(ExceptionMessagesEnun.TIPO_DOCUMENTO_ERROR.toString());
		}
		
	}
}
