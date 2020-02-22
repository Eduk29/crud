package br.com.crud.backend.enun;

public enum ExceptionMessagesEnun {

	CEP_ERROR {
		public String toString() {
			return "Cep inválido! Favor verificar.";
		}
	},
	TIPO_DOCUMENTO_ERROR {
		public String toString() {
			return "Tipo de documento inválido! Favor verificar.";
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
	}
}
