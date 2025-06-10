import {ColumnUtil} from "../../../shared/util/column-util";

export class UsuarioColumnUtil {

	static USER_COLUMNS: ColumnUtil[] = [
		{
			header: 'ID',
			field: 'id'
		},
		{
			header: 'Nome',
			field: 'nome',
		},
		{
			header: 'Login Usuário',
			field: 'login',
		},
    {
      header: 'CPF',
      field: 'cpf',
      type: 'cpf'
    },
		{
			header: 'Perfil',
			field: 'descPerfil'
		},
    {
			header: 'Ações',
			field: 'acoes',
      columnWidth: '132px'
		},
	];
}
