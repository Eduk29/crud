import { Pessoa } from 'src/app/models/Pessoa.model';

export function buildPessoaResult(): Pessoa[] {
  const pessoas: Pessoa[] = [];
  for (let i = 0; i < 1; i++) {
    pessoas.push({
      id: i + 1,
      contato: [],
      dataNascimento: null,
      documentos: [],
      endereco: null,
      genero: '',
      nome: ''
    });
  }
  return pessoas;
}
