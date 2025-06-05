import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  // {path: '', loadChildren: () => import('./modules/painel-adm/painel-adm.module').then(m => m.PainelAdmModule)},
  {path: 'usuarios', loadChildren: () => import('./modules/usuario/usuario.module').then(m => m.UsuarioModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
