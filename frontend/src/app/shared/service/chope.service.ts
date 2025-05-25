import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page";
import {ChopeListModel} from "../../model/list/chope-list.model";
import {ChopeViewModel} from "../../model/list/chope-view.model";
import {ChopeModel} from "../../model/chope.model";

@Injectable({
  providedIn: 'root'
})
export class ChopeService {

  constructor(private http: HttpClient) { }

  resourceUrl = environment.apiUrl + '/chope';

  findDraftBeerByRFID(rfid: string): Observable<ChopeListModel> {
    return this.http.get<ChopeListModel>(this.resourceUrl + '/buscar-chope/' + rfid);
  }

  findAll(): Observable<Page<ChopeListModel[]>> {
    return this.http.get<Page<ChopeListModel[]>>(this.resourceUrl);
  }

  listAllDraftBeers(): Observable<ChopeViewModel[]> {
    return this.http.get<ChopeViewModel[]>(this.resourceUrl + '/listar-para-cliente');
  }

  enterProductDraftBeers(draftBeers: ChopeModel[]): Observable<ChopeModel[]> {
    return this.http.put<ChopeModel[]>(this.resourceUrl + '/entrada-produto', draftBeers);
  }

  releaseProductDraftBeers(id: number): Observable<void> {
    return this.http.put<void>(this.resourceUrl + '/saida-produto/' + id, id);
  }

  removalBeerMugByTheCustomer(draftBeers: ChopeModel): Observable<void> {
    return this.http.put<void>(this.resourceUrl + '/cliente-chope-saida', draftBeers);
  }
}
