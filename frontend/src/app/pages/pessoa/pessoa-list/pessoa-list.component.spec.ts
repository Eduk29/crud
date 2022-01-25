import { Pessoa } from 'src/app/models/Pessoa.model';
import { Contato } from 'src/app/models/Contato.model';
import { buildPessoaResult } from 'src/app/tests/utils/buildPessoaResult';
import { buildPessoaList } from 'src/app/tests/utils/buildPessoaList';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { PessoaModule } from './../pessoa.module';
import { PessoaListComponent } from './pessoa-list.component';
import { Router } from '@angular/router';
import { SimpleChange, SimpleChanges } from '@angular/core';


describe('PessoaListComponent', () => {
  let component: PessoaListComponent;
  let fixture: ComponentFixture<PessoaListComponent>;
  let router: Router;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaListComponent ],
      imports: [ PessoaModule, RouterTestingModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaListComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('displayedColumns should be different of empty list when component is created', () => {
    expect(component.displayedColumns.length).toBeGreaterThan(0);
  });

  it('displayPrincipalContact should return principal contact when called', () => {
    var contato1 = new Contato();
    contato1.isPrincipal = true;
    var contato2 = new Contato();
    contato2.isPrincipal = false;

    var retorno = component.displayPrincipalContact([contato1, contato2]);
    expect(retorno.isPrincipal).toBeTrue();
  });

  it('editContact should redirect to edit page when called', () => {
    let pessoa = new Pessoa();
    pessoa.id = 1;
    spyOn(router, 'navigate').and.stub();
    component.editContact(pessoa);

    expect(router.navigate).toHaveBeenCalledWith(['pessoa', 1, 'edit']);
  });

  it('detailContact should redirect to detail page when called', () => {
    let pessoa = new Pessoa();
    pessoa.id = 1;
    spyOn(router, 'navigate').and.stub();
    component.detailContact(pessoa);

    expect(router.navigate).toHaveBeenCalledWith(['pessoa', 1, 'detail']);
  });

  it('ngOnChanges should fill the datasource with (@Input pessoa) changes when called', () => {
    component.pessoaList = buildPessoaList();
    fixture.detectChanges();

    const changes: SimpleChanges = {
      pessoaList: new SimpleChange([], component.pessoaList, true)
    };

    component.ngOnChanges(changes);

    expect(component.dataSource.data.length).toBeGreaterThan(0);
  });
});
