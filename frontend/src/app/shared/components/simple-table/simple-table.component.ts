import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ColumnUtil} from "../../util/columnUtil";

@Component({
    selector: 'simple-table',
    templateUrl: './simple-table.component.html',
    styleUrls: ['./simple-table.component.scss']
})
export class SimpleTableComponent implements OnInit {

    @Input() colunas: ColumnUtil[];
    @Input() dados: any;
    @Input() rows: number;
    @Input() paginator: boolean;
    @Input() chope: boolean;

    @Output() public abrirModalHabilitado: EventEmitter<number> = new EventEmitter<number>();
    @Output() public abrirModalVisualizar: EventEmitter<number> = new EventEmitter<number>();

    constructor() {
    }

    ngOnInit(): void {
    }
}
