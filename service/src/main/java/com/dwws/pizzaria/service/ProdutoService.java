package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.repository.ProdutoRepository;
import com.dwws.pizzaria.service.dto.ProdutoDTO;
import com.dwws.pizzaria.service.dto.ProdutoListDTO;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.ProdutoMapper;
import com.dwws.pizzaria.service.util.MensagemProdutoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    private Produto findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public ProdutoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    @Transactional(readOnly = true)
    public Page<ProdutoListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoListDTO> findByFiltro(String filtro, Pageable pageable) {
        return repository.findByFiltro(filtro, pageable);
    }
}
