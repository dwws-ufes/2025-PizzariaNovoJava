import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {DropdownModel} from "../models/dropdown.model";
import {ItemPedidoListModel} from "../../model/list/item-pedido-list.model";

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
  realizarPagamento(pagemneto:ItemPedidoListModel ): Observable<ItemPedidoListModel> {
  return this.http.post<ItemPedidoListModel>(this.resourceUrl, pagemneto);
  }

}
