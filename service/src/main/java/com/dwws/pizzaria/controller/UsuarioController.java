package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.UsuarioService;
import com.dwws.pizzaria.service.dto.UserPasswordChangeDTO;
import com.dwws.pizzaria.service.dto.UsuarioDTO;
import com.dwws.pizzaria.service.dto.UsuarioListDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<UsuarioListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> findByID(@PathVariable("idUsuario") Long idUsuario) {
        return new ResponseEntity<>(service.findByID(idUsuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") Long idUsuario) {
        service.delete(idUsuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updtSenha")
    public ResponseEntity<Void> updtSenha(@RequestBody UserPasswordChangeDTO userPasswordChangeDTO) {
        service.updtPassword(userPasswordChangeDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
