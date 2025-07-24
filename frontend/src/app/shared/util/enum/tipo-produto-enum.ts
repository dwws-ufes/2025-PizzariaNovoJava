export class TipoProdutoEnum {
  static readonly PIZZA = new TipoProdutoEnum(0, 'Pizza');
  static readonly BEBIDA = new TipoProdutoEnum(1, 'Bebida');
  static readonly SOBREMESA = new TipoProdutoEnum(2, 'Sobremesa');

  static values = [
    TipoProdutoEnum.PIZZA,
    TipoProdutoEnum.BEBIDA,
    TipoProdutoEnum.SOBREMESA
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): TipoProdutoEnum | any {
    return TipoProdutoEnum.values.find(prod => prod.index === index);
  }

  static setClasse(id: number): TipoProdutoEnum {
    return TipoProdutoEnum.obterPorIndex(id);
  }
}
