package com.dwws.pizzaria.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
     * Consulta todos os produtos
     */
    @GetMapping("/produtos")
    public ResponseEntity<String> getAllProdutos() {
        String query = """
                    PREFIX schema: <http://schema.org/>
                
                    SELECT ?produto ?nome ?descricao ?preco ?tipo WHERE {
                        ?produto a schema:Product ;
                               schema:name ?nome ;
                               schema:description ?descricao ;
                               schema:price ?preco ;
                               schema:category ?tipo .
                    }
                    ORDER BY ?tipo ?nome
                """;

        Map<String, String> request = new HashMap<>();
        request.put("query", query);

        return executeSparqlQuery(request);
    }

    /**
     * Consulta produtos por tipo (0=pizza, 1=bebida, 2=sobremesa)
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<String> getProdutosPorTipo(@PathVariable int tipo) {
        String query = String.format("""
                    PREFIX schema: <http://schema.org/>
                
                    SELECT ?produto ?nome ?descricao ?preco WHERE {
                        ?produto a schema:Product ;
                               schema:name ?nome ;
                               schema:description ?descricao ;
                               schema:price ?preco ;
                               schema:category "%d" .
                    }
                    ORDER BY ?nome
                """, tipo);

        Map<String, String> request = new HashMap<>();
        request.put("query", query);

        return executeSparqlQuery(request);
    }

    /**
     * Busca semântica por nome de produto
     */
    @GetMapping("/buscar")
    public ResponseEntity<String> buscarProdutoPorNome(@RequestParam String nome) {
        String query = String.format("""
                    PREFIX schema: <http://schema.org/>
                
                    SELECT ?produto ?nome ?descricao ?preco ?tipo WHERE {
                        ?produto a schema:Product ;
                               schema:name ?nome ;
                               schema:description ?descricao ;
                               schema:price ?preco ;
                               schema:category ?tipo .
                        FILTER(CONTAINS(LCASE(?nome), LCASE("%s")) || 
                               CONTAINS(LCASE(?descricao), LCASE("%s")))
                    }
                    ORDER BY ?tipo ?nome
                """, nome, nome);

        Map<String, String> request = new HashMap<>();
        request.put("query", query);

        return executeSparqlQuery(request);
    }

    /**
     * Estatísticas semânticas do sistema por tipo
     */
    @GetMapping("/estatisticas")
    public ResponseEntity<String> getEstatisticasSemanticas() {
        String query = """
                    PREFIX schema: <http://schema.org/>
                
                    SELECT 
                        ?tipo
                        (COUNT(DISTINCT ?produto) AS ?quantidade)
                        (MIN(?preco) AS ?precoMinimo)
                        (MAX(?preco) AS ?precoMaximo)
                        (AVG(?preco) AS ?precoMedio)
                    WHERE {
                        ?produto a schema:Product ;
                               schema:price ?preco ;
                               schema:category ?tipo .
                    }
                    GROUP BY ?tipo
                    ORDER BY ?tipo
                """;

        Map<String, String> request = new HashMap<>();
        request.put("query", query);

        return executeSparqlQuery(request);
    }

    /**
     * Lista os tipos de produtos disponíveis
     */
    @GetMapping("/tipos")
    public ResponseEntity<String> getTiposProdutos() {
        String query = """
                    PREFIX schema: <http://schema.org/>
                
                    SELECT DISTINCT ?tipo (COUNT(?produto) AS ?quantidade) WHERE {
                        ?produto a schema:Product ;
                               schema:category ?tipo .
                    }
                    GROUP BY ?tipo
                    ORDER BY ?tipo
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
        info.put("tiposProduto", Map.of(
                "0", "Pizza",
                "1", "Bebida",
                "2", "Sobremesa"
        ));
        info.put("exemplosConsulta", new String[]{
                "/api/semantic/produtos - Todos os produtos",
                "/api/semantic/tipo/0 - Apenas pizzas",
                "/api/semantic/buscar?nome=margherita - Busca por nome",
                "/api/semantic/estatisticas - Estatísticas por tipo",
                "/api/semantic/tipos - Tipos disponíveis"
        });

        return ResponseEntity.ok(info);
    }
}
