package br.com.crud.backend.enun;

public enum ExceptionMessagesEnun {

	CEP_ERROR {
		public String toString() {
			return "Cep inv�lido! Favor verificar.";
		}
	},
	TIPO_DOCUMENTO_ERROR {
		public String toString() {
			return "Tipo de documento inv�lido! Favor verificar.";
		}
	},
	DOCUMENTO_ERROR {
		public String toString() {
			return "Documento Invalido! Favor Verificar";
		}
	},
	GENERO_ERROR {
		public String toString() {
			return "Genero Invalido! Favor Verificar";
		}
	},
	TIPO_CONTATO_ERROR {
		public String toString() {
			return "Tipo de contato inv�lido! Favor verificar.";
		}
	},
	CONTATO_ERROR {
		public String toString() {
			return "Contato inv�lido! Favor verificar.";
		}
	}
}
