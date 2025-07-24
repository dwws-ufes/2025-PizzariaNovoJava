export class TituloModalProdutoUtil {
  private constructor(
    public readonly index: number,
    public readonly header: string
  ) {
  }

  static criarTitulos(nomeProduto: string) {
    return {
      NEW: new TituloModalProdutoUtil(0, `Novo(a) ${nomeProduto}`),
      VIEW: new TituloModalProdutoUtil(1, `Visualizar ${nomeProduto}`),
      EDIT: new TituloModalProdutoUtil(2, `Editar ${nomeProduto}`),
      DELETE: new TituloModalProdutoUtil(3, `Excluir ${nomeProduto}`),
      ENTRY: new TituloModalProdutoUtil(4, `Entrada para ${nomeProduto}`)
    };
  }

  static obterPorIndex(
    titulos: Record<string, TituloModalProdutoUtil>,
    index: number
  ): TituloModalProdutoUtil | undefined {
    return Object.values(titulos).find(titulo => titulo.index === index);
  }

  static setTitulo(
    titulos: Record<string, TituloModalProdutoUtil>,
    id: number
  ): TituloModalProdutoUtil {
    const titulo = this.obterPorIndex(titulos, id);
    if (!titulo) {
      throw new Error(`Título não encontrado para o índice ${id}`);
    }
    return titulo;
  }
}

export class TipoTituloModalProdutoUtil {
  static readonly NEW = 0;
  static readonly VIEW = 1;
  static readonly EDIT = 2;
  static readonly DELETE = 3;
  static readonly ENTRY = 4;
}
