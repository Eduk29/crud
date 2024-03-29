import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { MastheadComponent } from './masthead.component';

describe('MastheadComponent', () => {
  let component: MastheadComponent;
  let fixture: ComponentFixture<MastheadComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ MastheadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MastheadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
