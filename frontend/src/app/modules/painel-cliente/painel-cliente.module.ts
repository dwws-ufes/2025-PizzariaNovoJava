import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PainelClienteRoutingModule} from './painel-cliente-routing.module';
import {PainelGarcomComponent} from './painel-garcom/painel-garcom.component';
import {PizzaPainelComponent} from './pizza-painel/pizza-painel.component';
import {BebidaPainelComponent} from './bebida-painel/bebida-painel.component';
import {SobremesaPainelComponent} from './sobremesa-painel/sobremesa-painel.component';
import {PedidoListComponent} from './painel-garcom/pedido-list/pedido-list.component'
import {SharedModule} from "../../shared/shared.module";
import { BadgeModule } from 'primeng/badge';


@NgModule({
  declarations: [
    PainelGarcomComponent,
    PizzaPainelComponent,
    BebidaPainelComponent,
    SobremesaPainelComponent,
    PedidoListComponent,
  ],
  exports: [
    PainelGarcomComponent,
    PedidoListComponent
  ],
  imports: [
    CommonModule,
    PainelClienteRoutingModule,
    SharedModule,
    BadgeModule
  ]
})
export class PainelClienteModule {
}
