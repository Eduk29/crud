import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
   MatButtonModule,
   MatIconModule,
   MatToolbarModule,

} from '@angular/material';

@NgModule({
   imports: [
      CommonModule,
      MatButtonModule,
      MatIconModule,
      MatToolbarModule,
   ],
   exports: [
      MatButtonModule,
      MatToolbarModule,
      MatIconModule
   ],
   providers: []
})

export class AngularMaterialModule { }