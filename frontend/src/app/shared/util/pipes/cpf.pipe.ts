import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'cpf'
})

export class CPFPipe implements PipeTransform {
    transform(value: any) {
        if (value == null || value == "") {
            return "";
        }
        return value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g,"\$1.\$2.\$3\-\$4")
    }
}
