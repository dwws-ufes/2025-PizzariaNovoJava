package com.dwws.pizzaria.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DropdownDTO implements Serializable {

    private String label;
    private Long value;

    public DropdownDTO(String label, Long value) {
        this.label = label;
        this.value = value;
    }
}
