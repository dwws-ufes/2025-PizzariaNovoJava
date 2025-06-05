package com.dwws.pizzaria.controller;

import com.dwws.pizzaria.service.PerfilService;
import com.dwws.pizzaria.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/perfil")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PerfilController {
    private final PerfilService service;

    @GetMapping("/select-perfil")
    public ResponseEntity<List<DropdownDTO>> fillProfileDropdown() {
        return new ResponseEntity<>(service.fillProfileDropdown(), HttpStatus.OK);
    }

}
