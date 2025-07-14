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
public class ProdutoListDTO implements Serializable {
    private Long id;
    private String nome;
    private String descricao;
    private Double precoVenda;
    private Boolean ativo;
    private String tipoProduto;

    public ProdutoListDTO(Long id, String nome, Double precoVenda, String descricao, String tipoProduto) {
        this.id = id;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.descricao = descricao;
        this.tipoProduto = tipoProduto;
    }
}
