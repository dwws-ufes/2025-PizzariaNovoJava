package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Bebida;
import com.dwws.pizzaria.service.dto.BebidaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BebidaMapper extends EntityMapper<BebidaDTO, Bebida> {
}
