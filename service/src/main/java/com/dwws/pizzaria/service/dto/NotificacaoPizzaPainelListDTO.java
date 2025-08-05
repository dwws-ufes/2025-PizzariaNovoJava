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
public class NotificacaoPizzaPainelListDTO implements Serializable {
    private Long pedidoId;
    private Long notificacaoCozinhaId;
    private String nomePizza;
    private String nomeCliente;
    private Integer tamanho;
    private Integer qtdFatias;
    private String observacao;
    private Integer statusPratoId;
    private Integer quantidade;
    private LocalDateTime dataHora;
    private Boolean ativo;

    public NotificacaoPizzaPainelListDTO(Long pedidoId, Long notificacaoCozinhaId,
                                         String nomePizza, String nomeCliente, Integer tamanho, Integer qtdFatias,
                                         String observacao, Integer statusPratoId, Integer quantidade) {

        this.pedidoId = pedidoId;
        this.notificacaoCozinhaId = notificacaoCozinhaId;
        this.nomePizza = nomePizza;
        this.nomeCliente = nomeCliente;
        this.tamanho = tamanho;
        this.qtdFatias = qtdFatias;
        this.observacao = observacao;
        this.statusPratoId = statusPratoId;
        this.quantidade = quantidade;
    }
}
