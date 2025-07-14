package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Pagamento;
import com.dwws.pizzaria.service.dto.PagamentoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.PagamentoListDTO(pg.id, pg.pedido.id, c.nome, pg.valorTotal, pg.formaPagamento, pg.dataHora) " +
            "FROM Pagamento pg " +
            "JOIN pg.pedido p " +
            "JOIN p.cliente c")
    Page<PagamentoListDTO> listAll(Pageable pageable);

    Optional<Pagamento> findByPedidoId(Long pedidoId);
}
