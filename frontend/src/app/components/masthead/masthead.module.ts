import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MastheadComponent } from './masthead.component';


@NgModule({
  declarations: [MastheadComponent],
  imports: [
    CommonModule
  ],
  exports: [MastheadComponent]
})
export class MastheadModule { }
