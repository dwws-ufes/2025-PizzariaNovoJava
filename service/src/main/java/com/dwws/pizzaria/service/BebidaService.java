package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Bebida;
import com.dwws.pizzaria.domain.enums.TipoBebida;
import com.dwws.pizzaria.domain.enums.TipoProduto;
import com.dwws.pizzaria.repository.BebidaRepository;
import com.dwws.pizzaria.service.dto.BebidaDTO;
import com.dwws.pizzaria.service.dto.BebidaListDTO;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.BebidaMapper;
import com.dwws.pizzaria.service.util.MensagemProdutoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class BebidaService {
    private final BebidaRepository repository;
    private final BebidaMapper mapper;

    private Bebida findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemProdutoUtil.ENTITY_NOT_FOUND));
    }

    public BebidaDTO findByID(Long id) {
        Bebida bebida = findEntity(id);

        BebidaDTO bebidaDTO = mapper.toDto(bebida);
        bebidaDTO.setTipoBebidaId(bebida.getTipoBebida().getId());
        bebidaDTO.setTipoProdutoId(TipoProduto.BEBIDA.getId());

        return bebidaDTO;
    }

    public BebidaDTO save(BebidaDTO bebidaDTO) {
        Bebida bebida = mapper.toEntity(bebidaDTO);

        if (Objects.nonNull(bebidaDTO.getTipoBebidaId())) {
            bebida.setTipoBebida(TipoBebida.fromId(bebidaDTO.getTipoBebidaId()));
        }

        bebida.setTipoProduto(TipoProduto.BEBIDA);

        return mapper.toDto(repository.save(bebida));
    }

    public Page<BebidaListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public void delete(Long id) {
        Bebida bebida = findEntity(id);
        bebida.setAtivo(Boolean.FALSE);
        repository.save(bebida);
    }
}
