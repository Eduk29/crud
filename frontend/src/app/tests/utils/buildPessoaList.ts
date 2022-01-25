import { Pessoa } from 'src/app/models/Pessoa.model';

export function buildPessoaList(): Pessoa[] {
  const pessoas: Pessoa[] = [];
  for (let i = 0; i < 8; i++) {
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
};

