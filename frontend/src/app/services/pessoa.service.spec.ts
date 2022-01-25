import { buildPessoaResult } from 'src/app/tests/utils/buildPessoaResult';
import { ChaveValor } from './../models/ChaveValor.model';
import { FilterPessoa } from './../models/FilterPessoa.model';
import { environment } from 'src/environments/environment';
import { Pessoa } from 'src/app/models/Pessoa.model';
import { buildPessoaList } from 'src/app/tests/utils/buildPessoaList';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed, waitForAsync } from '@angular/core/testing';
import { PessoaService } from 'src/app/services/pessoa.service';

const mockDataList = {
  api: `${environment.urlApi}/pessoas`,
  data: buildPessoaList()
};

const mockDataFilter = {
  api: `${environment.urlApi}/pessoas?$filter="name=test"`,
  data: buildPessoaResult()
}

describe('PessoaService', () => {
  let service: PessoaService;
  let httpController: HttpTestingController;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PessoaService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    service = TestBed.inject(PessoaService);
    httpController = TestBed.inject(HttpTestingController);
  });

  afterAll(() => httpController.verify());

  it('listPessoas should return pessoa list when called', done => {
    service.listPessoas().subscribe((pessoas: Pessoa[]) => {
      expect(pessoas.length).toBeGreaterThan(0);
      done();
    });

    httpController
      .expectOne(mockDataList.api)
      .flush(mockDataList.data);
  });

  it('listPessoasByFilter should return pessoa filtered when called', done => {
    const searchMode: ChaveValor = { chave: 'NAME', valor: 'Nome' };
    const filter: FilterPessoa = { searchMode, searchValue: 'test'};

    service.listPessoasByFilter(filter).subscribe((pessoas: Pessoa[]) => {
      expect(pessoas.length).toBeGreaterThan(0);
      done();
    });

    httpController
      .expectOne(mockDataFilter.api)
      .flush(mockDataFilter.data);
  });

});
