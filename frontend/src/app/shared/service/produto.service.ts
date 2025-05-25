import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Page} from "../util/page";
import {InsumoListModel} from "../../model/list/insumo-list.model";
import {ProdutoModel} from "../../model/produto.model";
import {ProdutoEstoqueReportModel} from "../../model/report/produto-estoque-report.model";
import {ProdutoPontoEncomendaReportModel} from "../../model/report/produto-ponto-encomenda-report.model";
import {RelatorioEntreDatasModel} from "../../model/report/relatorio-entre-datas.model";
import {ChopesPopularesReportModel} from "../../model/report/chopes-populares-report.model";

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }

  resourceUrl = environment.apiUrl + '/produto';

  listAllInputs(): Observable<Page<InsumoListModel[]>> {
    return this.http.get<Page<InsumoListModel[]>>(this.resourceUrl + '/listar-insumos');
  }

  findById(id: number): Observable<ProdutoModel> {
    return this.http.get<ProdutoModel>(this.resourceUrl + '/' + id);
  }

  findInputByBarCode(barCode: string): Observable<InsumoListModel> {
    return this.http.get<InsumoListModel>(this.resourceUrl + '/buscar-cod-barras/' + barCode);
  }

  save(entity: ProdutoModel): Observable<ProdutoModel> {
    return this.http.post<ProdutoModel>(this.resourceUrl, entity);
  }

  delete(id: number): Observable<ProdutoModel> {
    return this.http.delete<ProdutoModel>(this.resourceUrl + '/' + id);
  }

  enterProductInput(inputs: { id: number, qtdeEstoque: number }[]): Observable<ProdutoModel[]> {
    return this.http.put<any>(this.resourceUrl + '/entrada-produto', inputs);
  }

  releaseProductOutput(inputs: { id: number, qtdeEstoque: number }[]): Observable<ProdutoModel[]> {
    return this.http.put<any>(this.resourceUrl + '/saida-produto', inputs);
  }

  findSelfServiceProduct(): Observable<ProdutoModel> {
    return this.http.get<ProdutoModel>(this.resourceUrl + '/buscar-self-service');
  }

  getWeightValue(): Observable<any> {
    return this.http.get("http://192.168.1.150/peso");
  }

  balanceReportProductInStock(): Observable<ProdutoEstoqueReportModel[]> {
    return this.http.post<ProdutoEstoqueReportModel[]>(this.resourceUrl + '/relatorio-estoque-produtos', null);
  }

  pointOfOrderProductReport(): Observable<ProdutoPontoEncomendaReportModel[]> {
    return this.http.post<ProdutoPontoEncomendaReportModel[]>(this.resourceUrl + '/relatorio-ponto-encomenda-produtos', null);
  }

  reportMostConsumedBeersInAPeriod(report: RelatorioEntreDatasModel): Observable<ChopesPopularesReportModel[]> {
    return this.http.post<ChopesPopularesReportModel[]>(this.resourceUrl + '/relatorio-chopes-populares', report);
  }

}
