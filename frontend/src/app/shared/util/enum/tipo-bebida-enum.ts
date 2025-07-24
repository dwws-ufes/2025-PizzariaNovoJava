export class TipoBebidaEnum {
  static readonly REFRIGERANTE = new TipoBebidaEnum(0, 'Refrigerante');
  static readonly SUCO = new TipoBebidaEnum(1, 'Suco');
  static readonly AGUA = new TipoBebidaEnum(2, 'Água');
  static readonly CERVEJA = new TipoBebidaEnum(3, 'Cerveja');
  static readonly VINHO = new TipoBebidaEnum(4, 'Vinho');
  static readonly DRINKS = new TipoBebidaEnum(5, 'Drinks');
  static readonly ENERGETICO = new TipoBebidaEnum(6, 'Energético');

  static values = [
    TipoBebidaEnum.REFRIGERANTE,
    TipoBebidaEnum.SUCO,
    TipoBebidaEnum.AGUA,
    TipoBebidaEnum.CERVEJA,
    TipoBebidaEnum.VINHO,
    TipoBebidaEnum.DRINKS,
    TipoBebidaEnum.ENERGETICO
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): TipoBebidaEnum | any {
    return TipoBebidaEnum.values.find(prod => prod.index === index);
  }

  static setClasse(id: number): TipoBebidaEnum {
    return TipoBebidaEnum.obterPorIndex(id);
  }
}
