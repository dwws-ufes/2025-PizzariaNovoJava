import {Component, OnInit, ViewChild} from '@angular/core';
import {ProdutoColumnUtil} from "../../util/produto-column.util";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {BebidaFormComponent} from "../bebida-form/bebida-form.component";
import {ColumnUtil} from "../../../../shared/util/column-util";
import {BebidaListModel} from "../../../../model/list/bebida-list.model";
import {Page} from "../../../../shared/util/page-util";
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {TipoTituloModalProdutoUtil, TituloModalProdutoUtil} from "../../util/titulo-modal-produto.util";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";
import {BebidaService} from "../../../../shared/service/bebida.service";
import {MensagensProntasUtil} from "../../../../shared/util/messages/MensagensProntas.util";
import {finalize} from "rxjs";

@Component({
  selector: 'app-bebida-list',
  templateUrl: './bebida-list.component.html',
  styleUrls: ['./bebida-list.component.scss']
})
export class BebidaListComponent implements OnInit {

  columns: ColumnUtil[] = ProdutoColumnUtil.BEBIDA_COLUMNS;
  bebidasList: Page<BebidaListModel[]> | any = new Page<BebidaListModel[]>();

  titleDialog: string;

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild(BebidaFormComponent) bebidaFormComponent: BebidaFormComponent;

  display = false;
  entidade = EntidadeUtil.BEBIDA;
  titulosModal = TituloModalProdutoUtil.criarTitulos(this.entidade.descricao);


  constructor(
    private bebidaService: BebidaService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.findAllBebidas();
  }

  findAllBebidas(): void {
    this.blockUI.start();
    this.bebidaService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensProntasUtil.SUB_MESSAGE_ERROR, MensagensProntasUtil.ERROR);
        }
      });
  }

  registerBebida(): void {
    this.titleDialog = TituloModalProdutoUtil.setTitulo(this.titulosModal, TipoTituloModalProdutoUtil.NEW).header;
    this.bebidaFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.bebidaFormComponent.saveForm();
    this.onClose();
  }

  editBebida(id: number): void {
    this.titleDialog = TituloModalProdutoUtil.setTitulo(this.titulosModal, TipoTituloModalProdutoUtil.EDIT).header;
    this.display = true;
    this.bebidaFormComponent.editBebida(id);
  }

  deactivateBebida(id: number): void {
    this.bebidaService.delete(id).subscribe(() => this.findAllBebidas());
  }

  confirmAction(bebida: any): void {
    this.message.confirmarDialog(
      bebida.id,
      () => this.deactivateBebida(bebida.id),
      this.entidade,
      bebida.nome
    );
  }

  onClose(): void {
    this.updateList();
    this.bebidaFormComponent.formGroup.reset();
  }

  listAllBebidas(): void {
    this.bebidaService.findAll().subscribe((resp) => {
      this.resultRequestList(resp);
    });
  }

  private resultRequestList(result: Page<BebidaListModel[]>): void {
    result.content ? this.bebidasList = result : this.bebidasList = [];
  }

  private updateList(): void {
    this.listAllBebidas();
    this.display = false;
  }
}
