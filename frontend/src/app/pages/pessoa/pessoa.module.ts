import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';

import { MastheadModule } from '../../components/masthead/masthead.module';
import { SearchBarModule } from '../../components/search-bar/search-bar.module';
import { PessoaFormModule } from './../../components/pessoa-form/pessoa-form.module';
import { PessoaDetailComponent } from './pessoa-detail/pessoa-detail.component';
import { PessoaListComponent } from './pessoa-list/pessoa-list.component';
import { PessoaRoutingModule } from './pessoa-routing.module';
import { PessoaComponent } from './pessoa.component';


@NgModule({
  declarations: [PessoaComponent, PessoaListComponent, PessoaDetailComponent],
  imports: [
    CommonModule,
    MastheadModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    PessoaFormModule,
    PessoaRoutingModule,
    SearchBarModule
  ],
  exports: [PessoaListComponent, PessoaDetailComponent]
})
export class PessoaModule { }
