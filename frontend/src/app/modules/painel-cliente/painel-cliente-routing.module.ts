import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PainelGarcomComponent} from "./painel-garcom/painel-garcom.component";

const routes: Routes = [
  {path: '', component: PainelGarcomComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelClienteRoutingModule {
}
