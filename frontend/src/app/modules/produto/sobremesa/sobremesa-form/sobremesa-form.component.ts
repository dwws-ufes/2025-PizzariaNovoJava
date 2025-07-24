import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SobremesaModel} from 'src/app/model/sobremesa.model';
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {TipoProdutoEnum} from "../../../../shared/util/enum/tipo-produto-enum";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";

@Component({
  selector: 'app-sobremesa-form',
  templateUrl: './sobremesa-form.component.html',
  styleUrls: ['./sobremesa-form.component.scss']
})
export class SobremesaFormComponent implements OnInit {

  @Input() sobremesa: SobremesaModel;
  @Output() onClose: EventEmitter<void> = new EventEmitter();
  @Output() onSave: EventEmitter<SobremesaModel> = new EventEmitter();

  formGroup: FormGroup;

  entidade = EntidadeUtil.SOBREMESA;

  list: boolean = false;

  constructor(
    private fb: FormBuilder,
    // private sobremesaService: SobremesaService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.sobremesa) {
      this.loadSobremesaData();
    }
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

  loadSobremesaData(): void {
    this.formGroup.patchValue({
      id: this.sobremesa.id,
      nome: this.sobremesa.nome,
      descricao: this.sobremesa.descricao,
      precoVenda: this.sobremesa.precoVenda,
    });
  }

  saveForm(): void {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }

    const sobremesaData = this.formGroup.value as SobremesaModel;

    if (sobremesaData.id) {
      this.updateSobremesa(sobremesaData);
    } else {
      this.createSobremesa(sobremesaData);
    }
  }

  createSobremesa(sobremesa: SobremesaModel): void {
    // this.sobremesaService.create(sobremesa).subscribe({
    //   next: (response) => {
    //     this.message.showSuccess(MensagensProdutoUtil.SUCCESS_CREATED(this.entidade.descricao));
    //     this.onSave.emit(response);
    //     this.closeForm();
    //   },
    //   error: (error) => {
    //     this.message.showError(MensagensProdutoUtil.ERROR_CREATED(this.entidade.descricao), error.message);
    //   }
    // });
  }

  updateSobremesa(sobremesa: SobremesaModel): void {
    // this.sobremesaService.update(sobremesa.id, sobremesa).subscribe({
    //   next: (response) => {
    //     this.message.showSuccess(MensagensProdutoUtil.UPDATE_SUCCESSFUL(this.entidade.descricao));
    //     this.onSave.emit(response);
    //     this.closeForm();
    //   },
    //   error: (error) => {
    //     this.message.showError(MensagensProdutoUtil.ERROR_UPDATE(this.entidade.descricao), error.message);
    //   }
    // });
  }

  closeForm(): void {
    this.formGroup.reset();
    this.onClose.emit();
  }

  isFieldInvalid(field: string): boolean {
    const control = this.formGroup.get(field);
    return control != null && control.invalid && (control.dirty || control.touched);
  }
}
