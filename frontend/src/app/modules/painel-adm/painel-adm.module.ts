import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PainelAdmRoutingModule} from './painel-adm-routing.module';
import {PainelAdmComponent} from './painel-adm/painel-adm.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    PainelAdmComponent
  ],
  imports: [
    CommonModule,
    PainelAdmRoutingModule,
    SharedModule,
    SharedModule
  ]
})
export class PainelAdmModule {
}
