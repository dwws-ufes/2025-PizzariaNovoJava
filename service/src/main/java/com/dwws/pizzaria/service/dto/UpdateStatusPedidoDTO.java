package com.dwws.pizzaria.service.dto;

import com.dwws.pizzaria.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusPedidoDTO implements Serializable {

    private Long idPedido;

    private StatusPedido statusPedido;
}
