import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaComponent } from './pessoa.component';
import { PessoaListComponent } from './pessoa-list/pessoa-list.component';

@NgModule({
  declarations: [PessoaComponent, PessoaListComponent],
  imports: [CommonModule],
  exports: [PessoaListComponent]
})
export class PessoaModule {}
