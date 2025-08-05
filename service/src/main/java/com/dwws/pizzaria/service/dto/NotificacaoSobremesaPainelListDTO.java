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
public class NotificacaoSobremesaPainelListDTO implements Serializable {
    private Long pedidoId;
    private Long notificacaoCozinhaId;
    private String nomeSobremesa;
    private String nomeCliente;
    private String observacoes;
    private Integer statusPratoId;
    private Integer quantidade;
    private Boolean ativo;

    public NotificacaoSobremesaPainelListDTO(Long pedidoId, Long notificacaoCozinhaId, String nomeSobremesa,
                                             String nomeCliente, String observacoes, Integer statusPratoId,
                                             Integer quantidade) {
        this.pedidoId = pedidoId;
        this.notificacaoCozinhaId = notificacaoCozinhaId;
        this.nomeSobremesa = nomeSobremesa;
        this.nomeCliente = nomeCliente;
        this.observacoes = observacoes;
        this.statusPratoId = statusPratoId;
        this.quantidade = quantidade;
    }
}
