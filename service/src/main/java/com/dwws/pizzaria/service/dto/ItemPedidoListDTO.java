package com.dwws.pizzaria.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoListDTO implements Serializable {

    private Long id;
    private String nomeProduto;
    private Integer quantidade;
    private Double valorItem;
    private Double valorTotal; // quantidade * valorItem
}
