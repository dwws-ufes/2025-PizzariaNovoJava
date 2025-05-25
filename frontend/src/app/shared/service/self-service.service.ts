import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page";
import {SelfServicePratoListModel} from "../../model/list/self-service-prato-list.model";
import {SelfServicePratoModel} from "../../model/self-service-prato.model";

@Injectable({
  providedIn: 'root'
})
export class SelfServiceService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/prato-produto';

  findAll(): Observable<Page<SelfServicePratoListModel[]>> {
    return this.http.get<Page<SelfServicePratoListModel[]>>(this.resourceUrl);
  }

  findById(id: number): Observable<SelfServicePratoModel> {
    return this.http.get<SelfServicePratoModel>(this.resourceUrl + '/' + id);
  }

  save(entity: SelfServicePratoModel): Observable<SelfServicePratoModel> {
    return this.http.post<SelfServicePratoModel>(this.resourceUrl, entity);
  }

  update(entity: SelfServicePratoModel): Observable<SelfServicePratoModel> {
    return this.http.put<SelfServicePratoModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<SelfServicePratoModel> {
    return this.http.delete<SelfServicePratoModel>(this.resourceUrl + '/' + id);
  }

}
