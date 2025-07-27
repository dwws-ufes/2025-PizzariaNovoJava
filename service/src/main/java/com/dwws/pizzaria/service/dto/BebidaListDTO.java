package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.TipoBebida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BebidaListDTO extends ProdutoListDTO implements Serializable {
    private Double volume;
    private String fabricante;
    private String tipoBebida;
    private Integer tipoBebidaId;

    public BebidaListDTO(Long id, String nome, Double precoVenda, String descricao, Double volume, String fabricante,
                         TipoBebida tipoBebida) {
        super(id, nome, precoVenda, descricao);
        this.fabricante = fabricante;
        this.tipoBebida = tipoBebida.getValue();
        this.tipoBebidaId = tipoBebida.getId();
        this.volume = volume;
    }
}
