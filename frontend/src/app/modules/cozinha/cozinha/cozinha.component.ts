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
import {BebidaPedidoViewModel} from "../../../model/list/bebida-pedido-view.model";
import {UptadeStatusPedidoModel} from "../../../model/uptade-status-pedido.model";
import {PedidoService} from "../../../shared/service/pedido.service";

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
    private message: MensagensConfirmacao,
    private pedidoService: PedidoService,
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
  abrirModalStatus(item: PizzaPedidoViewModel): void {
    this.pedidoSelecionado = item;
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

  atualizarStatus(item: BebidaPedidoViewModel | null | SobremesaPedidoViewModel, novoStatus: number | null): void{
    if (!item || novoStatus === null) {
      console.warn("Item ou status inválido");
      return;
    }
    const status: UptadeStatusPedidoModel = {
      idPedido: item.pedidoId,
      statusPedido: novoStatus
    };
    this.pedidoService.alteraStatusPedido(status).subscribe({
      next: () => {
        this.message.showSuccess('Status do pedido alterado com sucesso');
        this.fecharModalStatus();
        this.carregarPedidosPizza();
      },
      error: (err) => {
        console.error("Erro ao alterar status:", err);
        this.message.showError('Erro ao alterar status do pedido', 'ERRO')
      }
    });
  }

  getStatusText(status: number): string {
    switch (status) {
      case 0: return 'Pendente';
      case 1: return 'Em Preparo';
      case 2: return 'Pronto';
      case 3: return 'Entregue';
      case 4: return 'Cancelado';
      default: return 'Indefinido';
    }
  }

  getStatusClass(status: number): string {
    switch (status) {
      case 0: return 'bg-amber-100 text-amber-800';
      case 1: return 'bg-blue-100 text-blue-800';
      case 2: return 'bg-green-100 text-green-800';
      case 3: return 'bg-gray-200 text-gray-800';
      case 4: return 'bg-red-100 text-red-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  }
}
