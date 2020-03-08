import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
   MatButtonModule,
   MatCardModule,
   MatIconModule,
   MatToolbarModule,
   MatChipsModule,

} from '@angular/material';

@NgModule({
   imports: [
      CommonModule,
      MatButtonModule,
      MatCardModule,
      MatChipsModule,
      MatIconModule,
      MatToolbarModule,
   ],
   exports: [
      MatButtonModule,
      MatCardModule,
      MatChipsModule,
      MatToolbarModule,
      MatIconModule
   ],
   providers: []
})

export class AngularMaterialModule { }