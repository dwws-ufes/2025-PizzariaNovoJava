import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {DropdownModel} from "../models/dropdown.model";

@Injectable({
  providedIn: 'root'
})
export class PagamentoCaixaService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/pagamento-caixa';

  findAllClientes(): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(this.resourceUrl + '/clientes');
  }
}
