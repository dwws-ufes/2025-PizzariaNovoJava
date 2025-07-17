package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.ProdutoService;
import com.dwws.pizzaria.service.dto.ProdutoDTO;
import com.dwws.pizzaria.service.dto.ProdutoListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> findByID(@PathVariable("idProduto") Long idProduto) {
        return new ResponseEntity<>(service.findByID(idProduto), HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<ProdutoListDTO>> findByFiltro(@RequestBody String filtro, Pageable pageable) {
        return new ResponseEntity<>(service.findByFiltro(filtro,pageable), HttpStatus.OK);
    }
}
