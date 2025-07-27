package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.TamanhoPizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PizzaListDTO extends ProdutoListDTO implements Serializable {
    private String tamanho;
    private Integer tamanhoId;
    private Integer qtdFatias;

    public PizzaListDTO(Long id, String nome, Double precoVenda, String descricao, Integer qtdFatias,
                        TamanhoPizza tamanho) {
        super(id, nome, precoVenda, descricao);
        this.qtdFatias = qtdFatias;
        this.tamanho = tamanho.getValue();
        this.tamanhoId = tamanho.getId();
    }
}
