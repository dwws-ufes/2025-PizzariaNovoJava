package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Pagamento;
import com.dwws.pizzaria.domain.enums.FormaPagamento;
import com.dwws.pizzaria.service.dto.PagamentoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.PagamentoListDTO(pg.id, pg.pedido.id, c.nome, pg.valorTotal, pg.formaPagamento, pg.dataHora) " +
            "FROM Pagamento pg " +
            "JOIN pg.pedido p " +
            "JOIN p.cliente c")
    Page<PagamentoListDTO> listAll(Pageable pageable);

    Optional<Pagamento> findByPedidoId(Long pedidoId);

    List<Pagamento> findByFormaPagamentoAndDataHoraBetween(FormaPagamento formaPagamento,
                                                           LocalDateTime dataInicio,
                                                           LocalDateTime dataFim);

    @Query("SELECT SUM(pg.valorTotal) FROM Pagamento pg WHERE pg.dataHora BETWEEN :dataInicio AND :dataFim")
    Double calcularReceitaPorPeriodo(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT pg.formaPagamento, SUM(pg.valorTotal) FROM Pagamento pg " +
            "WHERE pg.dataHora BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY pg.formaPagamento")
    List<Object[]> relatorioReceitaPorFormaPagamento(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}
