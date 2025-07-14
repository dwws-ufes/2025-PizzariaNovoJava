package com.dwws.pizzaria.domain;

import com.dwws.pizzaria.domain.enums.TipoBebida;
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
@Table(name = "bebida")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bebida extends Produto {

    @Column(name = "volume", nullable = false)
    private Double volume;

    @Column(name = "fabricante")
    private String fabricante;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_bebida", nullable = false)
    private TipoBebida tipoBebida;

    @Builder(builderMethodName = "bebidaBuilder")
    public Bebida(Long id, String nome, String descricao, Double precoVenda, TipoProduto tipoProduto,
                  Boolean ativo, Double volume, String fabricante, TipoBebida tipoBebida) {
        super(id, nome, descricao, precoVenda, tipoProduto, ativo);
        this.volume = volume;
        this.fabricante = fabricante;
        this.tipoBebida = tipoBebida;
    }
}
