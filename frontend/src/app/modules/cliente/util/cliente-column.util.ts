import {ColumnUtil} from "../../../shared/util/column-util";

export class ClienteColumnUtil {

	static CUSTOMER_COLUMNS: ColumnUtil[] = [
		{
			header: 'ID',
			field: 'id'
		},
		{
			header: 'Nome',
			field: 'nome',
		},
		{
			header: 'CPF',
			field: 'cpf',
      type: 'cpf',
		},
    {
			header: 'Telefone',
			field: 'telefone',
      type: 'tel',
		},
    {
			header: 'E-mail',
			field: 'email',
      type: 'notRequired',
		},
    {
			header: 'Ações',
			field: 'acoes',
      columnWidth: '132px'
		},
	];
}
