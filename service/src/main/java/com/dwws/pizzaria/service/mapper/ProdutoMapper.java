package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.service.dto.ProdutoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper extends EntityMapper<ProdutoDTO, Produto> {
}
