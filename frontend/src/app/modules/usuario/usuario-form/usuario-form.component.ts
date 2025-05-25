import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsuarioModel} from "../../../model/usuario.model";
import {SelectItem} from "primeng/api";
import {UsuarioService} from "../../../shared/service/usuario.service";
import {MensagensConfirmacao} from "../../../shared/util/msgConfirmacaoDialog.util";
import {PerfilService} from "../../../shared/service/perfil.service";
import {MensagensUsuarioUtil} from "../util/mensagens-usuario.util";

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent implements OnInit {

  @Input() userModel: UsuarioModel;
  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;

  newUser: UsuarioModel;

  typeProfile: SelectItem[];
  list: boolean = false;

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
      id: [null],
      nome: [null, [Validators.required]],
      usuario: [null, [Validators.required]],
      senha: [null, [Validators.required]],
      confirmSenha: [null, [Validators.required]],
      idPerfil: [null, [Validators.required]]
    });
  }

  saveUserForm(): void {
    this.newUser = this.formGroup.getRawValue();
    this.userService.save(this.newUser)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newUser.id);
          this.closeForm();
          this.list = true;
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newUser.id, error.message);
        }
      });
  }

  editUser(id: number): void {
    this.userService.findById(id).subscribe({
        next: (response) => {
          this.formGroup.patchValue(response);
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
