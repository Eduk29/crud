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
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Pessoa } from 'src/app/models/Pessoa.model';
import { PessoaService } from 'src/app/services/pessoa.service';
import { Contato } from 'src/app/models/Contato.model';
import { Router, ActivatedRoute } from '@angular/router';

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

  constructor(
    private ref: ChangeDetectorRef,
    private router: Router) { }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.dataSource.data = changes.pessoaList.currentValue;
  }

  displayPrincipalContact(contatos: Array<Contato>): Contato {
    return contatos.find((contato: Contato) => contato.isPrincipal === true);
  }

  editContact(item: Pessoa): void {
    this.router.navigate(['pessoa', item.id, 'edit']);
  }

  detailContact(pessoa: Pessoa): void {
    this.router.navigate(['pessoa', pessoa.id, 'detail']);
  }
}
