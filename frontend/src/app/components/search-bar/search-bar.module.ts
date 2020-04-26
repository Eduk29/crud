import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule, MatInputModule, MatButtonModule, MatSelectModule } from '@angular/material';
import { SearchBarComponent } from './search-bar.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [SearchBarComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule
  ], exports: [SearchBarComponent]
})
export class SearchBarModule { }
