package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.ItemPedido;
import com.dwws.pizzaria.service.dto.ItemPedidoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper extends EntityMapper<ItemPedidoDTO, ItemPedido> {

    @Override
    @Mapping(source = "pedido.id", target = "pedidoId")
    @Mapping(source = "produto.id", target = "produtoId")
    ItemPedidoDTO toDto(ItemPedido entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(source = "pedidoId", target = "pedido.id")
    @Mapping(source = "produtoId", target = "produto.id")
    ItemPedido toEntity(ItemPedidoDTO dto);
}
