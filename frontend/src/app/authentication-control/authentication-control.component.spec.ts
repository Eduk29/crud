import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticationControlComponent } from './authentication-control.component';

describe('AuthenticationControlComponent', () => {
  let component: AuthenticationControlComponent;
  let fixture: ComponentFixture<AuthenticationControlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthenticationControlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthenticationControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
