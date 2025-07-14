package com.dwws.pizzaria.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoBebida {
    REFRIGERANTE(0, "Refrigerante"),
    SUCO(1, "Suco"),
    AGUA(2, "Água"),
    CERVEJA(3, "Cerveja"),
    VINHO(4, "Vinho"),
    DRINK(5, "Drink"),
    ENERGETICO(6, "Energético");

    private final Integer id;
    private final String value;
}
