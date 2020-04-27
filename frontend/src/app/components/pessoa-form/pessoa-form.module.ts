import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule, MatInputModule } from '@angular/material';

import { PessoaFormComponent } from './pessoa-form.component';

@NgModule({
  declarations: [PessoaFormComponent],
  imports: [
    CommonModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  exports: [PessoaFormComponent]
})
export class PessoaFormModule { }
