import { Pessoa } from 'src/app/models/Pessoa.model';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { PessoaService } from 'src/app/services/pessoa.service';
import { PessoaModule } from './pessoa.module';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PessoaComponent } from './pessoa.component';
import { buildPessoaList } from 'src/app/tests/utils/buildPessoaList';
import { of } from 'rxjs';
import { buildPessoaResult } from 'src/app/tests/utils/buildPessoaResult';

describe('PessoaComponent', () => {
  let component: PessoaComponent;
  let fixture: ComponentFixture<PessoaComponent>;
  let service: PessoaService;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaComponent ],
      imports: [
        PessoaModule,
        HttpClientModule,
        BrowserAnimationsModule
      ],
      providers: [ PessoaService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(PessoaService);
  });

  it('Should create component', () => {
    expect(component).toBeTruthy();
  });

  it('SearchModeOptions should be filled when component loaded', () => {
    spyOn<any>(component, 'getPessoa');
    fixture.detectChanges();
    expect(component.searchModeOptions.length).toBe(4);
  });

  it('configureSearchFilterForDocumentSearch() should be called when search() is executed', () => {
    spyOn<any>(component, 'getPessoa');
    spyOn<any>(component, 'configureSearchFilterForDocumentSearch');
    spyOn<any>(component, 'listPessoasByFilter');
    component.search();
    expect<any>(component['configureSearchFilterForDocumentSearch']).toHaveBeenCalled();
  });

  it('listPessoasByFilter() should be called when search() is executed', () => {
    spyOn<any>(component, 'getPessoa');
    spyOn<any>(component, 'configureSearchFilterForDocumentSearch');
    spyOn<any>(component, 'listPessoasByFilter');
    component.search();
    expect<any>(component['listPessoasByFilter']).toHaveBeenCalled();
  });

  it('Search Mode should be changed when search mode is CPF', () => {
    spyOn<any>(component, 'getPessoa');
    component.filter = {};
    component.filter.searchMode = {};
    component.filter.searchMode.chave = 'CPF';
    component['configureSearchFilterForDocumentSearch']();

    expect<any>(component.filter.searchMode.chave).toBe('documentoValue');
  });

  it('Search Mode should be changed when search mode is RG', () => {
    spyOn<any>(component, 'getPessoa');
    component.filter = {};
    component.filter.searchMode = {};
    component.filter.searchMode.chave = 'RG';
    component['configureSearchFilterForDocumentSearch']();

    expect<any>(component.filter.searchMode.chave).toBe('documentoValue');
  });

  it('Search Mode should be different of documentoValue when search mode is RG', () => {
    spyOn<any>(component, 'getPessoa');
    component.filter = {};
    component.filter.searchMode = {};
    component.filter.searchMode.chave = 'NAME';
    component['configureSearchFilterForDocumentSearch']();

    expect<any>(component.filter.searchMode.chave).not.toBe('documentoValue');
  });

  it('PessoaList should be filled when service listPessoas called', () => {
    const pessoas = buildPessoaList();

    spyOn(service, 'listPessoas')
      .and.returnValue(of(pessoas));
    component['getPessoa']();

    expect(component.pessoaList.length).toBeGreaterThan(0);
  });

  it('PessoaList should be filled when service listPessoasByFilter called', () => {
    const pessoa = buildPessoaResult();

    spyOn(service, 'listPessoasByFilter')
      .and.returnValue(of(pessoa));
    component['listPessoasByFilter']();

    expect(component.pessoaList.length).toBeGreaterThan(0);
  });

});
