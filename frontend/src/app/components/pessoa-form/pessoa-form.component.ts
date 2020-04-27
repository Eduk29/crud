import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-pessoa-form',
  templateUrl: './pessoa-form.component.html',
  styleUrls: ['./pessoa-form.component.scss']
})
export class PessoaFormComponent implements OnInit {

  name = new FormControl('');
  gender = new FormControl('');

  constructor() { }

  ngOnInit() {
  }

}
