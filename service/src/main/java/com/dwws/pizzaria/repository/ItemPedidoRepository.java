package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.ItemPedido;
import com.dwws.pizzaria.service.dto.ItemPedidoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    @Query("SELECT NEW com.dwws.pizzaria.service.dto.ItemPedidoListDTO(" +
            "i.id, p.nome, i.quantidade, i.valorItem) " +
            "FROM ItemPedido i " +
            "JOIN i.produto p " +
            "WHERE i.pedido.id = :pedidoId")
    List<ItemPedidoListDTO> findByPedidoId(@Param("pedidoId") Long pedidoId);

    @Query("SELECT SUM(i.quantidade * i.valorItem) FROM ItemPedido i WHERE i.pedido.id = :pedidoId")
    Double calcularValorTotalPedido(@Param("pedidoId") Long pedidoId);

    @Query("SELECT i FROM ItemPedido i WHERE i.produto.id = :produtoId")
    List<ItemPedido> findByProdutoId(@Param("produtoId") Long produtoId);
}
