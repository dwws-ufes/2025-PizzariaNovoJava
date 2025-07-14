package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Pagamento;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.repository.PagamentoRepository;
import com.dwws.pizzaria.service.dto.PagamentoDTO;
import com.dwws.pizzaria.service.dto.PagamentoListDTO;
import com.dwws.pizzaria.service.dto.PedidoDTO;
import com.dwws.pizzaria.service.exception.BusinessRuleException;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.PagamentoMapper;
import com.dwws.pizzaria.service.util.MensagemPagamentoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoService {

    private final PagamentoMapper mapper;
    private final PagamentoRepository repository;
    private final PedidoService pedidoService;

    protected Pagamento findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemPagamentoUtil.ENTITY_NOT_FOUND));
    }

    public PagamentoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public PagamentoDTO save(PagamentoDTO pagamentoDTO) {
        PedidoDTO pedido = pedidoService.findByID(pagamentoDTO.getPedidoId());

        Optional<Pagamento> pagamentoExistente = repository.findByPedidoId(pagamentoDTO.getPedidoId());

        if (pagamentoExistente.isPresent()) {
            throw new BusinessRuleException("Já existe um pagamento para este pedido");
        }

        Pagamento pagamento = mapper.toEntity(pagamentoDTO);
        pagamento.setPedido(new Pedido(pedido.getId()));
        pagamento.setDataHora(LocalDateTime.now());

        pagamento = repository.save(pagamento);
        return mapper.toDto(pagamento);
    }

    public Page<PagamentoListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public void delete(Long id) {
        Pagamento pagamento = findEntity(id);
        pagamento.setAtivo(Boolean.FALSE);
        repository.delete(pagamento);
    }
}

