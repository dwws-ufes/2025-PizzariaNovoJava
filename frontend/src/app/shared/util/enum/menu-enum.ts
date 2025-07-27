export class MenuEnum {
  static readonly USUARIO = new MenuEnum(0, 'usuarios');
  static readonly PRODUTO = new MenuEnum(1, 'produtos');
  static readonly CLIENTE = new MenuEnum(2, 'clientes');
  static readonly CAIXA = new MenuEnum(3, 'caixa');
  static readonly COZINHA = new MenuEnum(4, 'cozinha');
  static readonly BAR = new MenuEnum(5, 'bar');
  static readonly PAINEL = new MenuEnum(6, 'painel');

  static values = [
    MenuEnum.USUARIO,
    MenuEnum.PRODUTO,
    MenuEnum.CLIENTE,
    MenuEnum.CAIXA,
    MenuEnum.COZINHA,
    MenuEnum.BAR,
    MenuEnum.PAINEL
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): MenuEnum | any {
    return MenuEnum.values.find(menu => menu.index === index);
  }

  static setClasse(id: number): MenuEnum {
    return MenuEnum.obterPorIndex(id);
  }
}
