import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BarRoutingModule } from './bar-routing.module';
import { BarComponent } from './bar/bar.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    BarComponent
  ],
  imports: [
    CommonModule,
    BarRoutingModule,
    SharedModule
  ]
})
export class BarModule { }
