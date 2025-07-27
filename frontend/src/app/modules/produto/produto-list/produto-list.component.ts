import {Component, ViewChild} from '@angular/core';
import {PizzaListComponent} from "../pizza/pizza-list/pizza-list.component";
import {BebidaListComponent} from "../bebida/bebida-list/bebida-list.component";
import {SobremesaListComponent} from "../sobremesa/sobremesa-list/sobremesa-list.component";

@Component({
  selector: 'app-produto-list',
  templateUrl: './produto-list.component.html',
  styleUrls: ['./produto-list.component.scss']
})
export class ProdutoListComponent {

  @ViewChild('pizzaList') pizzaList!: PizzaListComponent;
  @ViewChild('bebidaList') bebidaList!: BebidaListComponent;
  @ViewChild('sobremesaList') sobremesaList!: SobremesaListComponent;

  onTabChange(event: any) {
    const index = event.index;
    switch (index) {
      case 0:
        this.pizzaList?.listAllPizzas();
        break;
      case 1:
        this.bebidaList?.listAllBebidas();
        break;
      case 2:
        this.sobremesaList?.listAllSobremesas();
        break;
    }
  }
}
