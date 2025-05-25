import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioListComponent } from './usuario-list/usuario-list.component';
import {SharedModule} from "../../shared/shared.module";
import {ChangePasswordComponet} from "./cahngePassword-form/changePassword-form.component";


@NgModule({
  declarations: [
    UsuarioFormComponent,
    UsuarioListComponent,
    ChangePasswordComponet
  ],
    imports: [
        CommonModule,
        UsuarioRoutingModule,
        SharedModule
    ]
})
export class UsuarioModule { }
