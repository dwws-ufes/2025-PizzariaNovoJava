import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {ClienteCompraProdutoModel} from "../../model/cliente-compra-produto.model";
import {ClienteCaixaList} from "../../model/list/cliente-caixa-list";
import {RelatorioEntreDatasModel} from "../../model/report/relatorio-entre-datas.model";

@Injectable({
  providedIn: 'root'
})
export class ClienteCompraService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/cliente-compra';

  listPurchasedItemsOfCustomer(cardRFID: string): Observable<ClienteCaixaList[]> {
    return this.http.get<ClienteCaixaList[]>(this.resourceUrl + '/compras-cliente/' + cardRFID);
  }

  // findById(id: number): Observable<ClienteCompraProdutoModel> {
  //   return this.http.get<ClienteCompraProdutoModel>(this.resourceUrl + '/' + id);
  // }
  //
  // delete(id: number): Observable<ClienteCompraProdutoModel> {
  //   return this.http.delete<ClienteCompraProdutoModel>(this.resourceUrl + '/' + id);
  // }
  //
  // findByRFID(cartaoRFID: string): Observable<ClienteCompraModelList[]> {
  //   return this.http.get<ClienteCompraModelList[]>(this.resourceUrl + '/compras-cliente/' + cartaoRFID);
  // }

  save(entity: ClienteCompraProdutoModel): Observable<ClienteCompraProdutoModel> {
    return this.http.post<ClienteCompraProdutoModel>(this.resourceUrl, entity);
  }

  sendEmail(report: RelatorioEntreDatasModel): Observable<void> {
    return this.http.post<void>(this.resourceUrl + '/relatorio/enviar-email', report);
  }

}
