package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Bebida;
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
        return mapper.toDto(findEntity(id));
    }

    public BebidaDTO save(BebidaDTO bebidaDTO) {
        Bebida bebida = mapper.toEntity(bebidaDTO);
        bebida = repository.save(bebida);
        return mapper.toDto(bebida);
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
