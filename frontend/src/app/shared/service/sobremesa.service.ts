import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page-util";
import {SobremesaListModel} from "../../model/list/sobremesa-list.model";
import {SobremesaModel} from "../../model/sobremesa.model";

@Injectable({
  providedIn: 'root'
})
export class SobremesaService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/sobremesa';

  findAll(): Observable<Page<SobremesaListModel[]>> {
    return this.http.get<Page<SobremesaListModel[]>>(this.resourceUrl);
  }

  findById(id: number): Observable<SobremesaModel> {
    return this.http.get<SobremesaModel>(this.resourceUrl + '/' + id);
  }

  save(entity: SobremesaModel): Observable<SobremesaModel> {
    return this.http.post<SobremesaModel>(this.resourceUrl, entity);
  }

  update(entity: SobremesaModel): Observable<SobremesaModel> {
    return this.http.put<SobremesaModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<SobremesaModel> {
    return this.http.delete<SobremesaModel>(this.resourceUrl + '/' + id);
  }
}
