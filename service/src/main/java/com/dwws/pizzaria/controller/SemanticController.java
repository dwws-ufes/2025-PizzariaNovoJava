package com.dwws.pizzaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/semantic")
@CrossOrigin(origins = "*")
public class SemanticController {

    private static final String ONTOP_SPARQL_ENDPOINT = "http://localhost:8081/sparql";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Endpoint para consultas SPARQL diretas
     */
    @PostMapping("/sparql")
    public ResponseEntity<String> executeSparqlQuery(@RequestBody Map<String, String> request) {
        try {
            String query = request.get("query");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Accept", "application/sparql-results+json");
            
            String body = "query=" + java.net.URLEncoder.encode(query, "UTF-8");
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                ONTOP_SPARQL_ENDPOINT, 
                HttpMethod.POST, 
                entity, 
                String.class
            );
            
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao executar consulta SPARQL: " + e.getMessage());
        }
    }

    /**
     * Consulta todas as pizzas em RDF
     */
    @GetMapping("/pizzas")
    public ResponseEntity<String> getAllPizzasRDF() {
        String query = """
            PREFIX schema: <http://schema.org/>
            PREFIX pizzaria: <http://localhost:8081/pizzaria/>
            
            SELECT ?pizza ?nome ?descricao ?preco ?tamanho ?fatias ?dbpedia WHERE {
                ?pizza a pizzaria:Pizza ;
                       schema:name ?nome ;
                       schema:description ?descricao ;
                       schema:price ?preco ;
                       pizzaria:tamanho ?tamanho ;
                       pizzaria:qtdFatias ?fatias .
                OPTIONAL { ?pizza schema:sameAs ?dbpedia }
                FILTER(?pizza != <http://localhost:8081/pizzaria/Pizza>)
            }
            ORDER BY ?nome
        """;
        
        Map<String, String> request = new HashMap<>();
        request.put("query", query);
        
        return executeSparqlQuery(request);
    }

    /**
     * Consulta pedidos com detalhes semânticos
     */
    @GetMapping("/pedidos")
    public ResponseEntity<String> getPedidosSemanticos() {
        String query = """
            PREFIX schema: <http://schema.org/>
            PREFIX pizzaria: <http://localhost:8081/pizzaria/>
            
            SELECT ?pedido ?cliente ?clienteNome ?dataHora ?status ?produto ?produtoNome ?quantidade WHERE {
                ?pedido a pizzaria:Pedido ;
                        schema:customer ?cliente ;
                        schema:orderDate ?dataHora ;
                        schema:orderStatus ?status .
                
                ?cliente schema:name ?clienteNome .
                
                ?item schema:orderItemFor ?pedido ;
                      schema:orderedItem ?produto ;
                      schema:orderQuantity ?quantidade .
                
                ?produto schema:name ?produtoNome .
            }
            ORDER BY DESC(?dataHora)
        """;
        
        Map<String, String> request = new HashMap<>();
        request.put("query", query);
        
        return executeSparqlQuery(request);
    }

    /**
     * Busca semântica por ingredientes/nome de pizza
     */
    @GetMapping("/buscar-pizza")
    public ResponseEntity<String> buscarPizzaPorNome(@RequestParam String nome) {
        String query = String.format("""
            PREFIX schema: <http://schema.org/>
            PREFIX pizzaria: <http://localhost:8081/pizzaria/>
            
            SELECT ?pizza ?nome ?descricao ?preco ?tamanho ?fatias ?dbpedia WHERE {
                ?pizza a pizzaria:Pizza ;
                       schema:name ?nome ;
                       schema:description ?descricao ;
                       schema:price ?preco ;
                       pizzaria:tamanho ?tamanho ;
                       pizzaria:qtdFatias ?fatias .
                OPTIONAL { ?pizza schema:sameAs ?dbpedia }
                FILTER(CONTAINS(LCASE(?nome), LCASE("%s")) || CONTAINS(LCASE(?descricao), LCASE("%s")))
            }
            ORDER BY ?nome
        """, nome, nome);
        
        Map<String, String> request = new HashMap<>();
        request.put("query", query);
        
        return executeSparqlQuery(request);
    }

    /**
     * Estatísticas semânticas do sistema
     */
    @GetMapping("/estatisticas")
    public ResponseEntity<String> getEstatisticasSemanticas() {
        String query = """
            PREFIX schema: <http://schema.org/>
            PREFIX pizzaria: <http://localhost:8081/pizzaria/>
            
            SELECT 
                (COUNT(DISTINCT ?pizza) AS ?totalPizzas)
                (COUNT(DISTINCT ?bebida) AS ?totalBebidas)
                (COUNT(DISTINCT ?cliente) AS ?totalClientes)
                (COUNT(DISTINCT ?pedido) AS ?totalPedidos)
            WHERE {
                OPTIONAL { ?pizza a pizzaria:Pizza }
                OPTIONAL { ?bebida a pizzaria:Bebida }
                OPTIONAL { ?cliente a pizzaria:Cliente }
                OPTIONAL { ?pedido a pizzaria:Pedido }
            }
        """;
        
        Map<String, String> request = new HashMap<>();
        request.put("query", query);
        
        return executeSparqlQuery(request);
    }

    /**
     * Informações sobre o endpoint SPARQL
     */
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getSemanticInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("sparqlEndpoint", ONTOP_SPARQL_ENDPOINT);
        info.put("status", "ativo");
        info.put("descricao", "Backend semântico da Pizzaria com Ontop");
        info.put("vocabularios", new String[]{"schema.org", "DBpedia", "pizzaria"});
        info.put("exemplosConsulta", new String[]{
            "/api/semantic/pizzas - Todas as pizzas em RDF",
            "/api/semantic/pedidos - Pedidos com detalhes semânticos",
            "/api/semantic/buscar-pizza?nome=margherita - Busca por nome",
            "/api/semantic/estatisticas - Estatísticas do sistema"
        });
        
        return ResponseEntity.ok(info);
    }
}
