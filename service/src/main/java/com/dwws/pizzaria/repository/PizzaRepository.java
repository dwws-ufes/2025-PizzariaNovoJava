package com.dwws.pizzaria.repository;

import com.dwws.pizzaria.domain.NotificacaoBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<NotificacaoBar, Long> {
}
