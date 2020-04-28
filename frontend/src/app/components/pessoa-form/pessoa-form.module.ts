import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule, MatSelectModule, MatCheckboxModule } from '@angular/material';

import { PessoaFormComponent } from './pessoa-form.component';

@NgModule({
  declarations: [PessoaFormComponent],
  imports: [
    CommonModule,
    MatCheckboxModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  exports: [PessoaFormComponent]
})
export class PessoaFormModule { }
