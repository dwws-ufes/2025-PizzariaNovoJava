package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.NotificacaoCozinha;
import com.dwws.pizzaria.domain.enums.StatusPedido;
import com.dwws.pizzaria.service.dto.NotificacaoBarPainelListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoPizzaPainelListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoSobremesaPainelListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoCozinhaRepository extends JpaRepository<NotificacaoCozinha, Long> {

    @Query(value = "SELECT p.id      AS pedidoId," +
            "       nc.id            AS notificacaoCozinhaId," +
            "       prod.nome        As nomePizza," +
            "       c.nome           AS nomeCliente," +
            "       CAST(pizza.tamanho AS integer) AS tamanho," +
            "       pizza.qtd_fatias AS qtdFatias," +
            "       p.observacoes    AS observacao," +
            "       CAST(p.status AS integer) AS statusPratoId," +
            "       ip.quantidade    AS quantidade" +
            " FROM notificacao_cozinha nc" +
            "         JOIN pedido p on p.id = nc.pedido_id" +
            "         join item_pedido ip on ip.pedido_id = p.id" +
            "         join produto prod on ip.produto_id = prod.id" +
            "         join pizza on pizza.id = prod.id" +
            "         JOIN cliente c on c.id = p.cliente_id" +
            " WHERE nc.ativo = true" +
            "  and prod.tipo_produto = 0" +
            " ORDER BY nc.data_hora DESC", nativeQuery = true)
    List<NotificacaoPizzaPainelListDTO> listAllPizza();

    @Query(value = "SELECT p.id      AS pedidoId," +
            "       nc.id            AS notificacaoCozinhaId," +
            "       prod.nome        As nomeBebida," +
            "       c.nome           AS nomeCliente," +
            "       p.observacoes    AS observacao," +
            "       CAST(p.status AS integer) AS statusPratoId," +
            "       ip.quantidade    AS quantidade" +
            " FROM notificacao_bar nc" +
            "         JOIN pedido p on p.id = nc.pedido_id" +
            "         join item_pedido ip on ip.pedido_id = p.id" +
            "         join produto prod on ip.produto_id = prod.id" +
            "         join pizza on pizza.id = prod.id" +
            "         JOIN cliente c on c.id = p.cliente_id" +
            " WHERE nc.ativo = true" +
            "  and prod.tipo_produto = 0" +
            " ORDER BY nc.data_hora DESC", nativeQuery = true)
    List<NotificacaoBarPainelListDTO> listAllBebidas();

    @Query(value = "SELECT p.id             AS pedidoId, " +
            "       nc.id            AS notificacaoCozinhaId, " +
            "       prod.nome        As nomeSobremesa, " +
            "       c.nome           AS nomeCliente, " +
            "       p.observacoes    AS observacoes, " +
            "       CAST(p.status AS integer) AS statusPratoId, " +
            "       ip.quantidade    AS quantidade " +
            "FROM notificacao_cozinha nc " +
            "         JOIN public.pedido p on p.id = nc.pedido_id " +
            "         join item_pedido ip on ip.pedido_id = p.id " +
            "         join produto prod on ip.produto_id = prod.id " +
            "         join bebida on bebida.id = prod.id " +
            "         JOIN cliente c on c.id = p.cliente_id " +
            "WHERE nc.ativo = true " +
            "  and prod.tipo_produto = 1 " +
            "ORDER BY nc.data_hora DESC;", nativeQuery = true)
    List<NotificacaoSobremesaPainelListDTO> listAllSobremesa();

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
