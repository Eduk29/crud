import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-authentication-control',
  templateUrl: './authentication-control.component.html',
  styleUrls: ['./authentication-control.component.scss']
})
export class AuthenticationControlComponent implements OnInit {

  @Input()
  userAuthorized: boolean;

  constructor() { }

  ngOnInit() {
  }

}
