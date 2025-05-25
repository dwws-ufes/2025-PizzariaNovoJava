import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {UsuarioModel} from "../../../model/usuario.model";
import {UsuarioAutenticacaoModel} from "../../../model/usuario-autenticacao.model";

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.scss']
})
export class LoginViewComponent implements OnInit {
  username: string;
  password: string;

  usuario: UsuarioAutenticacaoModel = new UsuarioAutenticacaoModel();
  private usuarioAutenticado: boolean;
  constructor(
    private authService: AuthService
  ) {
  }

  ngOnInit(): void {
    this.username = 'teste';
    this.password ='teste';
  }

  fazerLogin( ): void{
    this.authService.login(this.usuario);
  }

}
