import {ColumnUtil} from "../../../shared/util/column-util";

export class ProdutoColumnUtil {

  static INPUT_COLUMNS: ColumnUtil[] = [
    {
      header: 'Código de Barras',
      field: 'codigoBarras'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Qtde. Estoque',
      field: 'qtdeEstoque',
    },
    {
      header: 'Preço Compra',
      field: 'precoCompra',
      type: 'price',
      pipe: 'currency',
    },
    {
      header: 'Unidade',
      field: 'unidade'
    },
    {
      header: 'Ponto de Encomenda',
      field: 'pontoEncomenda'
    },
    {
      header: 'Ações',
      field: 'acoes',
      columnWidth: '132px'
    },
  ];

  static DRAFT_BEER_COLUMNS: ColumnUtil[] = [
    {
      header: 'RFID',
      field: 'etiquetaRFID'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Qtde. Estoque',
      field: 'qtdeEstoque',
    },
    {
      header: 'Total Litros',
      field: 'litro_chope',
      type: 'litro'
    },
    {
      header: 'Preço Compra',
      field: 'precoCompra',
      type: 'price',
      pipe: 'currency',
    },
    {
      header: 'Unidade',
      field: 'unidade'
    },
    {
      header: 'Ponto de Encomenda',
      field: 'pontoEncomenda'
    },
    {
      header: 'Preço de Venda',
      field: 'precoVenda',
      type: 'price',
      pipe: 'currency',
    },
    {
      header: 'Ações',
      field: 'acoes',
      columnWidth: '132px'
    },
  ];

  static ENTRY_PRODUCTS_COLUMNS: ColumnUtil[] = [
    {
      header: 'Código de Barras',
      field: 'codigoBarras'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Qtde. Estoque',
      field: 'qtdeEstoque',
    }
  ];

  static WITHDRAW_PRODUCTS_COLUMNS: ColumnUtil[] = [
    {
      header: 'Código de Barras',
      field: 'codigoBarras'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Qtde. Estoque',
      field: 'qtdeEstoque',
    }
  ];

  static ENTRY_DRAFF_BEER_COLUMNS: ColumnUtil[] = [
    {
      header: 'Etiqueta RFID',
      field: 'etiquetaRFID'
    },
    {
      header: 'Descrição',
      field: 'descricao',
    },
    {
      header: 'Quantidade',
      field: 'qtdeEstoque',
    },
    {
      header: 'Total Litros',
      field: 'litro_chope',
    }
  ];

}
