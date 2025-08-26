import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', loadChildren: () => import('./modules/painel-adm/painel-adm.module').then(m => m.PainelAdmModule)},
  {path: 'usuarios', loadChildren: () => import('./modules/usuario/usuario.module').then(m => m.UsuarioModule)},
  {path: 'produtos', loadChildren: () => import('./modules/produto/produto.module').then(m => m.ProdutoModule)},
  {path: 'clientes', loadChildren: () => import('./modules/cliente/cliente.module').then(m => m.ClienteModule)},
  {path: 'cozinha', loadChildren: () => import('./modules/cozinha/cozinha.module').then(m => m.CozinhaModule)},
  {path: 'bar', loadChildren: () => import('./modules/bar/bar.module').then(m => m.BarModule)},
  {path: 'login', loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule)},
  {path: 'pedidos', loadChildren: () => import('./modules/pedido/pedido.module').then(m => m.PedidoModule)},
  {path: 'painel', loadChildren: () => import('./modules/painel-cliente/painel-cliente.module').then(m => m.PainelClienteModule)},
  {path: 'semantic', loadChildren: () => import('./modules/semantic/semantic.module').then(m => m.SemanticModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
