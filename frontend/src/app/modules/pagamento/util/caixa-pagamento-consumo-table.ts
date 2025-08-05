import {ColumnUtil} from "../../../shared/util/column-util";

export class CaixaPagamentoConsumoTable {

  static CONSUME_TABLE: ColumnUtil[] = [
    {
      header: 'Produto Consumido',
      field: 'nomeProduto',
    },
    {
      header: 'Valor Compra',
      field: 'valorItem',
    }
  ];
}
