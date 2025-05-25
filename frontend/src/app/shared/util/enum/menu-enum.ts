export class MenuEnum {
  static readonly USUARIO = new MenuEnum(0, 'Usuarios');

  static values = [
    MenuEnum.USUARIO,
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
