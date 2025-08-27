package com.dwws.pizzaria.controller;
import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.service.PizzaService;
import com.dwws.pizzaria.service.ProdutoService;
import com.dwws.pizzaria.service.dto.PizzaDTO;
import com.dwws.pizzaria.service.dto.PizzaListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    @GetMapping("/{idProduto}")
    public ResponseEntity<Produto> findByID(@PathVariable("idProduto") Long idProduto) {
        return new ResponseEntity<>(service.findEntity(idProduto), HttpStatus.OK);
    }


}
