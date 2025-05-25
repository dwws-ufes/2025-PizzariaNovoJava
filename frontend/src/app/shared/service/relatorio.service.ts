import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {RelatorioEntreDatasModel} from "../../model/report/relatorio-entre-datas.model";

@Injectable({
  providedIn: 'root'
})
export class RelatorioService {

  constructor(private http: HttpClient) {
  }

  resourceUrl = environment.apiUrl + '/relatorio';

  balanceReportProductInStock(): Observable<any> {
    return this.http.get(`${this.resourceUrl}/relatorio-estoque-produtos`, {responseType: 'arraybuffer'});
  }

  pointOfOrderProductReport(): Observable<any> {
    return this.http.get(`${this.resourceUrl}/relatorio-ponto-encomenda-produtos`, {responseType: 'arraybuffer'});
  }

  reportMostConsumedBeersInAPeriod(report: RelatorioEntreDatasModel): Observable<any> {
    return this.http.get(
      `${this.resourceUrl}/relatorio-chopes-populares?dataInicio=${report.dataInicial}&dataFinal=${report.dataFinal}`,
      {responseType: 'arraybuffer'});
  }

  customerReportWithAmountPurchasedInPeriod(report: RelatorioEntreDatasModel): Observable<any> {
    return this.http.get(
      `${this.resourceUrl}/relatorio-total-compras-clientes?dataInicio=${report.dataInicial}&dataFinal=${report.dataFinal}`,
      {responseType: 'arraybuffer'});
  }
  expenseAndIncomeMovementReport(report: RelatorioEntreDatasModel): Observable<any> {
    return this.http.get(
      `${this.resourceUrl}//relatorio-receita-despesas?dataInicio=${report.dataInicial}&dataFinal=${report.dataFinal}`,
      {responseType: 'arraybuffer'});
  }

}
