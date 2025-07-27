package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.PizzaService;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/pizza")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService service;

    @GetMapping
    public ResponseEntity<Page<PizzaListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaDTO> findByID(@PathVariable("idPizza") Long idPizza) {
        return new ResponseEntity<>(service.findByID(idPizza), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PizzaDTO> save(@RequestBody PizzaDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable("idPizza") Long idPizza) {
        service.delete(idPizza);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
