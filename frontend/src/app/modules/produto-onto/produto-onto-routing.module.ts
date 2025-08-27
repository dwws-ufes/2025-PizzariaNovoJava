import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProdutoOntoComponent} from "./produto-onto.component";

const routes: Routes = [
  {path: '', component: ProdutoOntoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProdutoOntoRoutingModule {
}
