import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cpfPrivado'
})
export class CpfPrivadoPipe implements PipeTransform {

  transform(value: string | null | undefined): string {
    if (!value) return '';

    const cpfLimpo = value.replace(/\D/g, '');

    if (cpfLimpo.length !== 11) return value;

    const ultimos = cpfLimpo.slice(-3);
    return `***.***.***-${ultimos}`;
  }

}
