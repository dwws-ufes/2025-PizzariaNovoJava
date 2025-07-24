import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PainelClienteRoutingModule } from './painel-cliente-routing.module';
import { PainelClienteComponent } from './painel-cliente/painel-cliente.component';
import { PizzaPainelComponent } from './pizza-painel/pizza-painel.component';
import { BebidaPainelComponent } from './bebida-painel/bebida-painel.component';
import { SobremesaPainelComponent } from './sobremesa-painel/sobremesa-painel.component';


@NgModule({
  declarations: [
    PainelClienteComponent,
    PizzaPainelComponent,
    BebidaPainelComponent,
    SobremesaPainelComponent
  ],
  imports: [
    CommonModule,
    PainelClienteRoutingModule
  ]
})
export class PainelClienteModule { }
