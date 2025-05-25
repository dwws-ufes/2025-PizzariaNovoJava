import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ColumnUtil} from "../../util/columnUtil";
import {Page} from "../../util/page";

@Component({
  selector: 'custom-table',
  templateUrl: './custom-table.component.html',
  styleUrls: ['./custom-table.component.scss']
})
export class CustomTableComponent implements OnInit {

  @Input() colunas: ColumnUtil[] = [];
  @Input() dados: Page<any>;
  @Input() rows: number;
  @Input() paginator: boolean;

  @Input() editarDado: boolean;
  @Input() changePassword: boolean;
  @Input() desativarDado: boolean;
  @Input() reativarDado: boolean;
  @Input() visualizarDado: boolean;
  @Input() entradaCliente: boolean;
  @Input() saidaCliente: boolean;
  @Input() repor: boolean;

  @Output() public abrirModalEntrada: EventEmitter<number> = new EventEmitter<number>();
  @Output() public abrirModalSaida: EventEmitter<number> = new EventEmitter<number>();
  @Output() public abrirModalVisualizar: EventEmitter<number> = new EventEmitter<number>();
  @Output() public abrirModalChangePassword: EventEmitter<number> = new EventEmitter<number>();
  @Output() public abrirModalHabilitado: EventEmitter<number> = new EventEmitter<number>();
  @Output() public deletarDado: EventEmitter<number> = new EventEmitter<number>();
  @Output() public reativar: EventEmitter<number> = new EventEmitter<number>();
  @Output() public solicitarReposicao: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit(): void {
  }
}
