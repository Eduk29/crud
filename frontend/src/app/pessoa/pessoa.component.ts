import { Pessoa } from './../models/Pessoa.model';
import { PessoaService } from './../services/pessoa.service';
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

  pessoaList: Array<Pessoa>;
  filter: any;
  searchModeOptions: Array<ChaveValor> = SEARCH_MODE_OPTION_LIST;

  constructor(private pessoaService: PessoaService) {
    this.getPessoa();
  }

  ngOnInit() {
    this.filter = {};
    this.filter.searchMode = SEARCH_MODE_OPTION_LIST.filter(option => option.chave === 'NAME').shift();
    this.filter.searchValue = '';
  }

  search(): void {
    if (this.filter.searchMode.chave === 'CPF' || this.filter.searchMode.chave === 'RG') {
      this.filter.searchMode.chave = 'documentoValue';
    }

    this.pessoaService
      .listPessoasByFilter(this.filter.searchMode.chave.toLowerCase(), this.filter.searchValue)
      .subscribe(
        (response: Array<Pessoa>) => {
          this.pessoaList = response;
        }
      )
  }

  getPessoa(): void {
    this.pessoaService
      .listPessoas()
      .subscribe(
        (response: Array<Pessoa>) => {
          this.pessoaList = response;
        }
      );
  }

}
