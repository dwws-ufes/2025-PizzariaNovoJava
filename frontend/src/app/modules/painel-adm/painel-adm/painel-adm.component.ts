import {Component, OnInit} from '@angular/core';
import {MenuEnum} from "../../../shared/util/enum/menu-enum";
import {Router} from "@angular/router";

@Component({
  selector: 'app-painel-adm',
  templateUrl: './painel-adm.component.html',
  styleUrls: ['./painel-adm.component.scss']
})
export class PainelAdmComponent implements OnInit {

  public cardComponent: string;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  public mudarRota(idTipoAtendimento: number): void {
    this.cardComponent = MenuEnum.setClasse(idTipoAtendimento).titulo;
    this.router.navigate(['/' + this.cardComponent]);
  }
}
