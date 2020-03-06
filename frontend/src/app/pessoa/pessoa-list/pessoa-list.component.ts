import {
  Component,
  OnInit,
  ViewChild
} from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Pessoa } from 'src/app/models/Pessoa.model';
import { PessoaService } from 'src/app/services/pessoa.service';
import { Contato } from 'src/app/models/Contato.model';

@Component({
  selector: 'app-pessoa-list',
  templateUrl: './pessoa-list.component.html',
  styleUrls: ['./pessoa-list.component.scss']
})
export class PessoaListComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;

  pessoaList: Array<Pessoa>;
  displayedColumns: string[] = ['id', 'name', 'gender', 'contact', 'actions'];
  dataSource = new MatTableDataSource();

  constructor(private pessoaService: PessoaService) {
    this.getPessoa();
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  getPessoa(): void {
    this.pessoaService.listPessoas().subscribe((response: Array<Pessoa>) => {
      this.pessoaList = response;
      this.dataSource.data = response;
      this.dataSource.paginator = this.paginator;
    });
  }

  displayPrincipalContact(contatos: Array<Contato>): Contato {
    const contatoToDisplay = contatos.find((contato: Contato) => contato.isPrincipal === true);
    return contatoToDisplay;
  }

  editContact(): void {
    console.log('Edit Contact');
  }

  detailContact(): void {
    console.log('Detail Contact');
  }
}
