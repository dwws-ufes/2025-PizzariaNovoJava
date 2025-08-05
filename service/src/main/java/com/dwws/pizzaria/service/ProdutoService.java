package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.repository.ProdutoRepository;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.util.MensagemProdutoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdutoService {
    private final ProdutoRepository repository;

    public Produto findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

}
