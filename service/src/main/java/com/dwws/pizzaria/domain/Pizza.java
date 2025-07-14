package com.dwws.pizzaria.domain;

import com.dwws.pizzaria.domain.enums.TamanhoPizza;
import com.dwws.pizzaria.domain.enums.TipoProduto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pizza extends Produto {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tamanho", nullable = false)
    private TamanhoPizza tamanho;

    @Column(name = "qtd_fatias", nullable = false)
    private Integer qtdFatias;

    @Builder(builderMethodName = "pizzaBuilder")
    public Pizza(Long id, String nome, String descricao, Double precoVenda, TipoProduto tipoProduto,
                 Boolean ativo, TamanhoPizza tamanho, Integer qtdFatias) {
        super(id, nome, descricao, precoVenda, tipoProduto, ativo);
        this.tamanho = tamanho;
        this.qtdFatias = qtdFatias;
    }
}
