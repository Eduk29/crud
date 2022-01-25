import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { ChaveValor } from '../../models/ChaveValor.model';


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})

export class SearchBarComponent implements OnInit {

  @Input() filter;
  @Input() searchModeOptions: ChaveValor[];
  @Output() searchEvent = new EventEmitter();

  constructor() { }

  ngOnInit() { }

  search(): void {
    this.searchEvent.emit(this.filter);
  }

}


