import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

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
