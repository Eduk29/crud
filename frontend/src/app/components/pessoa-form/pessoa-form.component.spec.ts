import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PessoaFormComponent } from './pessoa-form.component';

describe('PessoaFormComponent', () => {
  let component: PessoaFormComponent;
  let fixture: ComponentFixture<PessoaFormComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
