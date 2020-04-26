import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PessoaComponent } from './pessoa.component';
import { PessoaDetailComponent } from './pessoa-detail/pessoa-detail.component';


const routes: Routes = [
  {
    path: '',
    component: PessoaComponent
  },
  {
    path: ':id/edit',
    component: PessoaDetailComponent
  },
  {
    path: ':id/detail',
    component: PessoaDetailComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PessoaRoutingModule { }
