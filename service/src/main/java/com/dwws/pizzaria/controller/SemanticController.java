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
     * Consulta todas as pizzas em RDF (VERSÃO CORRIGIDA)
     */
    @GetMapping("/pizzas")
    public ResponseEntity<String> getAllPizzasRDF() {
        String query = """
                    PREFIX schema: <http://schema.org/>
                    PREFIX ex: <http://example.org/pizza#>
                
                    SELECT ?pizza ?nome ?descricao WHERE {
                        ?pizza a schema:Pizza ;
                               schema:name ?nome ;
                               schema:description ?descricao .
                    }
                    ORDER BY ?nome
                """;

        Map<String, String> request = new HashMap<>();
        request.put("query", query);

        return executeSparqlQuery(request);
    }

    /**
     * Busca semântica por nome de pizza (VERSÃO CORRIGIDA)
     */
    @GetMapping("/buscar-pizza")
    public ResponseEntity<String> buscarPizzaPorNome(@RequestParam String nome) {
        String query = String.format("""
                    PREFIX schema: <http://schema.org/>
                
                    SELECT ?pizza ?nome ?descricao WHERE {
                        ?pizza a schema:Pizza ;
                               schema:name ?nome ;
                               schema:description ?descricao .
                        FILTER(CONTAINS(LCASE(?nome), LCASE("%s")) || CONTAINS(LCASE(?descricao), LCASE("%s")))
                    }
                    ORDER BY ?nome
                """, nome, nome);

        Map<String, String> request = new HashMap<>();
        request.put("query", query);

        return executeSparqlQuery(request);
    }

    /**
     * Estatísticas semânticas do sistema (VERSÃO SIMPLIFICADA)
     */
    @GetMapping("/estatisticas")
    public ResponseEntity<String> getEstatisticasSemanticas() {
        String query = """
                    PREFIX schema: <http://schema.org/>
                
                    SELECT (COUNT(DISTINCT ?pizza) AS ?totalPizzas)
                    WHERE {
                        ?pizza a schema:Pizza .
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
        info.put("vocabularios", new String[]{"schema.org", "ex (pizzaria)"});
        info.put("exemplosConsulta", new String[]{
                "/api/semantic/pizzas - Todas as pizzas em RDF",
                "/api/semantic/buscar-pizza?nome=margherita - Busca por nome",
                "/api/semantic/estatisticas - Estatísticas do sistema"
        });

        return ResponseEntity.ok(info);
    }
}
