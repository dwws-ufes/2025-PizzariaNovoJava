package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Cliente;
import com.dwws.pizzaria.service.dto.ClienteListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.ClienteListDTO(" +
            "c.id, c.nome, c.telefone, c.email, c.cpf, c.ativo) " +
            "FROM Cliente c WHERE c.ativo = true")
    Page<ClienteListDTO> listAll(Pageable pageable);

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.ClienteListDTO(c.id, c.nome, c.telefone, c.email, c.cpf, c.ativo) " +
            "FROM Cliente c WHERE c.ativo = true AND " +
            "(LOWER(c.nome) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "c.cpf LIKE CONCAT('%', :filtro, '%'))")
    Page<ClienteListDTO> findByFiltro(@Param("filtro") String filtro, Pageable pageable);

    Optional<Cliente> findByCpfAndAtivoTrue(String cpf);

    Optional<Cliente> findByEmailAndAtivoTrue(String email);

    boolean existsByCpfAndIdNotAndAtivoTrue(String cpf, Long id);

    boolean existsByEmailAndIdNotAndAtivoTrue(String email, Long id);
}

