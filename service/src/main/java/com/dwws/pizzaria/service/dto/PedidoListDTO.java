package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoListDTO implements Serializable {

    private Long id;
    private String nomeCliente;
    private String nomeAtendente;
    private StatusPedido status;
    private LocalDateTime dataHora;
    private Double valorTotal;
    private Integer quantidadeItens;

    public PedidoListDTO(Long id, String nomeAtendente, String nomeCliente, StatusPedido status, LocalDateTime dataHora) {
        this.id = id;
        this.nomeAtendente = nomeAtendente;
        this.nomeCliente = nomeCliente;
        this.status = status;
        this.dataHora = dataHora;
    }
}
