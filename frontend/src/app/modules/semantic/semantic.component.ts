import {Component, OnInit} from "@angular/core"
import {HttpClient} from "@angular/common/http"

@Component({
  selector: "app-semantic",
  templateUrl: "./semantic.component.html",
  styleUrls: ["./semantic.component.css"],
})
export class SemanticComponent implements OnInit {
  termoBusca = ""
  consultaPersonalizada = ""
  resultados: any = null
  loading = false
  erro: string | null = null

  private apiUrl = "http://localhost:8080/api/semantic"

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.consultaPersonalizada = `PREFIX schema: <http://schema.org/>
PREFIX pizzaria: <http://localhost:8081/pizzaria/>

SELECT ?pizza ?nome ?preco WHERE {
  ?pizza a pizzaria:Pizza ;
         schema:name ?nome ;
         schema:price ?preco .
}
LIMIT 10`
  }

  private handleError(error: any, defaultMessage: string): void {
    console.error(defaultMessage, error)
    this.erro = error.error?.message || defaultMessage
    this.resultados = null
    this.loading = false
  }

  executarConsultaPizzas(): void {
    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/pizzas`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar pizzas")
    })
  }

  executarConsultaPedidos(): void {
    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/pedidos`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar pedidos")
    })
  }

  executarEstatisticas(): void {
    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/estatisticas`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar estatísticas")
    })
  }

  buscarPizza(): void {
    if (!this.termoBusca.trim()) {
      this.erro = "Digite um termo para buscar"
      return
    }

    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/buscar-pizza?nome=${encodeURIComponent(this.termoBusca)}`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar pizza")
    })
  }

  executarConsultaPersonalizada(): void {
    if (!this.consultaPersonalizada.trim()) {
      this.erro = "Digite uma consulta SPARQL"
      return
    }

    this.loading = true
    this.erro = null
    const body = {query: this.consultaPersonalizada}

    this.http.post(`${this.apiUrl}/sparql`, body).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao executar consulta personalizada")
    })
  }
}
