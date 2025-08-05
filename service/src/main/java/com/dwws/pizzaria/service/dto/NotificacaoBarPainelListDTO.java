package com.dwws.pizzaria.service.dto;

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
public class NotificacaoBarPainelListDTO implements Serializable {
    private Long pedidoId;
    private Long notificacaoCozinhaId;
    private String nomeBebida;
    private String nomeCliente;
    private String observacao;
    private Integer quantidade;
    private Integer statusPratoId;
    private LocalDateTime dataHora;
    private Boolean ativo;

    public NotificacaoBarPainelListDTO(Long pedidoId, Long notificacaoCozinhaId, String nomeBebida, String nomeCliente,
                                       String observacao, Integer statusPratoId, Integer quantidade) {
        this.pedidoId = pedidoId;
        this.notificacaoCozinhaId = notificacaoCozinhaId;
        this.nomeBebida = nomeBebida;
        this.nomeCliente = nomeCliente;
        this.observacao = observacao;
        this.statusPratoId = statusPratoId;
        this.quantidade = quantidade;
    }
}
