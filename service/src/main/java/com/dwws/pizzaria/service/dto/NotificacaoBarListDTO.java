package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoBarListDTO implements Serializable {

    private Long id;
    private Long pedidoId;
    private String nomeCliente;
    private StatusPedido status;
    private LocalDateTime dataHora;
    private Boolean ativo;
}
