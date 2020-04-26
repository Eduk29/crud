import { SearchBarModule } from '../components/search-bar/search-bar.module';
import { MastheadModule } from '../components/masthead/masthead.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaComponent } from './pessoa.component';
import { PessoaListComponent } from './pessoa-list/pessoa-list.component';
import { PessoaDetailComponent } from './pessoa-detail/pessoa-detail.component';
import { PessoaRoutingModule } from './pessoa-routing.module';
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
    MastheadModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    PessoaRoutingModule,
    SearchBarModule
  ],
  exports: [PessoaListComponent, PessoaDetailComponent]
})
export class PessoaModule { }
