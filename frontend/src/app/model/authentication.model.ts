import {UsuarioModel} from "./usuario.model";

export class AuthenticationModel {
  usuario: UsuarioModel;
  accessToken: string;
  private refreshToken: string;
}
