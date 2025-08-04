import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {ClienteListModel} from "../../model/list/cliente-list.model";
import {ClienteModel} from "../../model/cliente.model";
import {Page} from "../util/page-util";

@Injectable({
    providedIn: 'root'
})
export class ClienteService {

    constructor(private http: HttpClient) {
    }

    resourceUrl = environment.apiUrl + '/cliente';

    findAll(): Observable<Page<ClienteListModel[]>> {
        return this.http.get<Page<ClienteListModel[]>>(this.resourceUrl);
    }

    findById(id: number): Observable<ClienteModel> {
        return this.http.get<ClienteModel>(this.resourceUrl + '/' + id);
    }

    save(entity: ClienteModel): Observable<ClienteModel> {
        return this.http.post<ClienteModel>(this.resourceUrl, entity);
    }

    update(entity: ClienteModel): Observable<ClienteModel> {
        return this.http.put<ClienteModel>(this.resourceUrl, entity);
    }

    delete(id: number): Observable<ClienteModel> {
        return this.http.delete<ClienteModel>(this.resourceUrl + '/' + id);
    }

  buscarPorTermo(term: string): Observable<ClienteListModel[]> {
    return this.http.get<ClienteListModel[]>(`${this.resourceUrl}/buscar?termo=${term}`);
  }
}
