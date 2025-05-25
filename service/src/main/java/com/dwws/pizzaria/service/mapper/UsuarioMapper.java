package com.dwws.pizzaria.service.mapper;

import com.dwws.pizzaria.domain.Usuario;
import com.dwws.pizzaria.service.dto.UsuarioDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
    @Override
    @Mapping(source = "perfil.id", target = "idPerfil")
    UsuarioDTO toDto(Usuario entity);

    @Override
    @InheritInverseConfiguration
    Usuario toEntity(UsuarioDTO dto);
}
