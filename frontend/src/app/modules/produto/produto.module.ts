import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { PizzaComponent } from './pizza/pizza.component';
import { BebidaComponent } from './bebida/bebida.component';
import { SobremesaComponent } from './sobremesa/sobremesa.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';


@NgModule({
  declarations: [
    PizzaComponent,
    BebidaComponent,
    SobremesaComponent,
    ProdutoListComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule
  ]
})
export class ProdutoModule { }
