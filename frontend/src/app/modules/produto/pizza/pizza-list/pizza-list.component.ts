import {Component, OnInit, ViewChild} from '@angular/core';
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ColumnUtil} from "../../../../shared/util/column-util";
import {Page} from "../../../../shared/util/page-util";
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";
import {PizzaListModel} from "../../../../model/list/pizza-list.model";
import {PizzaFormComponent} from "../pizza-form/pizza-form.component";
import {TipoTituloModalProdutoUtil, TituloModalProdutoUtil} from "../../util/titulo-modal-produto.util";

@Component({
  selector: 'app-pizza-list',
  templateUrl: './pizza-list.component.html',
  styleUrls: ['./pizza-list.component.scss']
})
export class PizzaListComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.PIZZA_COLUMNS;
  pizzasList: Page<PizzaListModel[]> | any = new Page<PizzaListModel[]>();

  titleDialog: string;

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(PizzaFormComponent) pizzaFormComponent: PizzaFormComponent;

  display = false;
  entidade = EntidadeUtil.PIZZA;
  titulosModal = TituloModalProdutoUtil.criarTitulos(this.entidade.descricao);

  constructor(
    // private pizzaService: PizzaService,
    // private produtoService: ProdutoService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.findAllPizzas();
  }

  findAllPizzas(): void {
    // this.blockUI.start();
    // this.pizzaService.findAll()
    //   .pipe(finalize(() => this.blockUI.stop()))
    //   .subscribe({
    //     next: (result) => {
    //       this.resultRequestList(result);
    //     },
    //     error: () => {
    //       this.message.showInfo(MensagensProntasUtil.SUB_MESSAGE_ERROR, MensagensProntasUtil.ERROR);
    //     }
    //   });
  }

  registerPizza(): void {
    this.titleDialog = TituloModalProdutoUtil.setTitulo(this.titulosModal, TipoTituloModalProdutoUtil.NEW).header;
    this.pizzaFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.pizzaFormComponent.saveForm();
    this.onClose();
  }

  editPizza(id: number): void {
    this.titleDialog = TituloModalProdutoUtil.setTitulo(this.titulosModal, TipoTituloModalProdutoUtil.EDIT).header;
    this.display = true;
    // this.pizzaFormComponent.editPizza(id);
  }

  deactivatePizza(id: number): void {
    // this.produtoService.delete(id).subscribe(() => this.findAllPizzas());
  }

  confirmAction(pizza: any): void {
    this.message.confirmarDialog(
      pizza.id,
      () => this.deactivatePizza(pizza.id),
      this.entidade,
      pizza.nome
    );
  }

  onClose(): void {
    this.updateListAfterCreate();
    this.pizzaFormComponent.formGroup.reset();
    this.display = false;
  }

  private listAllPizzas(): void {
    // this.pizzaService.findAll().subscribe((resp) => {
    //   this.resultRequestList(resp);
    // });
  }

  private resultRequestList(result: Page<PizzaListModel[]>): void {
    result.content ? this.pizzasList = result : this.pizzasList = [];
  }

  private updateListAfterCreate(): void {
    if (this.pizzaFormComponent.list) {
      this.listAllPizzas();
    }
  }

}
