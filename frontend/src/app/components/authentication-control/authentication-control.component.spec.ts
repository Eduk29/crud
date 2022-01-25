import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { AuthenticationControlComponent } from './authentication-control.component';

describe('AuthenticationControlComponent', () => {
  let component: AuthenticationControlComponent;
  let fixture: ComponentFixture<AuthenticationControlComponent>;

  beforeEach(waitForAsync(() => {
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
