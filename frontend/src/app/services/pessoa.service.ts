import { ChaveValor } from './../models/ChaveValor.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Pessoa } from '../models/Pessoa.model';
import { FilterPessoa } from './../models/FilterPessoa.model';

@Injectable()
export class PessoaService {
  private urlAPI: string;

  constructor(private httpRequest: HttpClient) {
    this.urlAPI = `${environment.urlApi}/pessoas`;
  }

  listPessoas(): Observable<Array<Pessoa>> {
    return this.httpRequest
      .get<Array<Pessoa>>(this.urlAPI);
  }

  listPessoasByFilter(filter: FilterPessoa): Observable<Array<Pessoa>> {

    const { searchMode, searchValue } = filter;

    return this.httpRequest
      .get<Array<Pessoa>>(`${this.urlAPI}?$filter="${searchMode.chave.toLowerCase()}=${searchValue}"`);
  }
}
