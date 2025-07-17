package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.ItemPedidoService;
import com.dwws.pizzaria.service.dto.ItemPedidoDTO;
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
@RequestMapping("api/itempedido")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ItemPedidoController {
    private final ItemPedidoService service;

    @GetMapping
    public ResponseEntity<Page<ItemPedidoDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idItemPedido}")
    public ResponseEntity<ItemPedidoDTO> findByID(@PathVariable("idItemPedido") Long idItemPedido) {
        return new ResponseEntity<>(service.findByID(idItemPedido), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemPedidoDTO> save(@RequestBody ItemPedidoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idItemPedido}")
    public ResponseEntity<Void> delete(@PathVariable("idItemPedido") Long idItemPedido) {
        service.delete(idItemPedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
