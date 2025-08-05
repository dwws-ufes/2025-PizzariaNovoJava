import {Component, ViewChild} from '@angular/core';
import {SidemenuModel} from "./shared/models/sidemenu.model";
import {AuthService} from "./modules/login/auth.service";
import {ClienteFormComponent} from "./modules/cliente/cliente-form/cliente-form.component";
import {PainelGarcomComponent} from "./modules/painel-cliente/painel-garcom/painel-garcom.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  mostrarMenu: boolean = false;
  impBarCode: boolean = localStorage.getItem('impBarCode') === 'true';

  role: string | null = localStorage.getItem('roleDescription')
  public configuracaoMenuLateral: SidemenuModel = new SidemenuModel();
  @ViewChild(ClienteFormComponent) customerFormComponent: ClienteFormComponent;
  @ViewChild(PainelGarcomComponent) painelGarcomComponent: PainelGarcomComponent;

  constructor(private authService: AuthService) {

  }

  ngOnInit(){
    this.authService.mostrarMenuEmitter.subscribe(
      mostrar => this.mostrarMenu = mostrar
    );
    console.log(this.impBarCode)
  }

  onSave(): void {
    this.customerFormComponent.saveForm();
    this.onClose();
  }

  onClose(): void {
    this.customerFormComponent.formGroup.reset();
  }

  onRecarregarClientes(): void {
    if (this.painelGarcomComponent) {
      this.painelGarcomComponent.carregarClientes();
    }
  }
}
