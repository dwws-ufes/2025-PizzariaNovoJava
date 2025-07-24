export class NotificacaoModel {
  id?: number;
  pedidoId: number;
  statusPedidoId: number;
  dataHora: Date;
  ativo: boolean = true;
}
