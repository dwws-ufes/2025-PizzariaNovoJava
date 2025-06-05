import {Component, Input} from '@angular/core';
import {OpcaoMenuModel} from 'src/app/shared/models/opcao-menu.model';
import {SidemenuModel} from 'src/app/shared/models/sidemenu.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent {
  @Input() public configuracaoMenuLateral?: SidemenuModel;

  @Input() public opcoesControleAverco: OpcaoMenuModel[] = [
    new OpcaoMenuModel('pi pi-home', 'Painel Geral',
      () => this.router.navigateByUrl('/')),
    new OpcaoMenuModel('bi bi-person-vcard', 'Usuários',
      () => this.router.navigateByUrl('/usuarios')),
    new OpcaoMenuModel('bi bi-grid', 'Produtos',
      () => this.router.navigateByUrl('/produtos')),
    new OpcaoMenuModel('bi-people-fill', 'Clientes',
      () => this.router.navigateByUrl('/clientes')),
    new OpcaoMenuModel('bi bi-cart', 'Caixa',
      () => this.router.navigateByUrl('/caixa')),
    new OpcaoMenuModel('icon bi bi-fire', 'Cozinha',
      () => this.router.navigateByUrl('/cozinha')),
    new OpcaoMenuModel('bi bi-shop-window', 'Painel',
      () => this.router.navigateByUrl('/painel')),
  ];

  constructor(
    private router: Router
  ) {
  }

  public estaVisivel(): boolean {
    if (this.configuracaoMenuLateral) {
      return this.configuracaoMenuLateral.visivel;
    }
    return false;
  }
}
