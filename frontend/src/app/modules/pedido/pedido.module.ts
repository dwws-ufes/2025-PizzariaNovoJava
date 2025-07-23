import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PedidoRoutingModule } from './pedido-routing.module';
import { CaixaComponent } from './caixa/caixa.component';
import { PedidoClienteComponent } from './pedido-cliente/pedido-cliente.component';
import { PedidoComponent } from './pedido/pedido.component';


@NgModule({
  declarations: [
    CaixaComponent,
    PedidoClienteComponent,
    PedidoComponent
  ],
  imports: [
    CommonModule,
    PedidoRoutingModule
  ]
})
export class PedidoModule { }
