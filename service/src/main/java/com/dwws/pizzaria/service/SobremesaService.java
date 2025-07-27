package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.domain.enums.TipoProduto;
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
public class SobremesaService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    private Produto findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public ProdutoDTO findByID(Long id) {
        Produto sobremesa = findEntity(id);
        ProdutoDTO sobremesaDTO = mapper.toDto(sobremesa);
        sobremesaDTO.setTipoProdutoId(sobremesa.getTipoProduto().getId());
        return sobremesaDTO;
    }

    public ProdutoDTO save(ProdutoDTO sobremesaDTO) {
        Produto sobremesa = mapper.toEntity(sobremesaDTO);
        sobremesa.setTipoProduto(TipoProduto.SOBREMESA);
        return mapper.toDto(repository.save(sobremesa));
    }

    public Page<ProdutoListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public void delete(Long id) {
        Produto sobremesa = findEntity(id);
        sobremesa.setAtivo(Boolean.FALSE);
        repository.save(sobremesa);
    }
}
