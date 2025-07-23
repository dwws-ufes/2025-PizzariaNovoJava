import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PainelClienteRoutingModule } from './painel-cliente-routing.module';
import { PainelClienteComponent } from './painel-cliente/painel-cliente.component';


@NgModule({
  declarations: [
    PainelClienteComponent
  ],
  imports: [
    CommonModule,
    PainelClienteRoutingModule
  ]
})
export class PainelClienteModule { }
