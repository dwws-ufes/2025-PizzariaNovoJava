export class TamanhoPizzaEnum {
  static readonly P = new TamanhoPizzaEnum(0, "Pequena");
  static readonly M = new TamanhoPizzaEnum(1, "Média");
  static readonly G = new TamanhoPizzaEnum(2, "Grande");
  static readonly GG = new TamanhoPizzaEnum(3, "Gigante");
  static readonly FATIA = new TamanhoPizzaEnum(4, "Fatia");

  static values = [
    TamanhoPizzaEnum.P,
    TamanhoPizzaEnum.M,
    TamanhoPizzaEnum.G,
    TamanhoPizzaEnum.GG,
    TamanhoPizzaEnum.FATIA
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): TamanhoPizzaEnum | any {
    return TamanhoPizzaEnum.values.find(prod => prod.index === index);
  }

  static setClasse(id: number): TamanhoPizzaEnum {
    return TamanhoPizzaEnum.obterPorIndex(id);
  }
}
