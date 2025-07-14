package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.TipoBebida;
import com.dwws.pizzaria.domain.enums.TipoProduto;
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

    public BebidaListDTO(Long id, String nome, Double precoVenda, String descricao,
                         TipoProduto tipoProduto, String fabricante,
                         TipoBebida tipoBebida, Double volume) {
        super(id, nome, precoVenda, descricao, tipoProduto.getValue());
        this.fabricante = fabricante;
        this.tipoBebida = tipoBebida.getValue();
        this.volume = volume;
    }
}
