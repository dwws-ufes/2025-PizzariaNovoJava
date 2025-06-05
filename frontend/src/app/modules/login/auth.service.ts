import {EventEmitter, Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {UsuarioModel} from "../../model/usuario.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {UsuarioAutenticacaoModel} from "../../model/usuario-autenticacao.model";
import {MensagensProntasUtil} from "../../shared/util/messages/MensagensProntas.util";
import {MensagensConfirmacao} from "../../shared/util/msgConfirmacaoDialog.util";
import {AuthenticationModel} from "../../model/authentication.model";

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  resourceUrl = environment.apiUrl + '/auth';
  username: string
  private usuarioAutenticado: boolean;
  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router,
              private http: HttpClient,
              private message: MensagensConfirmacao) {
  }

  authentication(entity: UsuarioAutenticacaoModel): Observable<AuthenticationModel> {
    return this.http.post<AuthenticationModel>(`${this.resourceUrl}/authenticate`, entity);
  }

  public nomeUsuario() {
    return this.username
  }

  saveRoleOnLocalStorege(role: any, usuario: string) {
    localStorage.setItem('roleDescription', role);
    localStorage.setItem('userName', usuario)
  }

  login(usuario: UsuarioAutenticacaoModel): void {
    this.authentication(usuario)
      .subscribe({
          next: (response: AuthenticationModel) => {
            console.log(response)
            const userResponse: UsuarioModel = response.usuario;
            this.username = userResponse.nome;
            this.saveRoleOnLocalStorege(userResponse.perfilDesc, userResponse.login);
            console.log(localStorage.getItem('roleDescription'))
            this.usuarioAutenticado = true;
            this.mostrarMenuEmitter.emit(true);
            this.router.navigate(['/'])
            this.message.showSuccess(`Bem vindo ${userResponse.nome}`);
            location.reload();
          },
          error: () => {
            this.usuarioAutenticado = false;
            this.mostrarMenuEmitter.emit(false);
            this.message.showError(MensagensProntasUtil.USER_LOGIN_ERROR, MensagensProntasUtil.ERROR);
            console.log('ERRO AO LOGAR')
            console.log("ERROR AO AUTENTICAR")
          }
        }
      )
    //Enviar para o backend na rota de Authenticate
    // this.usuarioAutenticado = true;
    // this.mostrarMenuEmitter.emit(true);
    // this.username = 'Filipe No AuthService';
  }
}
