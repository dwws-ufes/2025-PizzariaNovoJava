package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.service.util.MensagemItemPedidoUtil;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
public class ItemPedidoDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemItemPedidoUtil.NULL_PEDIDO)
    private Long pedidoId;

    @NotNull(message = MensagemItemPedidoUtil.NULL_PRODUTO)
    private Long produtoId;

    @NotNull(message = MensagemItemPedidoUtil.NULL_QUANTIDADE)
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    @NotNull(message = MensagemItemPedidoUtil.NULL_VALOR)
    @DecimalMin(value = "0.01", message = "Valor do item deve ser maior que zero")
    private Double valorItem;
}
