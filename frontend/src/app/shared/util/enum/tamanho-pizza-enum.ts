export class TamanhoPizzaEnum {
  static readonly P = new TamanhoPizzaEnum(0, "Pequena", 4);
  static readonly M = new TamanhoPizzaEnum(1, "Média", 6);
  static readonly G = new TamanhoPizzaEnum(2, "Grande", 8);
  static readonly GG = new TamanhoPizzaEnum(3, "Gigante", 10);
  static readonly FATIA = new TamanhoPizzaEnum(4, "Fatia", 1);

  static values = [
    TamanhoPizzaEnum.P,
    TamanhoPizzaEnum.M,
    TamanhoPizzaEnum.G,
    TamanhoPizzaEnum.GG,
    TamanhoPizzaEnum.FATIA
  ];

  constructor(
    public index: number,
    public titulo: string,
    public fatias: number
  ) {}

  static obterPorIndex(index: number): TamanhoPizzaEnum | any {
    return TamanhoPizzaEnum.values.find(prod => prod.index === index);
  }

  static setClasse(id: number): TamanhoPizzaEnum {
    return TamanhoPizzaEnum.obterPorIndex(id);
  }
}
