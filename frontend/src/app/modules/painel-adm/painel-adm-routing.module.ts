import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PainelAdmComponent} from "./painel-adm/painel-adm.component";

const routes: Routes = [
  {path: '', component: PainelAdmComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelAdmRoutingModule {
}
