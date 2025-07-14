package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.TipoBebida;
import com.dwws.pizzaria.service.util.MensagemProdutoUtil;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BebidaDTO implements Serializable {

    private Long id;

    @NotNull(message = MensagemProdutoUtil.NULL_NAME)
    @NotEmpty(message = MensagemProdutoUtil.EMPTY_NAME)
    private String nome;

    @NotNull(message = MensagemProdutoUtil.NULL_DESCRICAO)
    @NotEmpty(message = MensagemProdutoUtil.EMPTY_DESCRICAO)
    private String descricao;

    @NotNull(message = MensagemProdutoUtil.NULL_PRECO)
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private Double precoVenda;

    private Boolean ativo = true;

    @NotNull(message = "Volume Não Pode Ser Nulo")
    @DecimalMin(value = "0.01", message = "Volume deve ser maior que zero")
    private Double volume;

    private String fabricante;

    @NotNull(message = "Tipo de Bebida Não Pode Ser Nulo")
    private TipoBebida tipoBebida;
}

