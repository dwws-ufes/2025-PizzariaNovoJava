import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {BebidaModel} from "../../../../model/bebida.model";
import {TipoBebidaEnum} from "../../../../shared/util/enum/tipo-bebida-enum";
import {TipoProdutoEnum} from "../../../../shared/util/enum/tipo-produto-enum";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";
import {BebidaService} from "../../../../shared/service/bebida.service";
import {MensagensProdutoUtil} from "../../util/mensagens-produto.util";
import {SelectItem} from "primeng/api";

@Component({
  selector: 'app-bebida-form',
  templateUrl: './bebida-form.component.html',
  styleUrls: ['./bebida-form.component.scss']
})
export class BebidaFormComponent implements OnInit {

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();
  @Output() list: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newBebida: BebidaModel;
  entidade = EntidadeUtil.BEBIDA;
  tiposBebida: SelectItem[];

  constructor(
    private fb: FormBuilder,
    private bebidaService: BebidaService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.initForm();
    this.initDropdown();
  }

  initDropdown(): void {
    this.tiposBebida = TipoBebidaEnum.values.map(item => ({
      label: item.titulo,
      value: item.index
    }));
  }

  initForm(): void {
    this.formGroup = this.fb.group({
      id: [null],
      nome: [null, [Validators.required, Validators.maxLength(100)]],
      descricao: [null, [Validators.required, Validators.maxLength(255)]],
      precoVenda: [null, [Validators.required, Validators.min(0.01)]],
      volume: [null, [Validators.required, Validators.min(0.01)]],
      fabricante: [null, [Validators.required]],
      tipoBebidaId: [null, [Validators.required]],
      tipoProdutoId: [TipoProdutoEnum.BEBIDA.index]
    });
  }

  saveForm(): void {
    this.newBebida = this.formGroup.getRawValue();
    this.bebidaService.save(this.newBebida)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newBebida.id);
          this.closeForm();
          this.list.emit(true);
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newBebida.id, error.message);
          this.list.emit(true);
        }
      });
  }

  editBebida(id: number): void {
    this.bebidaService.findById(id).subscribe({
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
