package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.service.dto.ProdutoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT NEW com.dwws.pizzaria.service.dto.ProdutoListDTO(p.id, p.nome, p.descricao, p.precoVenda, p.ativo, " +
            "CASE WHEN TYPE(p) = Pizza THEN 'Pizza' WHEN TYPE(p) = Bebida THEN 'Bebida' ELSE 'Produto' END) " +
            "FROM Produto p WHERE p.ativo = true")
    Page<ProdutoListDTO> listAll(Pageable pageable);

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.ProdutoListDTO(p.id, p.nome, p.descricao, p.precoVenda, p.ativo, " +
            "CASE WHEN TYPE(p) = Pizza THEN 'Pizza' WHEN TYPE(p) = Bebida THEN 'Bebida' ELSE 'Produto' END) " +
            "FROM Produto p WHERE p.ativo = true AND " +
            "LOWER(p.nome) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    Page<ProdutoListDTO> findByFiltro(@Param("filtro") String filtro, Pageable pageable);
}
