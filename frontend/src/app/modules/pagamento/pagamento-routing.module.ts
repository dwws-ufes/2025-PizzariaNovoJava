import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CaixaPagamentoComponent} from "./caixa-pagamento/caixa-pagamento.component"

const routes: Routes = [
  {path: '', component: CaixaPagamentoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagamentoRoutingModule { }
