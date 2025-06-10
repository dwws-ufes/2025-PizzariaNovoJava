import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges, OnChanges} from '@angular/core';
import {ColumnUtil} from "../../util/column-util";
import {Page} from "../../util/page-util";

@Component({
  selector: 'custom-table',
  templateUrl: './custom-table.component.html',
  styleUrls: ['./custom-table.component.scss']
})
export class CustomTableComponent implements OnInit, OnChanges {
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

  globalFilter: string = '';
  filteredData: any[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.updateFilteredData();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['dados']) {
      this.updateFilteredData();
    }
  }

  updateFilteredData(): void {
    this.filteredData = this.dados?.content ? [...this.dados.content] : [];
    if (this.globalFilter) {
      this.filterTable();
    }
  }

  filterTable(): void {
    if (!this.globalFilter) {
      this.filteredData = this.dados?.content ? [...this.dados.content] : [];
      return;
    }

    const searchTerm = this.globalFilter.toLowerCase();

    this.filteredData = this.dados.content.filter(item => {
      return Object.keys(item).some(key => {
        // Verifica se a coluna existe na configuração de colunas
        const columnExists = this.colunas.some(col => col.field === key);

        if (!columnExists) return false;

        const value = item[key];
        if (value === null || value === undefined) return false;

        return value.toString().toLowerCase().includes(searchTerm);
      });
    });
  }
}
