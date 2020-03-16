import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Pessoa } from '../models/Pessoa.model';
import { Observable } from 'rxjs';

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

  listPessoasByFilter(filter: object): Observable<Array<Pessoa>> {

    const { searchMode } = filter;

    return this.httpRequest
      .get<Array<Pessoa>>(`${this.urlAPI}?$filter="${searchMode}=${searchValue}"`)
  }
}
