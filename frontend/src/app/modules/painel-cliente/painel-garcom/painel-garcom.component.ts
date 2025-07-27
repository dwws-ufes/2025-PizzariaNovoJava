import {Component, OnInit} from '@angular/core';
import {MessageService} from 'primeng/api';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ClienteService} from "../../../shared/service/cliente.service";

@Component({
  selector: 'app-painel-garcom',
  templateUrl: './painel-garcom.component.html',
  styleUrls: ['./painel-garcom.component.scss'],
  providers: [MessageService]
})
export class PainelGarcomComponent implements OnInit {
  // Listas de produtos
  pizzas: any[] = [];
  bebidas: any[] = [];
  sobremesas: any[] = [];

  // Pedido em andamento
  pedidoForm: FormGroup;
  itensPedido: any[] = [];
  clientes: any[] = [];
  displayModalPedido = false;
  totalPedido = 0;

  // Status (similar ao que você já tem)
  StatusPedido = {
    PENDENTE: {index: 0, descricao: 'Pendente'},
    // ... outros status
  };

  constructor(
    // private produtoService: ProdutoService,
    // private pedidoService: PedidoService,
    private clienteService: ClienteService,
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
  }

  carregarProdutos(): void {
    // this.produtoService.listarPizzas().subscribe(pizzas => this.pizzas = pizzas);
    // this.produtoService.listarBebidas().subscribe(bebidas => this.bebidas = bebidas);
    // this.produtoService.listarSobremesas().subscribe(sobremesas => this.sobremesas = sobremesas);
  }

  carregarClientes(): void {
    // this.clienteService.listarAtivos().subscribe(clientes => this.clientes = clientes);
  }

  adicionarItem(item: any, tipo: string): void {
    const itemPedido = {
      produtoId: item.id,
      nome: item.nome,
      tipo: tipo,
      preco: item.precoVenda,
      quantidade: 1,
      observacao: ''
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
    this.totalPedido = this.itensPedido.reduce((total, item) => total + (item.preco * item.quantidade), 0);
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

    const pedido = {
      clienteId: this.pedidoForm.value.clienteId,
      observacoes: this.pedidoForm.value.observacoes,
      itens: this.itensPedido
    };

    // this.pedidoService.criarPedido(pedido).subscribe({
    //   next: () => {
    //     this.messageService.add({severity: 'success', summary: 'Sucesso', detail: 'Pedido criado com sucesso'});
    //     this.limparPedido();
    //   },
    //   error: (err) => {
    //     this.messageService.add({severity: 'error', summary: 'Erro', detail: 'Falha ao criar pedido'});
    //   }
    // });
  }

  limparPedido(): void {
    this.itensPedido = [];
    this.totalPedido = 0;
    this.pedidoForm.reset();
    this.displayModalPedido = false;
  }
}
