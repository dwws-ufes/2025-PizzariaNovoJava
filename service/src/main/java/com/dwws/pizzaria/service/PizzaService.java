package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Pizza;
import com.dwws.pizzaria.domain.enums.TamanhoPizza;
import com.dwws.pizzaria.domain.enums.TipoProduto;
import com.dwws.pizzaria.repository.PizzaRepository;
import com.dwws.pizzaria.service.dto.PizzaDTO;
import com.dwws.pizzaria.service.dto.PizzaListDTO;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.PizzaMapper;
import com.dwws.pizzaria.service.util.MensagemClienteUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class PizzaService {
    private final PizzaRepository repository;
    private final PizzaMapper mapper;

    private Pizza findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteUtil.ENTITY_NOT_FOUND));
    }

    public PizzaDTO findByID(Long id) {
        Pizza pizza = findEntity(id);

        PizzaDTO pizzaDTO = mapper.toDto(pizza);
        pizzaDTO.setTamanhoId(pizza.getTamanho().getId());
        pizzaDTO.setTipoProdutoId(TipoProduto.PIZZA.getId());

        return pizzaDTO;
    }

    public Page<PizzaListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public PizzaDTO save(PizzaDTO dto) {
        Pizza pizza = mapper.toEntity(dto);

        if (Objects.nonNull(dto.getTamanhoId())) {
            pizza.setTamanho(TamanhoPizza.fromId(dto.getTamanhoId()));
        }

        pizza.setTipoProduto(TipoProduto.PIZZA);

        return mapper.toDto(repository.save(pizza));
    }

    public void delete(Long id) {
        Pizza pizza = findEntity(id);
        pizza.setAtivo(Boolean.FALSE);
        repository.save(pizza);
    }
}
