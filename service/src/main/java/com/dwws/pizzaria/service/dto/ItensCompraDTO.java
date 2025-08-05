package com.dwws.pizzaria.service.dto;
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
public class ItensCompraDTO implements Serializable {
    private Long id;
    private String nomeProduto;
    private Double valorItem;
    private Integer quantidade;

}
