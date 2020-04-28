import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { DocumentFormComponent } from '../../components/document-form/document-form.component';

@NgModule({
  declarations: [
    DocumentFormComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DocumentFormComponent
  ]
})
export class DocumentFormModule { }
