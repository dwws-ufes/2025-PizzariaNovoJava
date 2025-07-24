import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MensagensConfirmacao} from "../../../../shared/util/msg-confirmacao-dialog-util";
import {PizzaModel} from "../../../../model/pizza.model";
import {TamanhoPizzaEnum} from "../../../../shared/util/enum/tamanho-pizza-enum";
import {TipoProdutoEnum} from "../../../../shared/util/enum/tipo-produto-enum";
import {MensagensProdutoUtil} from "../../util/mensagens-produto.util";
import {EntidadeUtil} from "../../../../shared/util/entidade-util";

@Component({
  selector: 'app-pizza-form',
  templateUrl: './pizza-form.component.html',
  styleUrls: ['./pizza-form.component.scss']
})
export class PizzaFormComponent implements OnInit {

  @Input() pizza: PizzaModel;
  @Output() onClose: EventEmitter<void> = new EventEmitter();
  @Output() onSave: EventEmitter<PizzaModel> = new EventEmitter();

  list: boolean = true;
  formGroup: FormGroup;
  entidade = EntidadeUtil.PIZZA;
  tamanhosPizza = TamanhoPizzaEnum.values;

  constructor(
    private fb: FormBuilder,
    // private pizzaService: PizzaService,
    private message: MensagensConfirmacao
  ) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.pizza) {
      this.loadPizzaData();
    }
  }

  initForm(): void {
    this.formGroup = this.fb.group({
      id: [null],
      nome: [null, [Validators.required, Validators.maxLength(100)]],
      descricao: [null, [Validators.required, Validators.maxLength(255)]],
      precoVenda: [null, [Validators.required, Validators.min(0.01)]],
      tamanho: [null, [Validators.required]],
      qtdFatias: [null, [Validators.required]],
      tipoProdutoId: [TipoProdutoEnum.PIZZA.index]
    });
  }

  loadPizzaData(): void {
    this.formGroup.patchValue({
      id: this.pizza.id,
      nome: this.pizza.nome,
      descricao: this.pizza.descricao,
      precoVenda: this.pizza.precoVenda,
      tamanho: this.pizza.tamanhoId,
      qtdFatias: this.pizza.qtdFatias,
    });
  }

  saveForm(): void {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }

    const pizzaData = this.formGroup.value as PizzaModel;

    if (pizzaData.id) {
      this.updatePizza(pizzaData);
    } else {
      this.createPizza(pizzaData);
    }
  }

  createPizza(pizza: PizzaModel): void {
    // this.pizzaService.create(pizza).subscribe({
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

  updatePizza(pizza: PizzaModel): void {
    // this.pizzaService.update(pizza.id, pizza).subscribe({
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
