package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.NotificacaoCozinha;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificacaoCozinhaMapper extends EntityMapper<NotificacaoCozinhaDTO, NotificacaoCozinha> {

    @Override
    @Mapping(source = "pedido.id", target = "pedidoId")
    NotificacaoCozinhaDTO toDto(NotificacaoCozinha entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(source = "pedidoId", target = "pedido.id")
    NotificacaoCozinha toEntity(NotificacaoCozinhaDTO dto);
}
