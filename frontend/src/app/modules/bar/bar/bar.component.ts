import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {BebidaPedidoViewModel} from "../../../model/list/bebida-pedido-view.model";
import {MensagensConfirmacao} from "../../../shared/util/msg-confirmacao-dialog-util";
import {StatusPedido} from "../../../shared/util/enum/status-pedido-enum";

@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html',
  styleUrls: ['./bar.component.scss']
})
export class BarComponent implements OnInit {
  bebidasList: BebidaPedidoViewModel[] = [];
  StatusPedido = StatusPedido;
  @BlockUI() blockUI: NgBlockUI;

  displayModalStatus = false;
  pedidoSelecionado: BebidaPedidoViewModel | null = null;
  statusSelecionado: number | null = null;
  opcoesStatus: StatusPedido[] = StatusPedido.values;

  constructor(
    // private barService: BarService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.carregarBebidasPedidas();
  }

  getStatusDescricao(statusId: number): string {
    return StatusPedido.obterPorIndex(statusId).descricao;
  }

  carregarBebidasPedidas(): void {
    // this.blockUI.start();
    // this.barService.getBebidasPedidas()
    //   .pipe(finalize(() => this.blockUI.stop()))
    //   .subscribe({
    //     next: (result) => {
    //       this.bebidasList = result || [];
    //     },
    //     error: () => {
    //       this.message.showInfo(MensagensBarUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
    //     }
    //   });
  }

  abrirModalStatus(item: BebidaPedidoViewModel): void {
    this.pedidoSelecionado = item;
    this.statusSelecionado = item.statusPratoId;
    this.displayModalStatus = true;
  }

  fecharModalStatus(): void {
    this.displayModalStatus = false;
    this.pedidoSelecionado = null;
    this.statusSelecionado = null;
  }

  confirmarAlteracaoStatus(): void {
    if (!this.pedidoSelecionado || this.statusSelecionado === null) {
      return;
    }

    this.message.confirmUpdateDish(
      this.pedidoSelecionado.pedidoId,
      () => {
        this.atualizarStatus(this.pedidoSelecionado, this.statusSelecionado);
      },
    );
  }

  atualizarStatus(item: BebidaPedidoViewModel | null, novoStatus: number | null): void {
    //   if (!item || novoStatus === null) {
    //     return;
    //   }
    //
    //   this.blockUI.start();
    //   this.barService.atualizarStatusBebida(item.pedidoId, novoStatus)
    //     .pipe(finalize(() => {
    //       this.blockUI.stop();
    //       this.fecharModalStatus();
    //     }))
    //     .subscribe({
    //       next: () => {
    //         this.message.showSuccess(MensagensBarUtil.UPDATE_SUCCESSFUL_BEBIDA);
    //         this.carregarBebidasPedidas();
    //       },
    //       error: () => {
    //         this.message.showError(MensagensBarUtil.ERROR_UPDATE, MensagensProntasUtil.ERROR);
    //       }
    //     });
  }
}
