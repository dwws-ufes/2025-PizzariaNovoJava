import {Injectable} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {EntidadeUtil} from "./entidade-util";

@Injectable()
export class MensagensConfirmacao {

  constructor(private confirmMessage: ConfirmationService,
              private messageService: MessageService) {
  }

  public confirmarDialog(id: number, alterarSituacao: () => void, entidade: EntidadeUtil, description?: string): void {
    this.confirmMessage.confirm({
      header: 'Confirmação',
      message: 'Deseja desativar esse(a) ' + entidade.descricao + ' - ' + description + '?',
      acceptLabel: 'Sim',
      rejectLabel: 'Cancelar',
      accept: alterarSituacao
    });
  }

  public confirmReplenishment(id: number, alterarSituacao: () => void, description?: string): void {
    this.confirmMessage.confirm({
      header: 'Confirmação',
      message: 'Deseja repor ese prato' + ' - ' + description + '?',
      acceptLabel: 'Sim',
      rejectLabel: 'Cancelar',
      accept: alterarSituacao
    });
  }

  public confirmRequestDishReplacement(id: number, alterarSituacao: () => void, description?: string): void {
    this.confirmMessage.confirm({
      header: 'Confirmação',
      message: 'Confirmar solicitação de reposição do prato' + ' - ' + description + '?',
      acceptLabel: 'Sim',
      rejectLabel: 'Cancelar',
      accept: alterarSituacao
    });
  }

  public confirmExitCustomer(id: number, alterarSituacao: () => void, description?: string): void {
    this.confirmMessage.confirm({
      header: 'Confirmação',
      message: 'Deseja liberar saída do cliente' + ' - ' + description + '?',
      acceptLabel: 'Sim',
      rejectLabel: 'Cancelar',
      accept: alterarSituacao
    });
  }


  showSuccess(message: string) {
    this.messageService.add({severity: 'success', summary: 'Success', detail: message});
  }

  showInfo(message: string, summary: string) {
    this.messageService.add({severity: 'info', summary: summary, detail: message});
  }

  showWarn(message: string, summary: string) {
    this.messageService.add({severity: 'warn', summary: summary, detail: message});
  }

  showError(message: string, error: string) {
    this.messageService.add({severity: 'error', summary: error, detail: message});
  }
}
