package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Pizza;
import com.dwws.pizzaria.domain.enums.TamanhoPizza;
import com.dwws.pizzaria.domain.enums.TipoProduto;
import com.dwws.pizzaria.repository.PizzaRepository;
import com.dwws.pizzaria.service.dto.PizzaDTO;
import com.dwws.pizzaria.service.dto.PizzaListDTO;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.PizzaMapper;
import com.dwws.pizzaria.service.util.MensagemClienteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
public class PizzaService {
    private final PizzaRepository repository;
    private final PizzaMapper mapper;
    private static final String DBPEDIA_SPARQL = "https://dbpedia.org/sparql";

    private Pizza findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteUtil.ENTITY_NOT_FOUND));
    }

    public PizzaDTO findByID(Long id) {
        Pizza pizza = findEntity(id);

        PizzaDTO pizzaDTO = mapper.toDto(pizza);
        pizzaDTO.setTamanhoId(pizza.getTamanho().getId());
        pizzaDTO.setTipoProdutoId(TipoProduto.PIZZA.getId());

        return pizzaDTO;
    }

    public Page<PizzaListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public PizzaDTO save(PizzaDTO dto) {
        Pizza pizza = mapper.toEntity(dto);

        if (Objects.nonNull(dto.getTamanhoId())) {
            pizza.setTamanho(TamanhoPizza.fromId(dto.getTamanhoId()));
        }

        pizza.setTipoProduto(TipoProduto.PIZZA);

        return mapper.toDto(repository.save(pizza));
    }

    public void delete(Long id) {
        Pizza pizza = findEntity(id);
        pizza.setAtivo(Boolean.FALSE);
        repository.save(pizza);
    }

    public String getDescription(String entity) throws Exception {
        String query = String.format("""
    PREFIX dbo: <http://dbpedia.org/ontology/>
    PREFIX dbr: <http://dbpedia.org/resource/>
    SELECT ?abstract WHERE {
      dbr:%s dbo:abstract ?abstract .
      FILTER (lang(?abstract) = "en")
    } LIMIT 1
    """, entity.replace(" ", "_"));  // troca espaço por underline

        String urlStr = DBPEDIA_SPARQL + "?query=" + URLEncoder.encode(query, "UTF-8") + "&format=json";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/sparql-results+json");

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("HTTP error code: " + status);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();

        String json = sb.toString();

        Pattern pattern = Pattern.compile("\"value\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            String description = matcher.group(1);
            description = description.replace("\\n", "\n").replace("\\\"", "\"").replace("\\\\", "\\");
            description = unescapeUnicode(description);
            return description;
        }

        // Caso não encontre descrição
        return "Descrição não encontrada para '" + entity + "'.";
    }

    public static String unescapeUnicode(String escaped) {
        Properties prop = new Properties();
        try {
            prop.load(new StringReader("key=" + escaped));
        } catch (IOException e) {
            return escaped;
        }
        return prop.getProperty("key");
    }

}
