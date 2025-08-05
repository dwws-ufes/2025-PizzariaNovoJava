import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {PizzaPedidoViewModel} from '../../../model/list/pizza-pedido-view.model';
import {SobremesaPedidoViewModel} from '../../../model/list/sobremesa-pedido-view.model';
import {MensagensConfirmacao} from '../../../shared/util/msg-confirmacao-dialog-util';
import {StatusPedido} from '../../../shared/util/enum/status-pedido-enum';
import {NotificacaoService} from "../../../shared/service/notificacao.service";
import {finalize} from "rxjs";
import {MensagensCozinhaUtil} from "../util/mensagens-cozinha-util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";

@Component({
  selector: 'app-cozinha',
  templateUrl: './cozinha.component.html',
  styleUrls: ['./cozinha.component.scss']
})
export class CozinhaComponent implements OnInit {
  pizzasList: PizzaPedidoViewModel[] = [];
  sobremesasList: SobremesaPedidoViewModel[] = [];
  StatusPedido = StatusPedido;
  @BlockUI() blockUI: NgBlockUI;

  // Variáveis para o modal
  displayModalStatus = false;
  pedidoSelecionado: any = null;
  tipoPratoSelecionado: string = '';
  statusSelecionado: number | null = null;
  opcoesStatus: StatusPedido[] = StatusPedido.values;

  constructor(
    private cozinhaService: NotificacaoService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.carregarPedidosPizza();
    this.carregarPedidosSobremesas();
  }

  getStatusDescricao(statusId: number): string {
    return StatusPedido.obterPorIndex(statusId).descricao;
  }

  carregarPedidosPizza(): void {
    this.blockUI.start();
    this.cozinhaService.findAllCozinha()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.pizzasList = result;
        },
        error: () => {
          this.message.showError(MensagensCozinhaUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      });
  }

  carregarPedidosSobremesas(): void {
    this.blockUI.start();
    this.cozinhaService.findAllSobremesa()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.sobremesasList = result || [];
        },
        error: () => {
          this.message.showError(MensagensCozinhaUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      });
  }

  abrirModalStatus(item: any, tipoPrato: string): void {
    this.pedidoSelecionado = item;
    this.tipoPratoSelecionado = tipoPrato;
    this.statusSelecionado = item.statusPratoId;
    this.displayModalStatus = true;
  }

  fecharModalStatus(): void {
    this.displayModalStatus = false;
    this.pedidoSelecionado = null;
    this.tipoPratoSelecionado = '';
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

  atualizarStatus(item: PizzaPedidoViewModel | null | SobremesaPedidoViewModel, novoStatus: number | null): void {
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
