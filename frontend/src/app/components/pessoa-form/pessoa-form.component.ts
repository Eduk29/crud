import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { TipoDocumento } from '../../models/TipoDocumento.model';
import { TipoContato } from './../../models/TipoContato.model';

const TIPOS_DOCUMENTO: Array<TipoDocumento> = [
  {
    id: 1,
    chave: 'RG',
    valor: 'RG'
  },
  {
    id: 2,
    chave: 'CPF',
    valor: 'CPF'
  }
];

const TIPOS_CONTATO: Array<TipoContato> = [
  {
    id: 1,
    chave: 'TELEFONE',
    valor: 'Telefone'
  },
  {
    id: 2,
    chave: 'CELULAR',
    valor: 'Celular'
  },
  {
    id: 3,
    chave: 'EMAIL',
    valor: 'Email'
  }
];

@Component({
  selector: 'app-pessoa-form',
  templateUrl: './pessoa-form.component.html',
  styleUrls: ['./pessoa-form.component.scss']
})
export class PessoaFormComponent implements OnInit {

  tiposDocumento = TIPOS_DOCUMENTO;
  tiposContato = TIPOS_CONTATO;

  name = new FormControl('');
  gender = new FormControl('');
  documentType = new FormControl('');
  documentValue = new FormControl('');
  contactType = new FormControl('');
  contactValue = new FormControl('');
  contactIsPrincipal = new FormControl('');
  address = new FormControl('');
  numberAddress = new FormControl('');
  complementAddress = new FormControl('');
  cep = new FormControl('');
  city = new FormControl('');
  state = new FormControl('');

  constructor() { }

  ngOnInit() {
  }

}
