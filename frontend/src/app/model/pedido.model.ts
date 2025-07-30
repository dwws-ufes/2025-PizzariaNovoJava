import {ItemPedidoModel} from "./item-pedido.model";

export class PedidoModel {
  id?: number;
  clienteId: number;
  atendenteId?: number;
  status: number;
  dataHora?: Date;
  observacoes?: string;
  valorTotal: number;
  quatidateItens: number;
  itens: ItemPedidoModel[];
}
