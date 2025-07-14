package com.dwws.pizzaria.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TamanhoPizza {
    P(0, "Pequena"),
    M(1, "Média"),
    G(2, "Grande"),
    GG(3, "Família"),
    FATIA(4, "Fatia");

    private final Integer id;
    private final String value;
}
