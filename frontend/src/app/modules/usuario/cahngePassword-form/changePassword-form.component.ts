import {Component, EventEmitter, Input, OnInit, Output, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsuarioModel} from "../../../model/usuario.model";
import {SelectItem} from "primeng/api";
import {UsuarioService} from "../../../shared/service/usuario.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {PerfilService} from "../../../shared/service/perfil.service";
import {MensagensUsuarioUtil} from "../util/mensagens-usuario.util";

@Component({
  selector: 'app-changePassword-form',
  templateUrl: './changePassword-form.component.html',
  styleUrls: ['./changePassword-form.component.scss']
})
export class ChangePasswordComponet implements OnInit {

  @Input() userModel: UsuarioModel;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;

  newUser: UsuarioModel;

  typeProfile: SelectItem[];
  list: boolean = false;
  user: any;
  constructor(private builder: FormBuilder,
              private userService: UsuarioService,
              private profileService: PerfilService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
    this.profileDropdown();
  }

  public profileDropdown(): void {
    this.profileService.fillProfileDropdown().subscribe((data) => {
      this.typeProfile = data;
    });
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      senha: [null, [Validators.required]],
    });
  }

  changePassword(): void{
    let senha = this.formGroup.getRawValue()
    const newPasswordDto = {
      id: this.user.id,
      senha: senha.senha
    }
    console.log(newPasswordDto)


    this.userService.updtSenha(newPasswordDto).subscribe({
        next: (response) => {
          console.log("OK");
        },
      }
    );
  }

  closeForm(): void {
    this.formGroup.reset();
    this.answerForm.emit();
  }

  private showSuccessMsgAccordingToId(idUser: number): void {
    idUser ? this.message.showSuccess(MensagensUsuarioUtil.UPDATE_SUCCESSFUL_USER)
      : this.message.showSuccess(MensagensUsuarioUtil.SUCCESS_CREATED_USER);
  }

  private showErrorMsgAccordingToId(idUser: number, errorMsg: string): void {
    idUser ? this.message.showError(MensagensUsuarioUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensUsuarioUtil.ERROR_CREATED, errorMsg);
  }

}
