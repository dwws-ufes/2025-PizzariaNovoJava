package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.service.dto.ProdutoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "SELECT " +
            "   p.id AS id, " +
            "   p.nome AS nome, " +
            "   p.preco_venda AS precoVenda, " +
            "   p.descricao AS descricao, " +
            "   CAST(p.tipo_produto AS integer) AS tipoProdutoId " +
            "FROM produto p " +
            "WHERE p.ativo = true AND p.tipo_produto = 2",
            countQuery = "SELECT count(*) FROM produto p WHERE p.ativo = true AND p.tipo_produto = 2",
            nativeQuery = true)
    Page<ProdutoListDTO> listAll(Pageable pageable);
}
