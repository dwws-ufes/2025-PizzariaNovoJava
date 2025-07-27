import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SobremesaModel} from 'src/app/model/sobremesa.model';
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {TipoProdutoEnum} from "../../../../shared/util/enum/tipo-produto-enum";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";
import {SobremesaService} from "../../../../shared/service/sobremesa.service";
import {MensagensProdutoUtil} from "../../util/mensagens-produto.util";

@Component({
  selector: 'app-sobremesa-form',
  templateUrl: './sobremesa-form.component.html',
  styleUrls: ['./sobremesa-form.component.scss']
})
export class SobremesaFormComponent implements OnInit {

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();
  @Output() list: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newSobremesa: SobremesaModel;
  entidade = EntidadeUtil.SOBREMESA;

  constructor(
    private fb: FormBuilder,
    private sobremesaService: SobremesaService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.formGroup = this.fb.group({
      id: [null],
      nome: [null, [Validators.required, Validators.maxLength(100)]],
      descricao: [null, [Validators.required, Validators.maxLength(255)]],
      precoVenda: [null, [Validators.required, Validators.min(0.01)]],
      tipoProdutoId: [TipoProdutoEnum.SOBREMESA.index]
    });
  }

  saveForm(): void {
    this.newSobremesa = this.formGroup.getRawValue();
    this.sobremesaService.save(this.newSobremesa)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newSobremesa.id);
          this.closeForm();
          this.list.emit(true);
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newSobremesa.id, error.message);
          this.list.emit(true);
        }
      });
  }

  editSobremesa(id: number): void {
    this.sobremesaService.findById(id).subscribe({
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

  isFieldInvalid(field: string): boolean {
    const control = this.formGroup.get(field);
    return control != null && control.invalid && (control.dirty || control.touched);
  }

  private showSuccessMsgAccordingToId(idCustomer: number | undefined): void {
    idCustomer ? this.message.showSuccess(MensagensProdutoUtil.UPDATE_SUCCESSFUL(this.entidade.descricao))
      : this.message.showSuccess(MensagensProdutoUtil.SUCCESS_CREATED(this.entidade.descricao));
  }

  private showErrorMsgAccordingToId(idCustomer: number | undefined, errorMsg: string): void {
    idCustomer ? this.message.showError(MensagensProdutoUtil.ERROR_UPDATE(this.entidade.descricao), errorMsg)
      : this.message.showError(MensagensProdutoUtil.ERROR_CREATED(this.entidade.descricao), errorMsg);
  }
}
