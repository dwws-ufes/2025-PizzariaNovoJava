import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PainelClienteModule} from "./painel-cliente.module";

const routes: Routes = [
  {path: '', component: PainelClienteModule}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelClienteRoutingModule {
}
