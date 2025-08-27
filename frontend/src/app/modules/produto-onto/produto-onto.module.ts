import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoOntoRoutingModule } from './produto-onto-routing.module';
import {SharedModule} from "../../shared/shared.module";
import { ProdutoOntoComponent } from './produto-onto.component';


@NgModule({
  declarations: [
    ProdutoOntoComponent
  ],
  imports: [
    CommonModule,
    ProdutoOntoRoutingModule,
    SharedModule
  ]
})
export class ProdutoOntoModule { }
