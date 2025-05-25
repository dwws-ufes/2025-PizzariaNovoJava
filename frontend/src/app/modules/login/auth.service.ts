import {EventEmitter, Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {UsuarioModel} from "../../model/usuario.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CaixaModel} from "../../model/caixa-model";
import {environment} from "../../../environments/environment";
import {UsuarioAutenticacaoModel} from "../../model/usuario-autenticacao.model";
import {MensagensProntasUtil} from "../../shared/util/messages/MensagensProntas.util";
import {MensagensConfirmacao} from "../../shared/util/msgConfirmacaoDialog.util";

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

  authentication(entity: UsuarioAutenticacaoModel): Observable<UsuarioModel> {
    return this.http.post<UsuarioModel>(`${this.resourceUrl}/login`, entity);
  }

  public nomeUsuario(){
    return this.username
  }

  saveRoleOnLocalStorege(role: any, usuario: string){
    localStorage.setItem('roleDescription', role);
    localStorage.setItem('userName', usuario)
  }

  login(usuario: UsuarioAutenticacaoModel): void{
    this.authentication(usuario)
      .subscribe({
        next: (response) => {
          console.log(response)
          this.username = response.usuario;
          this.saveRoleOnLocalStorege(response.perfilDesc, response.usuario);
          console.log(localStorage.getItem('roleDescription'))
          this.usuarioAutenticado = true;
          this.mostrarMenuEmitter.emit(true);
          this.router.navigate(['/'])
          this.message.showSuccess(`Bem vindo ${response.nome}`);
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
