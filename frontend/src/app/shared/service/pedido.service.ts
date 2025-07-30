import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page-util";
import {PedidoListModel} from "../../model/list/pedido-list.model";
import {PedidoModel} from "../../model/pedido.model";
import {PedidoPreparodoModel} from "../../model/pedido-preparodo.model";

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/pedido';

  criarPedido(entity: PedidoModel): Observable<PedidoModel> {
    return this.http.post<PedidoModel>(this.resourceUrl, entity);
  }
  findByIdAtendente(id: number): Observable<PedidoPreparodoModel[]> {
    return this.http.get<PedidoPreparodoModel[]>(this.resourceUrl + '/lista-pedido/' + id);
  }


}
