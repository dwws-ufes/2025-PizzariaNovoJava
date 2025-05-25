package com.dwws.pizzaria.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioListDTO implements Serializable {

    private Long id;
    private String usuario;
    private String nome;
    private Long idPerfil;
    private String descPerfil;
    private Boolean ativo;

    public UsuarioListDTO(Long id, String usuario, String nome, Long idPerfil, String descPerfil, Boolean ativo) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.idPerfil = idPerfil;
        this.descPerfil = descPerfil;
        this.ativo = ativo;
    }
}
