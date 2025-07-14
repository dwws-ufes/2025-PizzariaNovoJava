package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.service.util.MensagemClienteUtil;
import jakarta.validation.constraints.Email;
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
public class ClienteDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemClienteUtil.NULL_NAME)
    @NotEmpty(message = MensagemClienteUtil.EMPTY_NAME)
    private String nome;

    @NotNull(message = MensagemClienteUtil.NULL_TELEFONE)
    @NotEmpty(message = MensagemClienteUtil.EMPTY_TELEFONE)
    private String telefone;

    @NotNull(message = MensagemClienteUtil.NULL_EMAIL)
    @NotEmpty(message = MensagemClienteUtil.EMPTY_EMAIL)
    @Email(message = "Email deve ter formato válido")
    private String email;

    @NotNull(message = MensagemClienteUtil.NULL_CPF)
    @NotEmpty(message = MensagemClienteUtil.EMPTY_CPF)
    private String cpf;

    private Boolean ativo = true;
}
