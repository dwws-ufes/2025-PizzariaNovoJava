package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Bebida;
import com.dwws.pizzaria.service.dto.BebidaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

    @Query("SELECT NEW com.dwws.pizzaria.service.dto.BebidaListDTO(" +
            "b.id, b.nome, b.precoVenda, b.descricao, " +
            "b.tipoProduto, b.fabricante, b.tipoBebida, b.volume) " +
            "FROM Bebida b WHERE b.ativo = true")
    Page<BebidaListDTO> listAll(Pageable pageable);
}
