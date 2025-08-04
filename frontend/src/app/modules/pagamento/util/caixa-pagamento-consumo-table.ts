import {ColumnUtil} from "../../../shared/util/column-util";

export class CaixaPagamentoConsumoTable {

  static CONSUME_TABLE: ColumnUtil[] = [
    {
      header: 'Produto Consumido',
      field: 'descricao',
    },
    {
      header: 'Valor Compra',
      field: 'valor',
    }
  ];
}
