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

@Component({
  selector: 'app-caixa-pagamento',
  templateUrl: './caixa-pagamento.component.html',
  styleUrls: ['./caixa-pagamento.component.scss']
})
export class CaixaPagamentoComponent implements OnInit {

  columns: ColumnUtil[] = CaixaPagamentoConsumoTable.CONSUME_TABLE;
  cliente: ClienteModel;
  itensPedido: ItemPedidoListModel[] = [];
  valorTotal: number = 0;
  valorFinal: number = 0;

  formasPagamento: SelectItem[];
  formGroup: FormGroup;

  @BlockUI() blockUI: NgBlockUI;

  constructor(private builder: FormBuilder,
              private clienteService: ClienteService,
              private pagamentoService: PagamentoCaixaService,
              private message: MensagensConfirmacao,
  ) {
  }

  ngOnInit(): void {
    this.newForm();
    this.initDropdown();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      idPedido: [null],
      desconto: [null],
      valorFinal: [null],
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

  buscarCliente(event: any): void {
    const termo = event.target.value;
    if (termo.length > 2) {
      this.clienteService.buscarPorTermo(termo).subscribe(
        clientes => {
          // Lógica para selecionar/confirmar o cliente
          if (clientes.length === 1) {
            this.selecionarCliente(clientes[0]);
          }
        }
      );
    }
  }

  selecionarCliente(cliente: ClienteModel): void {
    this.cliente = cliente;
    if (this.cliente.id != null) {
      this.carregarPedidoDoCliente(this.cliente.id);
    }
  }

  carregarPedidoDoCliente(clienteId: number): void {
    // Chame seu serviço para buscar o pedido do cliente
    // this.pagamentoService.buscarPedidoPorCliente(clienteId).subscribe(
    //   pedido => {
    //     this.itensPedido = pedido.itens;
    //     this.calcularValorTotal();
    //     this.formGroup.patchValue({
    //       idPedido: pedido.id,
    //       valorFinal: pedido.valorTotal
    //     });
    //   }
    // );
  }

  calcularValorTotal(): void {
    this.valorTotal = this.itensPedido.reduce(
      (total: number, item: ItemPedidoListModel) => total + (item.valorItem * item.quantidade), 0);
    this.calcularValorFinal();
  }

  calcularValorFinal(): void {
    const desconto = this.formGroup.get('desconto')?.value || 0;
    this.valorFinal = this.valorTotal - desconto;
    this.formGroup.patchValue({valorFinal: this.valorFinal});
  }

  confirmarPagamento(): void {
    if (this.formGroup.valid) {
      const pagamento = this.formGroup.value;
      pagamento.dataHora = new Date();

      // this.pagamentoService.realizarPagamento(pagamento).subscribe(
      //   () => this.message.showSuccess('Pagamento realizado com sucesso!'),
      //   error => this.message.showError('Erro ao realizar pagamento')
      // );
    }
  }

}
