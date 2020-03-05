import { Component, OnInit } from '@angular/core';
import { PessoaService } from 'src/app/services/pessoa.service';
import { Pessoa } from 'src/app/models/Pessoa.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor( private pessoaService: PessoaService) { }

  ngOnInit() {
    this.getPessoa();
  }

  getPessoa(): void {
    this.pessoaService
      .listPessoas()
      .subscribe((response: Array<Pessoa>) => {
        console.log('Response: ', response);
      });
  }

}
