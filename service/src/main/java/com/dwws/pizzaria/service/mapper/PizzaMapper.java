package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Pizza;
import com.dwws.pizzaria.service.dto.PizzaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PizzaMapper extends EntityMapper<PizzaDTO, Pizza> {
}

