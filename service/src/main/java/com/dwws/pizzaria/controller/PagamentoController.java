package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.PagamentoService;
import com.dwws.pizzaria.service.dto.PagamentoDTO;
import com.dwws.pizzaria.service.dto.PagamentoListDTO;
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
@RequestMapping("api/pagamneto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService service;

    @GetMapping
    public ResponseEntity<Page<PagamentoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idPagamento}")
    public ResponseEntity<PagamentoDTO> findByID(@PathVariable("idPagamento") Long idPagamento) {
        return new ResponseEntity<>(service.findByID(idPagamento), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> save(@RequestBody PagamentoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idPagamento}")
    public ResponseEntity<Void> delete(@PathVariable("idPagamento") Long idPagamento) {
        service.delete(idPagamento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
