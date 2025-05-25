package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Usuario;
import com.dwws.pizzaria.service.dto.UsuarioListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query( " SELECT NEW com.dwws.pizzaria.service.dto.UsuarioListDTO(u.id, " +
            " u.usuario, u.nome, u.perfil.id, u.perfil.descricao, u.ativo) " +
            " FROM Usuario u WHERE u.ativo = true " )
    Page<UsuarioListDTO> listAll(Pageable pageable);

    Usuario findByUsuario(String usuario);
}
