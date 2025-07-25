export interface PizzaPedidoViewModel {
  pedidoId: number;
  nomePizza: string;
  nomeCliente: string;
  tamanho: string;
  qtdFatias: number;
  observacao?: string;
  statusPratoId: number;
  dataHora: Date;
}
