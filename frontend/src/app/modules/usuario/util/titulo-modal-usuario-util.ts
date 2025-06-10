export class TituloModalUsuarioUtil {
    static readonly NEW = new TituloModalUsuarioUtil(0, 'Novo Usuário');
    static readonly VIEW = new TituloModalUsuarioUtil(1, 'Visualizar Dados Usuário');
    static readonly EDIT = new TituloModalUsuarioUtil(2, 'Editar Usuário');
    static readonly DELETE = new TituloModalUsuarioUtil(3, 'Excluir Usuário');
    static readonly CHANGE_PASSWORD = new TituloModalUsuarioUtil(4, 'Alterar Senha');

    static values = [
      TituloModalUsuarioUtil.NEW,
      TituloModalUsuarioUtil.VIEW,
      TituloModalUsuarioUtil.EDIT,
      TituloModalUsuarioUtil.DELETE,
      TituloModalUsuarioUtil.CHANGE_PASSWORD,
    ];

    private constructor(
        public index: number,
        public header: string,
    ) {
    }

    static obterPorIndex(index: number): TituloModalUsuarioUtil | any {
        return TituloModalUsuarioUtil.values.find(titulo => titulo.index === index);
    }

    static setTitulo(id: number): TituloModalUsuarioUtil {
        return TituloModalUsuarioUtil.obterPorIndex(id);
    }
}
