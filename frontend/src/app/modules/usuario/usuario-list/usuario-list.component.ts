import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ColumnUtil} from "../../../shared/util/column-util";
import {UsuarioListModel} from "../../../model/list/usuario-list.model";
import {UsuarioModel} from "../../../model/usuario.model";
import {UsuarioFormComponent} from "../usuario-form/usuario-form.component";
import {UsuarioService} from "../../../shared/service/usuario.service";
import {MensagensConfirmacao} from "../../../shared/util/msg-confirmacao-dialog-util";
import {TituloModalUsuarioUtil} from "../util/titulo-modal-usuario-util";
import {EntidadeUtil} from "../../../shared/util/entidade-util";
import {finalize} from "rxjs";
import {Page} from "../../../shared/util/page-util";
import {UsuarioColumnUtil} from "../util/usuario-column-util";
import {MensagensUsuarioUtil} from "../util/mensagens-usuario-util";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {ChangePasswordComponet} from "../cahngePassword-form/changePassword-form.component";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.scss']
})
export class UsuarioListComponent implements OnInit {

  columns: ColumnUtil[] = UsuarioColumnUtil.USER_COLUMNS;
  usersList: Page<UsuarioListModel[]> | any = new Page<UsuarioListModel[]>();

  user: UsuarioModel;

  titleDialog: string

  @BlockUI() blockUI: NgBlockUI;
  @Input() display = false;
  @Input() displayChangePassword = false;
  @ViewChild(UsuarioFormComponent) userFormComponent: UsuarioFormComponent;
  @ViewChild(ChangePasswordComponet) changePasswordComponent: ChangePasswordComponet;

  constructor(private userService: UsuarioService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.blockUI.start();
    this.userService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensUsuarioUtil.ERROS_LIST_ALL, MensagensProntasUtil.ERROR);
        }
      });
  }

  listAllUsers(): void {
    this.userService.findAll().subscribe((resp) => {
      this.resultRequestList(resp)
    });
  }

  private resultRequestList(result: Page<UsuarioListModel[]>): void {
    result.content ? this.usersList = result : this.usersList = [];
  }

  newUser(): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.NEW.index).header;
    this.userFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.userFormComponent.saveUserForm();
    this.onClose();
  }

  onSaveNewPassword(): void {
    this.changePasswordComponent.changePassword();
    //this.onCloseModalChangePassword();
  }

  editUser(id: number): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.EDIT.index).header;
    this.display = true;
    this.userFormComponent.editUser(id);
  }

  changePassword(id: number): void {
    this.titleDialog = TituloModalUsuarioUtil.setTitulo(TituloModalUsuarioUtil.CHANGE_PASSWORD.index).header;
    this.displayChangePassword = true;
    this.changePasswordComponent.user = id;
  }

  deactivateUser(id: number): void {
    this.userService.delete(id).subscribe(() => this.listAllUsers());
  }

  confirmAction(info: any): void {
    this.message.confirmarDialog(info.id, () => this.deactivateUser(info.id), EntidadeUtil.USUARIO, info.nome)
  }

  resetForm(): void {
    this.userFormComponent.closeForm();
  }

  onClose(): void {
    this.updateList();
    this.userFormComponent.formGroup.reset();
  }

  onCloseModalChangePassword() {
    this.updateList();
    this.changePasswordComponent.formGroup.reset();
    this.displayChangePassword = false;
  }

  private updateList() {
    if (this.userFormComponent.list) {
      this.listAllUsers();
    }
    this.display = false;
  }

}
