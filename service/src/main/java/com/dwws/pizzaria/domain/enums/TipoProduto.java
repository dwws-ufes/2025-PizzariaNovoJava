package com.dwws.pizzaria.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoProduto {
    PIZZA(0, "Pizza"),
    BEBIDA(1, "Bebida"),
    SOBREMESA(2, "Sobremesa");

    private final Integer id;
    private final String value;
}
