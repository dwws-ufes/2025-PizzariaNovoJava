import {CommonModule} from '@angular/common';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CustomDialogComponent} from './custom-dialog/custom-dialog.component';
import {PRIMENG_IMPORTS} from "../imports/primeng.imports";
import {CustomTableComponent} from "./custom-table/custom-table.component";
import {CPFPipe} from "../util/pipes/cpf.pipe";
import {SimpleTableComponent} from "./simple-table/simple-table.component";

@NgModule({
  declarations: [
    CustomDialogComponent,
    CustomTableComponent,
    CPFPipe,
    SimpleTableComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    PRIMENG_IMPORTS,
  ],
  exports: [
    CustomDialogComponent,
    CustomTableComponent,
    CPFPipe,
    SimpleTableComponent
  ],
  entryComponents: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComponentsModule {
}
