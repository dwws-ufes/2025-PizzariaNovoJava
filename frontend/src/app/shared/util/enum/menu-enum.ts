export class MenuEnum {
  static readonly USUARIO = new MenuEnum(0, 'Usuarios');
  static readonly PRODUTO = new MenuEnum(1, 'Produtos');
  static readonly CLIENTE = new MenuEnum(2, 'Clientes');
  static readonly CAIXA = new MenuEnum(3, 'Caixa');
  static readonly COZINHA = new MenuEnum(4, 'Cozinha');
  static readonly BAR = new MenuEnum(5, 'Bar');
  static readonly PAINEL = new MenuEnum(6, 'Painel');

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
