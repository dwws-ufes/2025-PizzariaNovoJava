package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.FormaPagamento;
import com.dwws.pizzaria.service.util.MensagemPagamentoUtil;
import jakarta.validation.constraints.DecimalMin;
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
public class PagamentoDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemPagamentoUtil.NULL_PEDIDO)
    private Long pedidoId;

    @NotNull(message = MensagemPagamentoUtil.NULL_VALOR_TOTAL)
    @DecimalMin(value = "0.01", message = "Valor total deve ser maior que zero")
    private Double valorTotal;

    @NotNull(message = MensagemPagamentoUtil.NULL_FORMA_PAGAMENTO)
    private FormaPagamento formaPagamento;

    @NotNull(message = MensagemPagamentoUtil.NULL_DATA_HORA)
    private LocalDateTime dataHora;
}
