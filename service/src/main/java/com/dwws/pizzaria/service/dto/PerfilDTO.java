package com.dwws.pizzaria.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO implements Serializable {

    private Long id;

    @NotNull(message = "Descrição do Perfil Não Pode Ser Nula")
    @NotEmpty(message = "Descrição do Perfil Não Pode Ser Vazia")
    private String descricao;

    private Boolean ativo = true;
}
