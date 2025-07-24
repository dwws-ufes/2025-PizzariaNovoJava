import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClienteModel} from "../../../model/cliente.model";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensConfirmacao} from "../../../shared/util/msg-confirmacao-dialog-util";
import {MensagensClienteUtil} from "../util/mensagens-cliente.util";
import {FuncoesUtil} from "../../../shared/util/funcoes-util";

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.scss']
})
export class ClienteFormComponent implements OnInit {

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;

  newCustomer: ClienteModel;

  list: boolean = false;

  constructor(private builder: FormBuilder,
              private customerService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.newForm();
  }

  newForm(): void {
    this.formGroup = this.builder.group({
      id: [null],
      nome: [null, [Validators.required]],
      telefone: [null, [Validators.required]],
      email: [null],
      cpf: [null, [Validators.required]]
    });
  }

  saveForm(): void {
    this.newCustomer = this.formGroup.getRawValue();
    this.newCustomer.cpf = FuncoesUtil.formatarCpf(this.newCustomer.cpf);
    this.customerService.save(this.newCustomer)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newCustomer.id);
          this.closeForm();
          this.list = true;
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newCustomer.id, error.message);
        }
      });
  }

  editCustomer(id: number): void {
    this.customerService.findById(id).subscribe({
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

  private showSuccessMsgAccordingToId(idCustomer: number | undefined): void {
    idCustomer ? this.message.showSuccess(MensagensClienteUtil.UPDATE_SUCCESSFUL_CUSTOMER)
      : this.message.showSuccess(MensagensClienteUtil.SUCCESS_CREATED_CUSTOMER);
  }

  private showErrorMsgAccordingToId(idCustomer: number | undefined, errorMsg: string): void {
    idCustomer ? this.message.showError(MensagensClienteUtil.ERROR_UPDATE, errorMsg)
      : this.message.showError(MensagensClienteUtil.ERROR_CREATED, errorMsg);
  }
}
