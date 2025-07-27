import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page-util";
import {BebidaListModel} from "../../model/list/bebida-list.model";
import {BebidaModel} from "../../model/bebida.model";

@Injectable({
  providedIn: 'root'
})
export class BebidaService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/bebida';

  findAll(): Observable<Page<BebidaListModel[]>> {
    return this.http.get<Page<BebidaListModel[]>>(this.resourceUrl);
  }

  findById(id: number): Observable<BebidaModel> {
    return this.http.get<BebidaModel>(this.resourceUrl + '/' + id);
  }

  save(entity: BebidaModel): Observable<BebidaModel> {
    return this.http.post<BebidaModel>(this.resourceUrl, entity);
  }

  update(entity: BebidaModel): Observable<BebidaModel> {
    return this.http.put<BebidaModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<BebidaModel> {
    return this.http.delete<BebidaModel>(this.resourceUrl + '/' + id);
  }
}
