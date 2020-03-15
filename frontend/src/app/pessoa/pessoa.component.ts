import { ChaveValor } from './../models/ChaveValor.model';
import { Component, OnInit } from '@angular/core';

const SEARCH_MODE_OPTION_LIST: Array<ChaveValor> = [
  {
    chave: 'NAME',
    valor: 'Name'
  },
  {
    chave: 'CPF',
    valor: 'CPF'
  },
  {
    chave: 'RG',
    valor: 'RG'
  }
]

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.scss']
})
export class PessoaComponent implements OnInit {

  filter: any;
  searchModeOptions: Array<ChaveValor> = SEARCH_MODE_OPTION_LIST;

  constructor() { }

  ngOnInit() {
    this.filter = {};
    this.filter.searchMode = SEARCH_MODE_OPTION_LIST.filter(option => option.chave === 'NAME').shift();
  }

  search(): void {
    console.log('Filter: ', this.filter);
  }

}
