import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-masthead',
  templateUrl: './masthead.component.html',
  styleUrls: ['./masthead.component.scss']
})
export class MastheadComponent implements OnInit {

  @Input()
  title: string;

  @Input()
  subtitle: string;

  constructor() { }

  ngOnInit() { }

}
