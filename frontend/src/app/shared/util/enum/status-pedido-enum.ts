export class StatusPedido {
  static readonly PENDENTE = new StatusPedido(0, 'Pendente');
  static readonly EM_PREPARO = new StatusPedido(1, 'Em Preparo');
  static readonly PRONTO = new StatusPedido(2, 'Pronto');
  static readonly ENTREGUE = new StatusPedido(3, 'Entregue');
  static readonly CANCELADO = new StatusPedido(4, 'Cancelado');

  static values = [
    StatusPedido.PENDENTE,
    StatusPedido.EM_PREPARO,
    StatusPedido.PRONTO,
    StatusPedido.ENTREGUE,
    StatusPedido.CANCELADO
  ];

  constructor(
    public index: number,
    public titulo: string
  ) {
  }

  static obterPorIndex(index: number): StatusPedido | any {
    return StatusPedido.values.find(status => status.index === index);
  }

  static setClasse(id: number): StatusPedido {
    return StatusPedido.obterPorIndex(id);
  }

}

