import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {PizzaModel} from "../../../../model/pizza.model";
import {TamanhoPizzaEnum} from "../../../../shared/util/enum/tamanho-pizza-enum";
import {TipoProdutoEnum} from "../../../../shared/util/enum/tipo-produto-enum";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";
import {PizzaService} from "../../../../shared/service/pizza.service";
import {MensagensProdutoUtil} from "../../util/mensagens-produto.util";
import {SelectItem} from "primeng/api";

@Component({
  selector: 'app-pizza-form',
  templateUrl: './pizza-form.component.html',
  styleUrls: ['./pizza-form.component.scss']
})
export class PizzaFormComponent implements OnInit {

  @Output() answerForm: EventEmitter<boolean> = new EventEmitter();
  @Output() list: EventEmitter<boolean> = new EventEmitter();

  formGroup: FormGroup;
  newPizza: PizzaModel;
  entidade = EntidadeUtil.PIZZA;
  tamanhosPizza: SelectItem[];

  constructor(
    private fb: FormBuilder,
    private pizzaService: PizzaService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.initForm();
    this.initDropdown();
  }

  initDropdown(): void {
    this.tamanhosPizza = TamanhoPizzaEnum.values.map(item => ({
      label: item.titulo,
      value: item.index,
      fatias: item.fatias
    }));
  }

  initForm(): void {
    this.formGroup = this.fb.group({
      id: [null],
      nome: [null, [Validators.required, Validators.maxLength(100)]],
      descricao: [null, [Validators.required, Validators.maxLength(255)]],
      precoVenda: [null, [Validators.required, Validators.min(0.01)]],
      tamanhoId: [null, [Validators.required]],
      qtdFatias: [{value: 0, disabled: true}, Validators.required],
      tipoProdutoId: [TipoProdutoEnum.PIZZA.index]
    });
  }

  saveForm(): void {
    this.newPizza = this.formGroup.getRawValue();
    console.log(this.newPizza);
    this.pizzaService.save(this.newPizza)
      .subscribe({
        next: () => {
          this.showSuccessMsgAccordingToId(this.newPizza.id);
          this.closeForm();
          this.list.emit(true);
        },
        error: (error) => {
          this.showErrorMsgAccordingToId(this.newPizza.id, error.message);
          this.list.emit(true);
        }
      });
  }

  editPizza(id: number): void {
    this.pizzaService.findById(id).subscribe({
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

  atribuirQtdeFatia(tamanhoId: any): void {
    const tamanhoSelecionado = TamanhoPizzaEnum.obterPorIndex(tamanhoId);
    this.formGroup.get('qtdFatias')?.setValue(tamanhoSelecionado.fatias);
  }
}
