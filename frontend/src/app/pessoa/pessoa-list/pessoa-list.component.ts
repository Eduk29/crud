import {
  Component,
  OnInit,
  ViewChild,
  Input,
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  OnChanges,
  SimpleChanges
} from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Pessoa } from 'src/app/models/Pessoa.model';
import { PessoaService } from 'src/app/services/pessoa.service';
import { Contato } from 'src/app/models/Contato.model';

@Component({
  selector: 'app-pessoa-list',
  templateUrl: './pessoa-list.component.html',
  styleUrls: ['./pessoa-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PessoaListComponent implements AfterViewInit, OnChanges {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @Input() pessoaList: Array<Pessoa>;

  displayedColumns: string[] = ['id', 'name', 'gender', 'contact', 'actions'];
  dataSource = new MatTableDataSource();

  constructor(private ref: ChangeDetectorRef) { }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.dataSource.data = changes.pessoaList.currentValue;
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
