package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Cliente;
import com.dwws.pizzaria.service.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {
}

