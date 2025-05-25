import {NgModule} from '@angular/core';
import {PRIMENG_IMPORTS} from './imports/primeng.imports';
import {ANGULAR_IMPORTS} from './imports/angular.imports';
import {ComponentsModule} from "./components/components.module";

@NgModule({
    declarations: [
    ],
  imports: [
    PRIMENG_IMPORTS,
    ANGULAR_IMPORTS,
    ComponentsModule,
  ],
  providers: [],
    exports: [
        PRIMENG_IMPORTS,
        ANGULAR_IMPORTS,
        ComponentsModule,
    ]
})
export class SharedModule {
}
