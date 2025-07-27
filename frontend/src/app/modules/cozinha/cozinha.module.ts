import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CozinhaRoutingModule } from './cozinha-routing.module';
import { CozinhaComponent } from './cozinha/cozinha.component';
import {SharedModule} from "../../shared/shared.module";


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
        SharedModule
    ]
})
export class CozinhaModule { }
