export class UsuarioModel {
  id: number;
  nome: string;
  cpf: string;
  email: string;
  login: string;
  senha?: string;
  perfil: number;
  perfilDesc?: string;
}
