package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.ClienteService;
import com.dwws.pizzaria.service.dto.ClienteDTO;
import com.dwws.pizzaria.service.dto.ClienteListDTO;
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
@RequestMapping("api/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> findByID(@PathVariable("idCliente") Long idCliente) {
        return new ResponseEntity<>(service.findByID(idCliente), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> delete(@PathVariable("idCliente") Long idCliente) {
        service.delete(idCliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
