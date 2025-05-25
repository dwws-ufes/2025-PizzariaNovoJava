package com.dwws.pizzaria.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusPrato {
    PRONTO(0, "Pronto"),
    PENDENTE(1, "Pendente"),
    EM_PREPARO(2, "Em Preparo");

    private final Integer id;
    private final String value;
}
