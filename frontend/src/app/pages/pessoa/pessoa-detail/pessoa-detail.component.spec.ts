import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PessoaDetailComponent } from './pessoa-detail.component';

describe('PessoaDetailComponent', () => {
  let component: PessoaDetailComponent;
  let fixture: ComponentFixture<PessoaDetailComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
