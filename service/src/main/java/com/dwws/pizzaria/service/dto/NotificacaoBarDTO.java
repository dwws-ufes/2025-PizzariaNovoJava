package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
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
public class NotificacaoBarDTO implements Serializable {

    private Long id;

    @NotNull(message = "Pedido Não Pode Ser Nulo")
    private Long pedidoId;

    @NotNull(message = "Status Não Pode Ser Nulo")
    private StatusPedido status;

    @NotNull(message = "Data e Hora Não Podem Ser Nulas")
    private LocalDateTime dataHora;

    private Boolean ativo = true;
}

