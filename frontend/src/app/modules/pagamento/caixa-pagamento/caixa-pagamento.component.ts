import {Component, OnInit} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/column-util";
import {CaixaPagamentoConsumoTable} from "../util/caixa-pagamento-consumo-table";
import {ClienteModel} from "../../../model/cliente.model";
import {SelectItem} from "primeng/api";
import {FormBuilder, FormGroup} from "@angular/forms";
import {PagamentoCaixaService} from "../../../shared/service/pagamento-caixa.service";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensConfirmacao} from "../../../shared/util/msg-confirmacao-dialog-util";
import {FormaPagamentoEnum} from "../../../shared/util/enum/forma-pagamento-enum";
import {ItemPedidoListModel} from "../../../model/list/item-pedido-list.model";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {PedidoService} from "../../../shared/service/pedido.service";

@Component({
  selector: 'app-caixa-pagamento',
  templateUrl: './caixa-pagamento.component.html',
  styleUrls: ['./caixa-pagamento.component.scss']
})
export class CaixaPagamentoComponent implements OnInit {

  columns: ColumnUtil[] = CaixaPagamentoConsumoTable.CONSUME_TABLE;
  cliente: ClienteModel;
  clientes: SelectItem[];
  itensPedido: ItemPedidoListModel[] = [];
  valorTotal: number = 0;
  valorFinal: number = 0;

  formasPagamento: SelectItem[];
  formGroup: FormGroup;

  @BlockUI() blockUI: NgBlockUI;

  constructor(private builder: FormBuilder,
              private clienteService: ClienteService,
              private pagamentoService: PagamentoCaixaService,
              private pedidoService: PedidoService,
              private message: MensagensConfirmacao,
  ) {
  }

  ngOnInit(): void {
    this.newForm();
    this.initDropdown();
    this.carregarClientes();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      pedidoId: [null],
      desconto: [null],
      valorTotal: [null],
      formaPagamento: [null],
      dataHora: [null],
    })
  }

  initDropdown(): void {
    this.formasPagamento = FormaPagamentoEnum.values.map(item => ({
      label: item.titulo,
      value: item.index
    }));
  }

  carregarClientes(): void {
    this.clienteService.buscarPorPagamentoPendente().subscribe(clientes => {
      console.log(clientes)
      this.clientes = clientes
    });
  }

  selecionarCliente(idCliente: number): void {
    this.carregarPedidoDoCliente(idCliente);
  }

  carregarPedidoDoCliente(clienteId: number): void {
    // Chame seu serviço para buscar o pedido do cliente
    this.pedidoService.buscarItensPedidoPorCliente(clienteId).subscribe(
      pedido => {
        this.itensPedido = pedido;
        this.formGroup.patchValue({
          pedidoId: pedido[0].id,
        });
        this.calcularValorTotal();
      }
    );
  }

  calcularValorTotal(): void {
    this.valorTotal = this.itensPedido.reduce(
      (total: number, item: ItemPedidoListModel) => total + (item.valorItem * item.quantidade), 0);
    this.calcularValorFinal();
  }

  calcularValorFinal(): void {
    const desconto = this.formGroup.get('desconto')?.value || 0;
    this.valorFinal = this.valorTotal - desconto;
    this.formGroup.patchValue({valorTotal: this.valorFinal});
  }

  confirmarPagamento(): void {
    if (this.formGroup.valid) {
      const pagamento = this.formGroup.value;
      pagamento.valorTotal = this.valorFinal;

      this.pagamentoService.realizarPagamento(pagamento).subscribe(
        () => {
          this.message.showSuccess('Pagamento realizado com sucesso!')
          this.finalizarPedido();
        },
        error => this.message.showError('Erro ao realizar pagamento', error)
      );
    }
  }

  finalizarPedido(): void {
    this.formGroup.reset();
    this.itensPedido = [];
    this.carregarClientes();
    this.valorTotal = 0;
    this.valorFinal = 0;
  }

}
