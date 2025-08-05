export interface PizzaPedidoViewModel {
  pedidoId: number;
  notificacaoCozinhaId: number;
  nomePizza: string;
  nomeCliente: string;
  tamanho: string;
  qtdFatias: number;
  observacao?: string;
  statusPratoId: number;
  quantidade: number;
  dataHora: Date;
}
