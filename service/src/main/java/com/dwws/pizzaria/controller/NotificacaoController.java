package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.service.NotificacaoService;
import com.dwws.pizzaria.service.dto.NotificacaoBarDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaDTO;
import com.dwws.pizzaria.service.dto.NotificacaoBarListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO;
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
@RequestMapping("api/notoficacao")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor

public class NotificacaoController {
    private final NotificacaoService service;

    @GetMapping("/listar/notificacaobar")
    public ResponseEntity<Page<NotificacaoCozinhaListDTO>> findAllCozinha(Pageable pageable) {
        return new ResponseEntity<>(service.findAllCozinha(pageable), HttpStatus.OK);
    }
    @GetMapping("/listar/notificacaocozinha")
    public ResponseEntity<Page<NotificacaoBarListDTO>> findAllBar(Pageable pageable) {
        return new ResponseEntity<>(service.findAllBar(pageable), HttpStatus.OK);
    }

    @GetMapping("/notificacaobar/{idNotificacao}")
    public ResponseEntity<NotificacaoBarDTO> findByBar(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.findByBar(idNotificacao), HttpStatus.OK);
    }
    @GetMapping("/notificacaocozinha/{idNotificacao}")
    public ResponseEntity<NotificacaoCozinhaDTO> findbyIdCozinha(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.findbyIdCozinha(idNotificacao), HttpStatus.OK);
    }


    @PostMapping("/salvar/notificacaobar")
    public ResponseEntity<NotificacaoBarDTO> saveBar(@RequestBody NotificacaoBarDTO dto) {
        return new ResponseEntity<>(service.saveBar(dto), HttpStatus.CREATED);
    }
    @PostMapping("/salvar/notificacaocozinha")
    public ResponseEntity<NotificacaoCozinhaDTO> saveCozinha(@RequestBody NotificacaoCozinhaDTO dto) {
        return new ResponseEntity<>(service.saveCozinha(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/notificacaocozinha/{idNotificacao}")
    public ResponseEntity<Void> deleteCozinha(@PathVariable("idNotificacao") Long idNotificacao) {
        service.deleteCozinha(idNotificacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/notificacaobar/{idNotificacao}")
    public ResponseEntity<Void> deleteBar(@PathVariable("idNotificacao") Long idNotificacao) {
        service.deleteBar(idNotificacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ==================== ROTAS DE CONTROLE DE STATUS ====================
    @GetMapping("/marcarCozinhaComoProcessada/{idNotificacao}")
    public ResponseEntity<NotificacaoCozinhaDTO> marcarCozinhaComoProcessada(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.marcarCozinhaComoProcessada(idNotificacao), HttpStatus.OK);
    }
    @GetMapping("/marcarCozinhaComoPronta/{idNotificacao}")
    public ResponseEntity<NotificacaoCozinhaDTO> marcarCozinhaComoPronta(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.marcarCozinhaComoPronta(idNotificacao), HttpStatus.OK);
    }

    @GetMapping("/marcarBarComoProcessada/{idNotificacao}")
    public ResponseEntity<NotificacaoBarDTO> marcarBarComoProcessada(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.marcarBarComoProcessada(idNotificacao), HttpStatus.OK);
    }
    @GetMapping("/marcarBarComoPronta/{idNotificacao}")
    public ResponseEntity<NotificacaoBarDTO> marcarBarComoPronta(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.marcarBarComoPronta(idNotificacao), HttpStatus.OK);
    }

    // ==================== ROTAS PARA CRIAÇÃO AUTOMÁTICA ====================

    @PostMapping("/atualizarNotificacoesPorStatus")
    public ResponseEntity<Void> atualizarNotificacoesPorStatus(@RequestBody Pedido pedido) {
        service.atualizarNotificacoesPorStatus(pedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/desativarNotificacoesPedido")
    public ResponseEntity<Void> desativarNotificacoesPedido(@RequestBody Pedido pedido) {
        service.desativarNotificacoesPedido(pedido);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
