import {ColumnUtil} from "../../../shared/util/column-util";

export class ProdutoColumnUtil {
  // Colunas genéricas para produtos
  static BASE_PRODUCT_COLUMNS: ColumnUtil[] = [
    {
      header: 'Nome',
      field: 'nome'
    },
    {
      header: 'Descrição',
      field: 'descricao'
    },
    {
      header: 'Preço Venda',
      field: 'precoVenda',
      type: 'price',
      pipe: 'currency'
    },
    {
      header: 'Tipo',
      field: 'tipoProduto'
    },
    {
      header: 'Ações',
      field: 'acoes',
      columnWidth: '132px'
    }
  ];

  static BEBIDA_COLUMNS: ColumnUtil[] = [
    ...this.BASE_PRODUCT_COLUMNS,
    {
      header: 'Volume (ml)',
      field: 'volume',
      type: 'number'
    },
    {
      header: 'Fabricante',
      field: 'fabricante'
    },
    {
      header: 'Tipo Bebida',
      field: 'tipoBebida'
    }
  ];

  static PIZZA_COLUMNS: ColumnUtil[] = [
    ...this.BASE_PRODUCT_COLUMNS,
    {
      header: 'Tamanho',
      field: 'tamanho'
    },
    {
      header: 'Fatias',
      field: 'qtdFatias',
      type: 'number'
    }
  ];

  static SOBREMESA_COLUMNS: ColumnUtil[] = [
    ...this.BASE_PRODUCT_COLUMNS
  ];
}
