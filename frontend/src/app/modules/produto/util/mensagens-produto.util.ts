export class MensagensProdutoUtil {
  static readonly SUCCESS_CREATED = (tipo: string) => `${tipo} criado(a) com sucesso`;
  static readonly ERROR_CREATED = (tipo: string) => `Erro ao salvar ${tipo.toLowerCase()}`;
  static readonly UPDATE_SUCCESSFUL = (tipo: string) => `${tipo} atualizado(a) com sucesso`;
  static readonly ERROR_UPDATE = (tipo: string) => `Erro ao editar ${tipo.toLowerCase()}`;
  static readonly DELETE_SUCCESSFUL = (tipo: string) => `${tipo} removido(a) com sucesso`;
  static readonly ERROR_DELETE = (tipo: string) => `Erro ao remover ${tipo.toLowerCase()}`;
}
