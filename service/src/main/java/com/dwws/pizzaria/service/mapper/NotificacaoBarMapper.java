package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.NotificacaoBar;
import com.dwws.pizzaria.service.dto.NotificacaoBarDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificacaoBarMapper extends EntityMapper<NotificacaoBarDTO, NotificacaoBar> {

    @Override
    @Mapping(source = "pedido.id", target = "pedidoId")
    NotificacaoBarDTO toDto(NotificacaoBar entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(source = "pedidoId", target = "pedido.id")
    NotificacaoBar toEntity(NotificacaoBarDTO dto);
}
