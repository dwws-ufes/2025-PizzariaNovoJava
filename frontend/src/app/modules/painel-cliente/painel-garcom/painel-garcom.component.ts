import {Component, OnInit} from '@angular/core';
import {MessageService} from 'primeng/api';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ClienteService} from "../../../shared/service/cliente.service";
import {PizzaService} from"../../../shared/service/pizza.service";
import {BebidaService} from"../../../shared/service/bebida.service";
import {SobremesaService} from"../../../shared/service/sobremesa.service"

import {PizzaListModel} from "../../../model/list/pizza-list.model";
import {BebidaListModel} from "../../../model/list/bebida-list.model";
import {SobremesaListModel} from "../../../model/list/sobremesa-list.model";
import {ClienteListModel} from "../../../model/list/cliente-list.model";
import {Page} from "../../../shared/util/page-util";
import {StatusPedido} from "../../../shared/util/enum/status-pedido-enum"
import {ItemPedidoModel} from "../../../model/item-pedido.model";
import {PedidoModel} from "../../../model/pedido.model";
import {UsuarioService} from "../../../shared/service/usuario.service";
import {UsuarioModel} from "../../../model/usuario.model";
import {PedidoService} from "../../../shared/service/pedido.service";

@Component({
  selector: 'app-painel-garcom',
  templateUrl: './painel-garcom.component.html',
  styleUrls: ['./painel-garcom.component.scss'],
  providers: [MessageService]
})
export class PainelGarcomComponent implements OnInit {
  // Listas de produtos
  pizzas: Page<PizzaListModel[]> | any = new Page<PizzaListModel[]>();
  bebidas: Page<BebidaListModel[]> | any = new Page<BebidaListModel[]>();
  sobremesas: Page<SobremesaListModel[]> | any = new Page<SobremesaListModel[]>();

  // Pedido em andamento
  pedidoForm: FormGroup;
  itensPedido: ItemPedidoModel[] = [];
  clientes: Page<ClienteListModel[]> | any = new Page<ClienteListModel[]>();
  atendente: UsuarioModel;
  displayModalPedido = false;
  totalPedido = 0;
  status = StatusPedido.obterPorIndex(1);

  // Status (similar ao que você já tem)
  StatusPedido = {
    PENDENTE: {index: 0, descricao: 'Pendente'},
    // ... outros status
  };

  constructor(
    private pizzaService: PizzaService,
    private bebidaService: BebidaService,
    private sobremesaService: SobremesaService,
    private clienteService: ClienteService,
    private usuarioService: UsuarioService,
    private pedidoService: PedidoService,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.pedidoForm = this.fb.group({
      clienteId: [null, Validators.required],
      observacoes: ['']
    });
  }

  ngOnInit(): void {
    this.carregarProdutos();
    this.carregarClientes();
    this.carregarDadosAtendente();
  }

  carregarProdutos(): void {
    this.pizzaService.findAll().subscribe(pizzas => this.pizzas = pizzas.content);
    this.bebidaService.findAll().subscribe(bebidas => this.bebidas = bebidas.content);
    this.sobremesaService.findAll().subscribe(sobremesas => this.sobremesas = sobremesas.content);
  }

  carregarClientes(): void {
    this.clienteService.findAll().subscribe(clientes => this.clientes = clientes.content);
  }

  adicionarItem(item: any, tipo: string): void {
    const itemPedido: ItemPedidoModel = {
      produtoId: item.id,
      valorItem: item.precoVenda,
      quantidade: 1,
    };

    this.itensPedido.push(itemPedido);
    this.calcularTotal();
    this.messageService.add({
      severity: 'success',
      summary: 'Item adicionado',
      detail: `${item.nome} foi adicionado ao pedido`
    });
  }

  removerItem(index: number): void {
    this.itensPedido.splice(index, 1);
    this.calcularTotal();
  }

  calcularTotal(): void {
    this.totalPedido = this.itensPedido.reduce((total, item) => total + (item.valorItem * item.quantidade), 0);
  }

  abrirModalPedido(): void {
    if (this.itensPedido.length === 0) {
      this.messageService.add({
        severity: 'warn',
        summary: 'Atenção',
        detail: 'Adicione itens ao pedido antes de finalizar'
      });
      return;
    }
    this.displayModalPedido = true;
  }

  finalizarPedido(): void {
    if (this.pedidoForm.invalid) {
      this.messageService.add({severity: 'error', summary: 'Erro', detail: 'Selecione um cliente'});
      return;
    }

    const pedido: PedidoModel = {
      clienteId: this.pedidoForm.value.clienteId,
      observacoes: this.pedidoForm.value.observacoes,
      status: this.status.index,
      atendenteId: this.atendente.id,
      valorTotal: this.totalPedido,
      quatidateItens: this.itensPedido.length,
      itens: this.itensPedido
    };

    console.log(pedido);

    this.pedidoService.criarPedido(pedido).subscribe({
      next: () => {
        this.messageService.add({severity: 'success', summary: 'Sucesso', detail: 'Pedido criado com sucesso'});
        this.limparPedido();
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      },
      error: (err) => {
        this.messageService.add({severity: 'error', summary: 'Erro', detail: 'Falha ao criar pedido'});
      }
    });
  }

  limparPedido(): void {
    this.itensPedido = [];
    this.totalPedido = 0;
    this.pedidoForm.reset();
    this.displayModalPedido = false;
  }
  carregarDadosAtendente(): void{
    const login = localStorage.getItem("userName");
    this.usuarioService.findByLogin(login).subscribe(usuario => this.atendente = usuario);
  }
}
