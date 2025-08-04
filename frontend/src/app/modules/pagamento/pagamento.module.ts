import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagamentoRoutingModule } from './pagamento-routing.module';
import { CaixaPagamentoComponent } from './caixa-pagamento/caixa-pagamento.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    CaixaPagamentoComponent
  ],
  imports: [
    CommonModule,
    PagamentoRoutingModule,
    SharedModule
  ],
  exports: [
    CaixaPagamentoComponent
  ]
})
export class PagamentoModule { }
