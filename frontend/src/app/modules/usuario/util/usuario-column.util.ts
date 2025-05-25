import {ColumnUtil} from "../../../shared/util/columnUtil";

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
			header: 'Usuário',
			field: 'usuario',
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
