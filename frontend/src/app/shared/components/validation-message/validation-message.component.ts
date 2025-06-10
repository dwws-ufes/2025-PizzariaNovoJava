import {Component, Input} from '@angular/core';
import {AbstractControl, FormGroupDirective} from "@angular/forms";
import {ValidationUtil} from "../../util/validation-util";

@Component({
  selector: 'app-validation-message',
  templateUrl: './validation-message.component.html',
  styleUrls: ['./validation-message.component.scss']
})
export class ValidationMessageComponent {
  @Input() public field: string;

  constructor(private formGroupDirective: FormGroupDirective) {
  }

  public getMessagesValidation(): string {
    return ValidationUtil.getErrorMessages(this.getField());
  }

  private getField(): AbstractControl | null {
    return this.formGroupDirective.control.get(this.field);
  }
}
