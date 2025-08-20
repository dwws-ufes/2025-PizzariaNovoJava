import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page-util";
import {PizzaListModel} from "../../model/list/pizza-list.model";
import {PizzaModel} from "../../model/pizza.model";

@Injectable({
  providedIn: 'root'
})
export class PizzaService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/pizza';

  findAll(): Observable<Page<PizzaListModel[]>> {
    return this.http.get<Page<PizzaListModel[]>>(this.resourceUrl);
  }

  findById(id: number): Observable<PizzaModel> {
    return this.http.get<PizzaModel>(this.resourceUrl + '/' + id);
  }

  save(entity: PizzaModel): Observable<PizzaModel> {
    return this.http.post<PizzaModel>(this.resourceUrl, entity);
  }

  update(entity: PizzaModel): Observable<PizzaModel> {
    return this.http.put<PizzaModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<PizzaModel> {
    return this.http.delete<PizzaModel>(this.resourceUrl + '/' + id);
  }

  discricaoIngrediente(ingrediente: string): Observable<string> {
    return this.http.get(this.resourceUrl + '/description/' + ingrediente, {
      responseType: 'text'
    });
  }
}
