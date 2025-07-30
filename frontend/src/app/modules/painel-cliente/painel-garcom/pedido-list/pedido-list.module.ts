import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PedidoListRoutingModule} from './pedido-list-routing.module';
import {PedidoListComponent} from './pedido-list.component';
import {SharedModule} from "../../../../shared/shared.module";
import { BadgeModule } from 'primeng/badge';

@NgModule({
  declarations: [
    PedidoListComponent,

  ],
  exports: [
    PedidoListComponent
  ],
  imports: [
    CommonModule,
    PedidoListRoutingModule,
    SharedModule,
    BadgeModule
  ]
})
export class PedidoListModule {
}
