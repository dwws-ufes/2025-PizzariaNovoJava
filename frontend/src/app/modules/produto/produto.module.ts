import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { PizzaFormComponent } from './pizza/pizza-form/pizza-form.component';
import { BebidaFormComponent } from './bebida/bebida-form/bebida-form.component';
import { SobremesaComponent } from './sobremesa/sobremesa-form/sobremesa.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';
import { BebidaListComponent } from './bebida/bebida-list/bebida-list.component';
import { PizzaListComponent } from './pizza/pizza-list/pizza-list.component';
import { SobremesaListComponent } from './sobremesa/sobremesa-list/sobremesa-list.component';


@NgModule({
  declarations: [
    PizzaFormComponent,
    BebidaFormComponent,
    SobremesaComponent,
    ProdutoListComponent,
    BebidaListComponent,
    PizzaListComponent,
    SobremesaListComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule
  ]
})
export class ProdutoModule { }
