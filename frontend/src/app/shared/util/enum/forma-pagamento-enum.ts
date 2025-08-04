export class FormaPagamentoEnum {
  static readonly DINHEIRO = new FormaPagamentoEnum(0, 'Dinheiro');
  static readonly PIX = new FormaPagamentoEnum(1, 'Pix');
  static readonly DEBITO = new FormaPagamentoEnum(2, 'Débito');
  static readonly CREDITO = new FormaPagamentoEnum(3, 'Crédito');

  static values = [
    FormaPagamentoEnum.DINHEIRO,
    FormaPagamentoEnum.PIX,
    FormaPagamentoEnum.DEBITO,
    FormaPagamentoEnum.CREDITO
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): FormaPagamentoEnum | any {
    return FormaPagamentoEnum.values.find(prod => prod.index === index);
  }

  static setClasse(id: number): FormaPagamentoEnum {
    return FormaPagamentoEnum.obterPorIndex(id);
  }
}
