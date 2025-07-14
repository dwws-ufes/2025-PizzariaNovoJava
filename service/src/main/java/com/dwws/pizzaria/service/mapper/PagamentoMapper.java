package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Pagamento;
import com.dwws.pizzaria.service.dto.PagamentoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagamentoMapper extends EntityMapper<PagamentoDTO, Pagamento> {

    @Override
    @Mapping(source = "pedido.id", target = "pedidoId")
    PagamentoDTO toDto(Pagamento entity);

    @Override
    @InheritInverseConfiguration
    @Mapping(source = "pedidoId", target = "pedido.id")
    Pagamento toEntity(PagamentoDTO dto);
}
