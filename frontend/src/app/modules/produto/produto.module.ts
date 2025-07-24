import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProdutoRoutingModule} from './produto-routing.module';
import {PizzaFormComponent} from './pizza/pizza-form/pizza-form.component';
import {BebidaFormComponent} from './bebida/bebida-form/bebida-form.component';
import {SobremesaFormComponent} from './sobremesa/sobremesa-form/sobremesa-form.component';
import {ProdutoListComponent} from './produto-list/produto-list.component';
import {BebidaListComponent} from './bebida/bebida-list/bebida-list.component';
import {PizzaListComponent} from './pizza/pizza-list/pizza-list.component';
import {SobremesaListComponent} from './sobremesa/sobremesa-list/sobremesa-list.component';
import {SharedModule} from "../../shared/shared.module";
import {SelectButtonModule} from "primeng/selectbutton";


@NgModule({
  declarations: [
    PizzaFormComponent,
    BebidaFormComponent,
    SobremesaFormComponent,
    ProdutoListComponent,
    BebidaListComponent,
    PizzaListComponent,
    SobremesaListComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule,
    SharedModule,
    SelectButtonModule
  ]
})
export class ProdutoModule {
}
