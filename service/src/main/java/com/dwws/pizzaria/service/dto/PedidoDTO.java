package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.StatusPedido;
import com.dwws.pizzaria.service.util.MensagemPedidoUtil;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemPedidoUtil.NULL_CLIENTE)
    private Long clienteId;

    private Long atendenteId;

    @NotNull(message = MensagemPedidoUtil.NULL_STATUS)
    private StatusPedido status;

    @NotNull(message = MensagemPedidoUtil.NULL_DATA_HORA)
    private LocalDateTime dataHora;

    private String observacoes;

    private List<ItemPedidoDTO> itens;
}
