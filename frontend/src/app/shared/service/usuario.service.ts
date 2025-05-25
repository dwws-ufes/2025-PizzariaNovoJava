import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {UsuarioListModel} from "../../model/list/usuario-list.model";
import {UsuarioModel} from "../../model/usuario.model";
import {Page} from "../util/page";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/usuario';

  findAll(): Observable<Page<UsuarioListModel[]>> {
    return this.http.get<Page<UsuarioListModel[]>>(this.resourceUrl);
  }

  findById(id: number): Observable<UsuarioModel> {
    return this.http.get<UsuarioModel>(this.resourceUrl + '/' + id);
  }

  save(entity: UsuarioModel): Observable<UsuarioModel> {
    return this.http.post<UsuarioModel>(this.resourceUrl, entity);
  }

  update(entity: UsuarioModel): Observable<UsuarioModel> {
    return this.http.put<UsuarioModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<UsuarioModel> {
    return this.http.delete<UsuarioModel>(this.resourceUrl + '/' + id);
  }

  updtSenha(entity: {id: number, senha: string}){
    return this.http.put(this.resourceUrl + '/updtSenha', entity);
  }

}
