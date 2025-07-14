package com.dwws.pizzaria.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {
    PENDENTE(0, "Pendente"),
    EM_PREPARO(1, "Em Preparo"),
    PRONTO(2, "Pronto"),
    ENTREGUE(3, "Entregue"),
    CANCELADO(4, "Cancelado");

    private final Integer id;
    private final String value;
}
