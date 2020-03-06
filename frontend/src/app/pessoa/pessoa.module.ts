import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaComponent } from './pessoa.component';
import { PessoaListComponent } from './pessoa-list/pessoa-list.component';
import { PessoaDetailComponent } from './pessoa-detail/pessoa-detail.component';
import {
  MatPaginatorModule,
  MatIconModule,
  MatTableModule,
  MatButtonModule
} from '@angular/material';


@NgModule({
  declarations: [PessoaComponent, PessoaListComponent, PessoaDetailComponent],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule
  ],
  exports: [PessoaListComponent, PessoaDetailComponent]
})
export class PessoaModule {}
