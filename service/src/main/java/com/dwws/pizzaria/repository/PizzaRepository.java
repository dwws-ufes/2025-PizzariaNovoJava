package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.Pizza;
import com.dwws.pizzaria.service.dto.PizzaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    @Query("SELECT new com.dwws.pizzaria.service.dto.PizzaListDTO(" +
            "p.id, p.nome, p.precoVenda, p.descricao," +
            "p.qtdFatias, p.tamanho) " +
            "FROM Pizza p WHERE p.ativo = true ")
    Page<PizzaListDTO> listAll(Pageable pageable);

}
