import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {PizzaPedidoViewModel} from "../../model/list/pizza-pedido-view.model";
import {SobremesaPedidoViewModel} from "../../model/list/sobremesa-pedido-view.model";
import {BebidaPedidoViewModel} from "../../model/list/bebida-pedido-view.model";

@Injectable({
    providedIn: 'root'
})
export class NotificacaoService {

    constructor(private http: HttpClient) {
    }

    resourceUrl = environment.apiUrl + '/notificacao';

    findAllCozinha(): Observable<PizzaPedidoViewModel[]> {
        return this.http.get<PizzaPedidoViewModel[]>(this.resourceUrl + '/listar/pizza');
    }

    findAllSobremesa(): Observable<SobremesaPedidoViewModel[]> {
        return this.http.get<SobremesaPedidoViewModel[]>(this.resourceUrl + '/listar/sobremesa');
    }

    findAllBar(): Observable<BebidaPedidoViewModel[]> {
        return this.http.get<BebidaPedidoViewModel[]>(this.resourceUrl + '/listar/bar');
    }

}
