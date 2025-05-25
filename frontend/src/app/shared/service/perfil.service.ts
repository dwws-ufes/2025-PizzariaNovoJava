import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {SelectItem} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/perfil';

  fillProfileDropdown(): Observable<SelectItem[]> {
    return this.http.get<SelectItem[]>(this.resourceUrl + '/select-perfil')
  }
}
