package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.service.dto.PedidoListDTO;
import com.dwws.pizzaria.service.dto.PedidoPreparoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.PedidoListDTO(" +
            "p.id, c.nome, u.nome, p.status, p.dataHora) " +
            "FROM Pedido p " +
            "LEFT JOIN p.cliente c " +
            "LEFT JOIN p.atendente u " +
            "LEFT JOIN p.itens i " +
            "GROUP BY p.id, c.nome, u.nome, p.status, p.dataHora")
    Page<PedidoListDTO> listAll(Pageable pageable);
    @Query(value = """
    SELECT 
        p.id AS id,
        c.nome AS clienteNome,
        u.nome AS atendenteNome,
        p.observacoes AS observacoes,
        STRING_AGG(pr.nome, ', ') AS produtos,
        SUM(ip.quantidade) AS totalItens,
        SUM(ip.valor_item * ip.quantidade) AS valorTotalPedido
    FROM pedido p
    JOIN cliente c ON c.id = p.cliente_id
    JOIN usuario u ON u.id = p.atendente_id
    JOIN item_pedido ip ON ip.pedido_id = p.id
    JOIN produto pr ON pr.id = ip.produto_id
    WHERE p.atendente_id = :atendenteId
      AND p.status = 1
      AND p.ativo = true
    GROUP BY p.id, p.data_hora, c.nome, u.nome, p.observacoes
    """, nativeQuery = true)
    List<PedidoPreparoDTO> listarPedidosEmPreparo(@Param("atendenteId") Long atendenteId);

}
