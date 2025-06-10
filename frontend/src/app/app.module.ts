import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {TopbarComponent} from "./components/topbar/topbar.component";
import {SidemenuComponent} from "./components/sidemenu/sidemenu.component";
import {SharedModule} from "./shared/shared.module";
import {ConfirmationService, MessageService} from "primeng/api";
import {MensagensConfirmacao} from "./shared/util/msg-confirmacao-dialog-util";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AuthService} from "./modules/login/auth.service";
import {LoginViewComponent} from "./modules/login/login-view/login-view.component";
import ptBr from '@angular/common/locales/pt';
import {registerLocaleData} from "@angular/common";

registerLocaleData(ptBr);

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    SidemenuComponent,
    LoginViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    BrowserAnimationsModule,
  ],
  providers: [
    MessageService, ConfirmationService, MensagensConfirmacao, AuthService, {provide: LOCALE_ID, useValue: 'pt'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
