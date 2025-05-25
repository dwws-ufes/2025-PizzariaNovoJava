package com.dwws.pizzaria.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FormaPagamento {
    DINHEIRO(0, "Dinheiro"),
    PIX(1, "Pix"),
    DEBITO(2, "Cartão Débito"),
    CREDITO(3, "Cartão Crédito");

    private final Integer id;
    private final String value;
}
