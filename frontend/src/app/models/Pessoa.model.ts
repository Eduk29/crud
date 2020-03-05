import { Contato } from "./Contato.model";

export class Pessoa {
  contato?: Array<Contato>;
  dataNascimento?: Date;
  documentos?: Array<Documento>;
  endereco?: Endereco;
  genero?: string;
  id?: number;
  nome?: string;
}
