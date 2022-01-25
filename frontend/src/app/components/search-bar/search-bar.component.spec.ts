import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { SearchBarComponent } from './search-bar.component';

describe('SearchBarComponent', () => {
  let component: SearchBarComponent;
  let fixture: ComponentFixture<SearchBarComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    component.filter = {};
    component.filter.searchMode = '';
    component.searchModeOptions = [];
    expect(component).toBeTruthy();
  });

  it('search should trigger (@Output searchEvent) when called', () => {
    spyOn(component.searchEvent, 'emit');
    fixture.detectChanges();
    component.search();
    expect(component.searchEvent.emit).toHaveBeenCalled();
  });
});
