export class BebidaModel {
  id?: number;
  nome: string;
  descricao: string;
  precoVenda: number;
  ativo: boolean = true;
  volume: number;
  fabricante?: string;
  tipoBebidaId: number;
}
