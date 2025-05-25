import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {NotificacaoCozinhaListModel} from "../../model/list/notificacao-cozinha-list.model";
import {NotificacaoCozinhaModel} from "../../model/notificacao-cozinha.model";

@Injectable({
  providedIn: 'root'
})
export class CozinhaService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/cozinha';

  findAll(): Observable<NotificacaoCozinhaListModel[]> {
    return this.http.get<NotificacaoCozinhaListModel[]>(this.resourceUrl);
  }

  findById(id: number): Observable<NotificacaoCozinhaModel> {
    return this.http.get<NotificacaoCozinhaModel>(this.resourceUrl + '/' + id);
  }

  delete(id: number): Observable<NotificacaoCozinhaModel> {
    return this.http.delete<NotificacaoCozinhaModel>(this.resourceUrl + '/' + id);
  }

  needToReplacePlate(description: string): Observable<void> {
    return this.http.post<void>(this.resourceUrl + '/solicitar-reposicao', description)
  }

  replacePlate(id: number): Observable<void> {
    return this.http.post<void>(this.resourceUrl + '/repor-prato', id)
  }

  save(entity: NotificacaoCozinhaModel): Observable<NotificacaoCozinhaModel> {
    return this.http.post<NotificacaoCozinhaModel>(this.resourceUrl, entity);
  }

  update(entity: NotificacaoCozinhaModel): Observable<NotificacaoCozinhaModel> {
    return this.http.put<NotificacaoCozinhaModel>(this.resourceUrl, entity);
  }

}
