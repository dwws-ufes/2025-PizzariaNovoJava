import {ItemPedidoModel} from "./item-pedido.model";

export class PedidoModel {
  id?: number;
  clienteId: number;
  atendenteId?: number;
  statusPedidoId: number;
  dataHora: Date;
  observacoes?: string;
  itens: ItemPedidoModel[];
}
