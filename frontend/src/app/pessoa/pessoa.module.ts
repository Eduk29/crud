import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaComponent } from './pessoa.component';
import { PessoaListComponent } from './pessoa-list/pessoa-list.component';
import {MatPaginatorModule} from '@angular/material';
import {MatTableModule} from '@angular/material';

@NgModule({
  declarations: [PessoaComponent, PessoaListComponent],
  imports: [CommonModule, MatPaginatorModule, MatTableModule],
  exports: [PessoaListComponent]
})
export class PessoaModule {}
