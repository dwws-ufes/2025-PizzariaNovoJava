import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {TituloModalClienteUtil} from "../util/titulo-modal-cliente.util";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ClienteFormComponent} from "../cliente-form/cliente-form.component";
import {ClienteService} from "../../../shared/service/cliente.service";
import {MensagensProntasUtil} from "../../../shared/util/messages/MensagensProntas.util";
import {ClienteListModel} from "../../../model/list/cliente-list.model";
import {ClienteColumnUtil} from "../util/cliente-column.util";
import {finalize} from "rxjs";
import {ColumnUtil} from "../../../shared/util/column-util";
import {Page} from "../../../shared/util/page-util";
import {MensagensConfirmacao} from "../../../shared/util/msg-confirmacao-dialog-util";
import {EntidadeUtil} from "../../../shared/util/entidade-util";

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.scss']
})
export class ClienteListComponent implements OnInit {

  columns: ColumnUtil[] = ClienteColumnUtil.CUSTOMER_COLUMNS;
  customersList: Page<ClienteListModel[]> | any = new Page<ClienteListModel[]>();
  customerEntries: ClienteListModel[] = [];

  titleDialog: string;

  @BlockUI() blockUI: NgBlockUI;
  @Input() display: boolean = false;
  @Input() displayEntry: boolean = false;
  @Input() displayExit: boolean = false;
  @Input() displayCheckCardModal: boolean = false;
  @ViewChild(ClienteFormComponent) customerFormComponent: ClienteFormComponent;

  constructor(private customerService: ClienteService,
              private message: MensagensConfirmacao) {
  }

  ngOnInit(): void {
    this.findAllCustomers();
  }

  findAllCustomers(): void {
    this.blockUI.start();
    this.customerService.findAll()
      .pipe(finalize(() => this.blockUI.stop()))
      .subscribe({
        next: (result) => {
          this.resultRequestList(result);
        },
        error: () => {
          this.message.showInfo(MensagensProntasUtil.SUB_MESSAGE_ERROR, MensagensProntasUtil.ERROR);
        }
      })
  }

  private resultRequestList(result: Page<ClienteListModel[]>): void {
    result.content ? this.customersList = result : this.customersList = [];
  }

  newCustomer(): void {
    this.titleDialog = TituloModalClienteUtil.setTitulo(TituloModalClienteUtil.NEW.index).header;
    this.customerFormComponent.formGroup.reset();
    this.display = true;
  }

  onSave(): void {
    this.customerFormComponent.saveForm();
    this.findAllCustomers();
    this.onClose();
  }

  editCustomer(id: number): void {
    this.titleDialog = TituloModalClienteUtil.setTitulo(TituloModalClienteUtil.EDIT.index).header;
    this.display = true;
    this.customerFormComponent.editCustomer(id);
  }

  deactivateCustomer(id: number): void {
    this.customerService.delete(id).subscribe(() => this.findAllCustomers());
  }

  confirmAction(customer: any): void {
    this.message.confirmarDialog(customer.id, () => this.deactivateCustomer(customer.id), EntidadeUtil.CLIENTE, customer.nome)
  }

  onClose(): void {
    this.updateList();
    this.customerFormComponent.formGroup.reset();
  }

  private listAllClients(): void {
    this.customerService.findAll().subscribe((resp) => {
      this.resultRequestList(resp);
    });
  }

  private updateList() {
    if (this.customerFormComponent.list) {
      this.listAllClients();
    }
    this.display = false;
  }

}
