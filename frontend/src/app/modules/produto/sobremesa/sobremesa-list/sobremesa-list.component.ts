import {Component, OnInit, ViewChild} from '@angular/core';
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {SobremesaFormComponent} from "../sobremesa-form/sobremesa-form.component";
import {finalize} from "rxjs";
import {ColumnUtil} from "../../../../shared/util/column-util";
import {Page} from "../../../../shared/util/page-util";
import {SobremesaListModel} from "../../../../model/list/sobremesa-list.model";
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {MensagensProntasUtil} from "../../../../shared/util/messages/MensagensProntas.util";
import {TipoTituloModalProdutoUtil, TituloModalProdutoUtil} from "../../util/titulo-modal-produto.util";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";

@Component({
  selector: 'app-sobremesa-list',
  templateUrl: './sobremesa-list.component.html',
  styleUrls: ['./sobremesa-list.component.scss']
})
export class SobremesaListComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.SOBREMESA_COLUMNS;
  sobremesasList: Page<SobremesaListModel[]> | any = new Page<SobremesaListModel[]>();

  titleDialog: string;
  entidade = EntidadeUtil.SOBREMESA;
  titulosModal = TituloModalProdutoUtil.criarTitulos(this.entidade.descricao);

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(SobremesaFormComponent) sobremesaFormComponent: SobremesaFormComponent;

  display = false;
  displayEntry = false;

  constructor(
    // private sobremesaService: SobremesaService,
    // private produtoService: ProdutoService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.findAllSobremesas();
  }

  findAllSobremesas(): void {
    // this.blockUI.start();
    // this.sobremesaService.findAll()
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

  registerSobremesa(): void {
    this.titleDialog = TituloModalProdutoUtil.setTitulo(this.titulosModal, TipoTituloModalProdutoUtil.NEW).header;
    this.sobremesaFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.sobremesaFormComponent.saveForm();
    this.onClose();
  }

  editSobremesa(id: number): void {
    this.titleDialog = TituloModalProdutoUtil.setTitulo(this.titulosModal, TipoTituloModalProdutoUtil.EDIT).header;
    this.display = true;
    // this.sobremesaFormComponent.editSobremesa(id);
  }

  deactivateSobremesa(id: number): void {
    // this.produtoService.delete(id).subscribe(() => this.findAllSobremesas());
  }

  confirmAction(sobremesa: any): void {
    this.message.confirmarDialog(
      sobremesa.id,
      () => this.deactivateSobremesa(sobremesa.id),
      this.entidade,
      sobremesa.nome
    );
  }

  onClose(): void {
    this.updateListAfterCreate();
    this.sobremesaFormComponent.formGroup.reset();
    this.display = false;
  }

  private listAllSobremesas(): void {
    // this.sobremesaService.findAll().subscribe((resp) => {
    //   this.resultRequestList(resp);
    // });
  }

  private resultRequestList(result: Page<SobremesaListModel[]>): void {
    result.content ? this.sobremesasList = result : this.sobremesasList = [];
  }

  private updateListAfterCreate(): void {
    if (this.sobremesaFormComponent.list) {
      this.listAllSobremesas();
    }
  }
}

