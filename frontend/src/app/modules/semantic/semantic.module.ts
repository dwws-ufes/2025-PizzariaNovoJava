import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SemanticRoutingModule} from './semantic-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {SemanticComponent} from "./semantic.component";


@NgModule({
  declarations: [
    SemanticComponent
  ],
  imports: [
    CommonModule,
    SemanticRoutingModule,
    SharedModule,
  ]
})
export class SemanticModule {
}
