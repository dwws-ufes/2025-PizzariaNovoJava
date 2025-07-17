package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.PedidoService;
import com.dwws.pizzaria.service.dto.PedidoDTO;
import com.dwws.pizzaria.service.dto.PedidoListDTO;
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
@RequestMapping("api/pedido")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoDTO> findByID(@PathVariable("idPedido") Long idPedido) {
        return new ResponseEntity<>(service.findByID(idPedido), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> save(@RequestBody PedidoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> delete(@PathVariable("idPedido") Long idPedido) {
        service.delete(idPedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
