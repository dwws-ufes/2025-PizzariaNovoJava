package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.SobremesaService;
import com.dwws.pizzaria.service.dto.ProdutoDTO;
import com.dwws.pizzaria.service.dto.ProdutoListDTO;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sobremesa")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class SobremesaController {
    private final SobremesaService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idBebida}")
    public ResponseEntity<ProdutoDTO> findByID(@PathVariable("idBebida") Long idBebida) {
        return new ResponseEntity<>(service.findByID(idBebida), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idBebida}")
    public ResponseEntity<Void> delete(@PathVariable("idBebida") Long idBebida) {
        service.delete(idBebida);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
