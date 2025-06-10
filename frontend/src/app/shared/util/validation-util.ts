import { AbstractControl, FormGroup } from '@angular/forms';

export class ValidationUtil {
  public static validateForm(form: FormGroup) {
    Object.values(form.controls).forEach((control) => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.validateForm(control);
      }
    });
  }

  private static KEYS: Record<string, string> = {
    required: 'Campo requerido',
    _default: 'Campo de input inválido',
  };

  public static getValidationMessages(control: AbstractControl): string[] {
    const messages: string[] = [];
    if ((control.dirty || control.touched) && control.errors) {
      Object.keys(control.errors).forEach((key) => {
        const message = this.KEYS[key] || this.KEYS['_default'];
        if (!messages.includes(message)) {
          messages.push(message);
        }
      });
    }
    return messages;
  }

  public static getErrorMessages(control: AbstractControl | null): string {
    if (!control) {
      return ' ';
    }
    const keys = this.getValidationMessages(control);
    const messages = keys.length > 0 ? keys : [' '];
    return messages.join(', ');
  }
}
