import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CozinhaRoutingModule } from './cozinha-routing.module';
import { CozinhaComponent } from './cozinha/cozinha.component';
import {SharedModule} from "../../shared/shared.module";
import {BadgeModule} from "primeng/badge";


@NgModule({
    declarations: [
        CozinhaComponent
    ],
    exports: [
        CozinhaComponent
    ],
    imports: [
        CommonModule,
        CozinhaRoutingModule,
        SharedModule,
        BadgeModule
    ]
})
export class CozinhaModule { }
