export class EntidadeUtil {

  static readonly USUARIO = new EntidadeUtil(1, 'Usuário');
  static readonly RELATORIO = new EntidadeUtil(2, 'Relatório');
  static readonly PRODUTO = new EntidadeUtil(3, 'Produto');
  static readonly CLIENTE = new EntidadeUtil(4, 'Cliente');
  static readonly CAIXA = new EntidadeUtil(5, 'Caixa');
  static readonly COZINHA = new EntidadeUtil(6, 'Cozinha');
  static readonly SELF_SERVICE_CHOPE = new EntidadeUtil(7, 'Self-Service');
  static readonly CHOPE = new EntidadeUtil(8, 'Chope');
  static readonly INSUMO = new EntidadeUtil(3, 'Insumo');
  static readonly PRATO = new EntidadeUtil(3, 'Prato');



  static values = [
    EntidadeUtil.USUARIO,
    EntidadeUtil.RELATORIO,
    EntidadeUtil.PRODUTO,
    EntidadeUtil.CLIENTE,
    EntidadeUtil.CAIXA,
    EntidadeUtil.COZINHA,
    EntidadeUtil.SELF_SERVICE_CHOPE,
    EntidadeUtil.CHOPE,
    EntidadeUtil.INSUMO,
    EntidadeUtil.PRATO
  ];

  constructor(
    public id: number,
    public descricao: string
  ) {
  }
}
