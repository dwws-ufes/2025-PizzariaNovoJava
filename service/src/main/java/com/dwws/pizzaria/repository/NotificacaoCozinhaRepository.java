package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.NotificacaoCozinha;
import com.dwws.pizzaria.domain.enums.StatusPedido;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoCozinhaRepository extends JpaRepository<NotificacaoCozinha, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO(n.id, n.pedido.id, c.nome, n.status, n.dataHora, n.ativo) " +
            "FROM NotificacaoCozinha n " +
            "JOIN n.pedido p " +
            "JOIN p.cliente c " +
            "WHERE n.ativo = true " +
            "ORDER BY n.dataHora DESC")
    Page<NotificacaoCozinhaListDTO> listAll(Pageable pageable);

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO(n.id, n.pedido.id, c.nome, n.status, n.dataHora, n.ativo) " +
            "FROM NotificacaoCozinha n " +
            "JOIN n.pedido p " +
            "JOIN p.cliente c " +
            "WHERE n.ativo = true AND n.status = :status " +
            "ORDER BY n.dataHora DESC")
    Page<NotificacaoCozinhaListDTO> findByStatus(@Param("status") StatusPedido status, Pageable pageable);

    List<NotificacaoCozinha> findByPedidoIdAndAtivoTrue(Long pedidoId);

    List<NotificacaoCozinha> findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido status);
}
