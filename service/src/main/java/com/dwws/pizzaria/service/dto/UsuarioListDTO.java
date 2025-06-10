package com.dwws.pizzaria.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioListDTO implements Serializable {
    private Long id;
    private String login;
    private String cpf;
    private String nome;
    private Long idPerfil;
    private String descPerfil;
    private Boolean ativo;
}
