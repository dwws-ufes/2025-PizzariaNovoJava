export class EntidadeUtil {

  static readonly USUARIO = new EntidadeUtil(1, 'Usuário');
  static readonly PRODUTO = new EntidadeUtil(2, 'Produto');
  static readonly CLIENTE = new EntidadeUtil(3, 'Cliente');
  static readonly CAIXA = new EntidadeUtil(4, 'Caixa');
  static readonly COZINHA = new EntidadeUtil(5, 'Cozinha');
  static readonly SOBREMESA = new EntidadeUtil(6, 'Sobremesa');
  static readonly PIZZA = new EntidadeUtil(7, 'Pizza');
  static readonly BEBIDA = new EntidadeUtil(8, 'Bebida');
  static readonly BAR = new EntidadeUtil(9, 'Bar');

  static values = [
    EntidadeUtil.USUARIO,
    EntidadeUtil.PRODUTO,
    EntidadeUtil.CLIENTE,
    EntidadeUtil.CAIXA,
    EntidadeUtil.COZINHA,
    EntidadeUtil.SOBREMESA,
    EntidadeUtil.PIZZA,
    EntidadeUtil.BEBIDA,
    EntidadeUtil.BAR
  ];

  constructor(
    public id: number,
    public descricao: string
  ) {
  }
}
