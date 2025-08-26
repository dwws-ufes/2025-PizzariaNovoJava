import {Component, type OnInit} from "@angular/core"
import type {HttpClient} from "@angular/common/http"

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

  executarConsultaPizzas(): void {
    this.loading = true
    this.http.get(`${this.apiUrl}/pizzas`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => {
        console.error("Erro ao buscar pizzas:", error)
        this.resultados = {erro: "Erro ao executar consulta"}
        this.loading = false
      },
    })
  }

  executarConsultaPedidos(): void {
    this.loading = true
    this.http.get(`${this.apiUrl}/pedidos`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => {
        console.error("Erro ao buscar pedidos:", error)
        this.resultados = {erro: "Erro ao executar consulta"}
        this.loading = false
      },
    })
  }

  executarEstatisticas(): void {
    this.loading = true
    this.http.get(`${this.apiUrl}/estatisticas`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => {
        console.error("Erro ao buscar estatísticas:", error)
        this.resultados = {erro: "Erro ao executar consulta"}
        this.loading = false
      },
    })
  }

  buscarPizza(): void {
    if (!this.termoBusca.trim()) {
      alert("Digite um termo para buscar")
      return
    }

    this.loading = true
    this.http.get(`${this.apiUrl}/buscar-pizza?nome=${encodeURIComponent(this.termoBusca)}`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => {
        console.error("Erro ao buscar pizza:", error)
        this.resultados = {erro: "Erro ao executar busca"}
        this.loading = false
      },
    })
  }

  executarConsultaPersonalizada(): void {
    if (!this.consultaPersonalizada.trim()) {
      alert("Digite uma consulta SPARQL")
      return
    }

    this.loading = true
    const body = {query: this.consultaPersonalizada}

    this.http.post(`${this.apiUrl}/sparql`, body).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => {
        console.error("Erro ao executar consulta personalizada:", error)
        this.resultados = {erro: "Erro ao executar consulta SPARQL"}
        this.loading = false
      },
    })
  }
}
