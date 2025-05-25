import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {ClienteListModel} from "../../model/list/cliente-list.model";
import {ClienteModel} from "../../model/cliente.model";
import {Page} from "../util/page";
import {CaixaModel} from "../../model/caixa-model";

@Injectable({
  providedIn: 'root'
})
export class CaixaService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/caixa';

  findById(id: number): Observable<CaixaModel> {
    return this.http.get<CaixaModel>(this.resourceUrl + '/' + id);
  }

  insert(entity: CaixaModel): Observable<CaixaModel> {
    return this.http.post<CaixaModel>(this.resourceUrl, entity);
  }

  update(entity: CaixaModel): Observable<CaixaModel> {
    return this.http.put<CaixaModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<CaixaModel> {
    return this.http.delete<CaixaModel>(this.resourceUrl + '/' + id);
  }

  liberarCartao(entity: CaixaModel): Observable<CaixaModel>{
    return  this.http.put<CaixaModel>(this.resourceUrl + 'liberar-cartao', entity);
  }

}
