package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.service.util.MensagemUsuarioUtil;
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
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemUsuarioUtil.NULL_USER)
    @NotEmpty(message = MensagemUsuarioUtil.EMPTY_USER)
    private String login;

    @NotNull(message = MensagemUsuarioUtil.NULL_NAME)
    @NotEmpty(message = MensagemUsuarioUtil.EMPTY_NAME)
    private String nome;

    private String cpf;
    private String email;

    private String senha;

    private Boolean ativo = true;

    @NotNull(message = MensagemUsuarioUtil.NULL_PROFILE)
    private Integer idPerfil;
}
