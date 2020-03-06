import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PessoaComponent } from './pessoa.component';


const routes: Routes = [
  {
    path: '',
    component: PessoaComponent
  },
  {
    path: '/detail',
    component: PessoaDetailComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PessoaRoutingModule {}