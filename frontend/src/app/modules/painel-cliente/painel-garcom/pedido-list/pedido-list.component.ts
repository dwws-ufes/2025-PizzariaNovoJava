import {Component, OnInit} from '@angular/core';
import {PedidoPreparodoModel} from "../../../../model/pedido-preparodo.model";
import {UsuarioService} from "../../../../shared/service/usuario.service";
import {PedidoService} from "../../../../shared/service/pedido.service";
import {MessageService} from "primeng/api";
import {UsuarioModel} from "../../../../model/usuario.model";
import {UptadeStatusPedidoModel} from "../../../../model/uptade-status-pedido.model";
import {StatusPedido} from "../../../../shared/util/enum/status-pedido-enum";


@Component({
  selector: 'app-pedido-liist',
  templateUrl: './pedido-list.component.html',
  styleUrls: ['./pedido-list.component.scss']
})
export class PedidoListComponent implements OnInit{
  statusFiltro: string = '';
  busca: string = '';
  pedidoSelecionado?: PedidoPreparodoModel;
  mostrarModalDetalhes: boolean = false;
  atendente: UsuarioModel;
  pedidos: PedidoPreparodoModel[] = [];
  StatusPedido = StatusPedido;

  constructor(
    private usuarioService: UsuarioService,
    private pedidoService: PedidoService,
    private messageService: MessageService,
  ) {}

  ngOnInit(): void {
    this.carregarDadosAtendente();
  }

  carregarDadosAtendente(): void {
    const login = localStorage.getItem("userName");
    if (!login) {
      console.error("Login não encontrado no localStorage");
      return;
    }

    this.usuarioService.findByLogin(login).subscribe({
      next: (usuario) => {
        this.atendente = usuario;
        this.carregarPedidos(); // Chama somente depois de definir o atendente
      },
      error: (err) => {
        console.error("Erro ao buscar atendente:", err);
      }
    });
  }
  carregarPedidos(): void {
    this.pedidoService.findByIdAtendente(this.atendente.id).subscribe(pedidos => this.pedidos = pedidos);
  }
  getStatusText(): string {
    return 'Em Preparo';
  }

  getStatusClass(): string {
      return 'bg-yellow-100 text-yellow-800';
  }

  getPedidosFiltrados(): PedidoPreparodoModel[] {
    return this.pedidos.filter(p => {
      const texto = `${p.clienteNome} ${p.atendenteNome}`.toLowerCase();
      const buscaOk = texto.includes(this.busca.toLowerCase());
      return buscaOk;
    });
  }

  verDetalhes(pedido: PedidoPreparodoModel): void {
    this.pedidoSelecionado = pedido;
    this.mostrarModalDetalhes = true;
  }

  cancelarPedido(pedido: PedidoPreparodoModel): void {
    if(pedido.id){
      const statusCancelado = StatusPedido.obterPorIndex(4);
      const cancelarPedido: UptadeStatusPedidoModel = {
        idPedido: pedido.id,
        statusPedido: statusCancelado.index
      };
      this.alteraStatusPedido(cancelarPedido);
    }
  }

  entregarPedido(pedido: PedidoPreparodoModel): void{
    if(pedido.id){
      const statusEntrgue = StatusPedido.obterPorIndex(3);
      const entreguarPedido: UptadeStatusPedidoModel = {
        idPedido: pedido.id,
        statusPedido: statusEntrgue.index
      };
      this.alteraStatusPedido(entreguarPedido);
    }
  }

  alteraStatusPedido(status: UptadeStatusPedidoModel): void{
    this.pedidoService.alteraStatusPedido(status).subscribe({
      next: () => {
        this.carregarPedidos();
      },
      error: (err) => {
        console.error("Erro ao alterar status:", err);
      }
    });

  }


}
