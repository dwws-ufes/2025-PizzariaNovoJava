import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginViewComponent } from './login-view/login-view.component';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
    declarations: [

    ],
    exports: [

    ],
    imports: [
        CommonModule,
        LoginRoutingModule,
        SharedModule
    ]
})

export class LoginModule {
}
