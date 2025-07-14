package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.service.dto.PedidoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class})
public interface PedidoMapper extends EntityMapper<PedidoDTO, Pedido> {

    @Override
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "atendente.id", target = "atendenteId")
    PedidoDTO toDto(Pedido entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "atendenteId", target = "atendente.id")
    Pedido toEntity(PedidoDTO dto);
}
