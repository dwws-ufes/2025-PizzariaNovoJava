export class PizzaModel {
  id?: number;
  nome: string;
  descricao: string;
  precoVenda: number;
  ativo: boolean = true;
  tamanhoId: number;
  qtdFatias: number;
  tipoProdutoId: number;
}
