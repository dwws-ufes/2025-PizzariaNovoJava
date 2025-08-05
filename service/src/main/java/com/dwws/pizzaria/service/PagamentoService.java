package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Pagamento;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.domain.enums.FormaPagamento;
import com.dwws.pizzaria.repository.PagamentoRepository;
import com.dwws.pizzaria.repository.PedidoRepository;
import com.dwws.pizzaria.service.dto.PagamentoDTO;
import com.dwws.pizzaria.service.dto.PagamentoListDTO;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoService {

    private final PagamentoMapper mapper;
    private final PagamentoRepository repository;
    private final PedidoRepository pedidoRepository; // Usar repository diretamente

    protected Pagamento findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemPagamentoUtil.ENTITY_NOT_FOUND));
    }

    public PagamentoDTO findByID(Long id) {
        log.debug("Request to get Pagamento : {}", id);
        return mapper.toDto(findEntity(id));
    }

    public PagamentoDTO save(PagamentoDTO pagamentoDTO) {
        log.debug("Request to save Pagamento : {}", pagamentoDTO);

        // Validar pedido
        Pedido pedido = pedidoRepository.findById(pagamentoDTO.getPedidoId())
                .orElseThrow(() -> new BusinessRuleException("Pedido não encontrado"));

        // Verificar se já existe pagamento para este pedido
        Optional<Pagamento> pagamentoExistente = repository.findByPedidoId(pagamentoDTO.getPedidoId());
        if (pagamentoExistente.isPresent()) {
            throw new BusinessRuleException("Já existe um pagamento para este pedido");
        }

        Pagamento pagamento = mapper.toEntity(pagamentoDTO);
        pagamento.setPedido(pedido);
        pagamento.setValorFinal(pagamentoDTO.getValorTotal());
        pagamento.setAtivo(Boolean.TRUE);

        if (pagamento.getDataHora() == null) {
            pagamento.setDataHora(LocalDateTime.now());
        }

        pagamento = repository.save(pagamento);
        pedido.setAtivo(Boolean.FALSE);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return mapper.toDto(pagamento);
    }

    public PagamentoDTO update(PagamentoDTO pagamentoDTO) {
        log.debug("Request to update Pagamento : {}", pagamentoDTO);

        if (pagamentoDTO.getId() == null) {
            throw new BusinessRuleException("ID do pagamento é obrigatório para atualização");
        }

        // Validar pedido
        Pedido pedido = pedidoRepository.findById(pagamentoDTO.getPedidoId())
                .orElseThrow(() -> new BusinessRuleException("Pedido não encontrado"));

        Pagamento pagamento = mapper.toEntity(pagamentoDTO);
        pagamento.setPedido(pedido);

        pagamento = repository.save(pagamento);
        return mapper.toDto(pagamento);
    }

    @Transactional(readOnly = true)
    public Page<PagamentoListDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pagamentos");
        return repository.listAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<PagamentoDTO> findByPedidoId(Long pedidoId) {
        log.debug("Request to get Pagamento by pedido : {}", pedidoId);
        return repository.findByPedidoId(pedidoId)
                .map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findByFormaPagamentoAndPeriodo(FormaPagamento formaPagamento,
                                                             LocalDateTime dataInicio,
                                                             LocalDateTime dataFim) {
        log.debug("Request to get Pagamentos by forma pagamento and periodo : {} - {} to {}",
                formaPagamento, dataInicio, dataFim);
        List<Pagamento> pagamentos = repository.findByFormaPagamentoAndDataHoraBetween(
                formaPagamento, dataInicio, dataFim);
        return mapper.toDto(pagamentos);
    }

    @Transactional(readOnly = true)
    public Double calcularReceitaPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        log.debug("Request to calculate receita by periodo : {} to {}", dataInicio, dataFim);
        Double receita = repository.calcularReceitaPorPeriodo(dataInicio, dataFim);
        return receita != null ? receita : 0.0;
    }

    public void delete(Long id) {
        log.debug("Request to delete Pagamento : {}", id);

        Pagamento pagamento = findEntity(id);
        repository.delete(pagamento);
    }
}
