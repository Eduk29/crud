import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';

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