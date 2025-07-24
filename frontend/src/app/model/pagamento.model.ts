export class NotificacaoModel {
  id?: number;
  pedidoId: number;
  clienteId: number;
  valorTotal: number;
  desconto: number;
  valorFinal: number;
  formaPagamento: number;
  dataHora: Date;
}
