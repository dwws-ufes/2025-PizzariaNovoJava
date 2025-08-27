import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http"

@Component({
  selector: 'app-produto-onto',
  templateUrl: './produto-onto.component.html',
  styleUrls: ['./produto-onto.component.css']
})
export class ProdutoOntoComponent implements OnInit {
  termoBusca = ""
  consultaPersonalizada = ""
  resultados: any = null
  loading = false
  erro: string | null = null

  tiposProduto = [
    {id: 0, nome: 'Pizza'},
    {id: 1, nome: 'Bebida'},
    {id: 2, nome: 'Sobremesa'}
  ];

  tipoSelecionado: number | null = null;

  private apiUrl = "http://localhost:8080/api/semantic"

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.consultaPersonalizada = `PREFIX schema: <http://schema.org/>
SELECT ?produto ?nome ?descricao ?preco ?tipo WHERE {
  ?produto a schema:Product ;
         schema:name ?nome ;
         schema:description ?descricao ;
         schema:price ?preco ;
         schema:category ?tipo .
}
LIMIT 10`;
  }

  private handleError(error: any, defaultMessage: string): void {
    console.error(defaultMessage, error)
    this.erro = error.error?.message || defaultMessage
    this.resultados = null
    this.loading = false
  }

  // Método para buscar todos os produtos (substitui o de pizzas)
  executarConsultaProdutos(): void {
    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/produtos`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar produtos")
    })
  }

  // Método para consultar por tipo
  consultarPorTipo(tipo: number): void {
    this.loading = true;
    this.erro = null;
    this.http.get(`${this.apiUrl}/tipo/${tipo}`).subscribe({
      next: (data) => {
        this.resultados = data;
        this.loading = false;
      },
      error: (error) => this.handleError(error, "Erro ao buscar por tipo")
    });
  }

  // Método para listar tipos
  listarTipos(): void {
    this.loading = true;
    this.erro = null;
    this.http.get(`${this.apiUrl}/tipos`).subscribe({
      next: (data) => {
        this.resultados = data;
        this.loading = false;
      },
      error: (error) => this.handleError(error, "Erro ao listar tipos")
    });
  }

  // Método para buscar produto por nome (substitui o buscarPizza)
  buscarProduto(): void {
    if (!this.termoBusca.trim()) {
      this.erro = "Digite um termo para buscar"
      return
    }

    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/buscar?nome=${encodeURIComponent(this.termoBusca)}`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar produto")
    })
  }

  // Método para estatísticas
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

  // Método para informações do endpoint
  getInfo(): void {
    this.loading = true
    this.erro = null
    this.http.get(`${this.apiUrl}/info`).subscribe({
      next: (data) => {
        this.resultados = data
        this.loading = false
      },
      error: (error) => this.handleError(error, "Erro ao buscar informações")
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
