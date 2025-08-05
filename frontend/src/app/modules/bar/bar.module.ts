import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BarRoutingModule } from './bar-routing.module';
import { BarComponent } from './bar/bar.component';
import {SharedModule} from "../../shared/shared.module";
import {BadgeModule} from "primeng/badge";


@NgModule({
    declarations: [
        BarComponent
    ],
    exports: [
        BarComponent
    ],
    imports: [
        CommonModule,
        BarRoutingModule,
        SharedModule,
        BadgeModule
    ]
})
export class BarModule { }
