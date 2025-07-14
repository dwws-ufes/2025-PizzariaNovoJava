package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.service.dto.PedidoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.PedidoListDTO(" +
            "p.id, c.nome, u.nome, p.status, p.dataHora, " +
            "COALESCE(SUM(i.valorItem * i.quantidade), 0.0), " +
            "COALESCE(COUNT(i.id), 0L).intValue()) " +
            "FROM Pedido p " +
            "LEFT JOIN p.cliente c " +
            "LEFT JOIN p.atendente u " +
            "LEFT JOIN p.itens i " +
            "GROUP BY p.id, c.nome, u.nome, p.status, p.dataHora")
    Page<PedidoListDTO> listAll(Pageable pageable);
}
