package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Bebida;
import com.dwws.pizzaria.domain.ItemPedido;
import com.dwws.pizzaria.domain.NotificacaoBar;
import com.dwws.pizzaria.domain.NotificacaoCozinha;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.domain.Pizza;
import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.domain.enums.StatusPedido;
import com.dwws.pizzaria.repository.NotificacaoBarRepository;
import com.dwws.pizzaria.repository.NotificacaoCozinhaRepository;
import com.dwws.pizzaria.service.dto.NotificacaoBarDTO;
import com.dwws.pizzaria.service.dto.NotificacaoBarListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO;
import com.dwws.pizzaria.service.dto.PedidoDTO;
import com.dwws.pizzaria.service.exception.BusinessRuleException;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.NotificacaoBarMapper;
import com.dwws.pizzaria.service.mapper.NotificacaoCozinhaMapper;
import com.dwws.pizzaria.service.util.MensagemNotificacaoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NotificacaoService {

    private final NotificacaoCozinhaRepository notificacaoCozinhaRepository;
    private final NotificacaoCozinhaMapper notificacaoCozinhaMapper;
    private final NotificacaoBarRepository notificacaoBarRepository;
    private final NotificacaoBarMapper notificacaoBarMapper;
    private final PedidoService pedidoService;

    // ==================== MÉTODOS PARA COZINHA ====================
    private NotificacaoCozinha findEntityCozinha(Long id) {
        return notificacaoCozinhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemNotificacaoUtil.ENTITY_NOT_FOUND_COZINHA));
    }

    public NotificacaoCozinhaDTO findbyIdCozinha(Long id) {
        return notificacaoCozinhaMapper.toDto(findEntityCozinha(id));
    }

    public NotificacaoCozinhaDTO saveCozinha(NotificacaoCozinhaDTO notificacaoCozinhaDTO) {
        PedidoDTO pedido = validarPedido(notificacaoCozinhaDTO.getPedidoId());

        NotificacaoCozinha notificacaoCozinha = notificacaoCozinhaMapper.toEntity(notificacaoCozinhaDTO);
        notificacaoCozinha.setPedido(new Pedido(pedido.getId()));
        notificacaoCozinha.setDataHora(LocalDateTime.now());

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public Page<NotificacaoCozinhaListDTO> findAllCozinha(Pageable pageable) {
        return notificacaoCozinhaRepository.listAll(pageable);
    }

    public void deleteCozinha(Long id) {
        NotificacaoCozinha notificacaoCozinha = findEntityCozinha(id);
        notificacaoCozinha.setAtivo(false);
        notificacaoCozinhaRepository.save(notificacaoCozinha);
    }

    // ==================== MÉTODOS PARA BAR ====================
    private NotificacaoBar findEntityBar(Long id) {
        return notificacaoBarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemNotificacaoUtil.ENTITY_NOT_FOUND_BAR));
    }

    public NotificacaoBarDTO findByBar(Long id) {
        return notificacaoBarMapper.toDto(findEntityBar(id));
    }

    public NotificacaoBarDTO saveBar(NotificacaoBarDTO notificacaoBarDTO) {
        PedidoDTO pedido = validarPedido(notificacaoBarDTO.getPedidoId());

        NotificacaoBar notificacaoBar = notificacaoBarMapper.toEntity(notificacaoBarDTO);
        notificacaoBar.setPedido(new Pedido(pedido.getId()));

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        return notificacaoBarMapper.toDto(notificacaoBar);
    }

    public Page<NotificacaoBarListDTO> findAllBar(Pageable pageable) {
        return notificacaoBarRepository.listAll(pageable);
    }

    public void deleteBar(Long id) {
        NotificacaoBar notificacaoBar = findEntityBar(id);
        notificacaoBar.setAtivo(false);
        notificacaoBarRepository.save(notificacaoBar);
    }

    // ==================== MÉTODOS DE CONTROLE DE STATUS ====================

    public NotificacaoCozinhaDTO marcarCozinhaComoProcessada(Long id) {
        NotificacaoCozinha notificacaoCozinha = notificacaoCozinhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemNotificacaoUtil.ENTITY_NOT_FOUND_COZINHA));

        validarNotificacaoAtiva(notificacaoCozinha.getAtivo());

        notificacaoCozinha.setStatus(StatusPedido.EM_PREPARO);
        notificacaoCozinha.setDataHora(LocalDateTime.now());

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public NotificacaoCozinhaDTO marcarCozinhaComoPronta(Long id) {
        NotificacaoCozinha notificacaoCozinha = findEntityCozinha(id);
        validarNotificacaoAtiva(notificacaoCozinha.getAtivo());

        notificacaoCozinha.setStatus(StatusPedido.PRONTO);
        notificacaoCozinha.setDataHora(LocalDateTime.now());

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public NotificacaoBarDTO marcarBarComoProcessada(Long id) {
        NotificacaoBar notificacaoBar = findEntityBar(id);
        validarNotificacaoAtiva(notificacaoBar.getAtivo());

        notificacaoBar.setStatus(StatusPedido.EM_PREPARO);
        notificacaoBar.setDataHora(LocalDateTime.now());

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        return notificacaoBarMapper.toDto(notificacaoBar);
    }

    public NotificacaoBarDTO marcarBarComoPronta(Long id) {
        NotificacaoBar notificacaoBar = findEntityBar(id);
        validarNotificacaoAtiva(notificacaoBar.getAtivo());

        notificacaoBar.setStatus(StatusPedido.PRONTO);
        notificacaoBar.setDataHora(LocalDateTime.now());

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        return notificacaoBarMapper.toDto(notificacaoBar);
    }


    // ==================== MÉTODOS PARA CRIAÇÃO AUTOMÁTICA ====================
    public void criarNotificacoesPorTipoProduto(Pedido pedido) {
        boolean temPizza = false;
        boolean temBebida = false;
        boolean temSobremesa = false;

        // Verificar tipos de produtos no pedido
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();

            if (produto instanceof Pizza) {
                temPizza = true;
            } else if (produto instanceof Bebida) {
                temBebida = true;
            } else {
                // Assumindo que outros produtos são sobremesas
                temSobremesa = true;
            }
        }

        // Criar notificação para cozinha (Pizza e Sobremesa)
        if (temPizza || temSobremesa) {
            try {
                NotificacaoCozinha notificacaoCozinha = NotificacaoCozinha.builder()
                        .pedido(pedido)
                        .status(StatusPedido.PENDENTE)
                        .dataHora(LocalDateTime.now())
                        .ativo(true)
                        .build();

                notificacaoCozinhaRepository.save(notificacaoCozinha);
                log.info(MensagemNotificacaoUtil.LOG_NOTIFICACAO_CRIADA, "Cozinha", pedido.getId());
            } catch (Exception e) {
                log.error(MensagemNotificacaoUtil.LOG_ERRO_CRIAR_NOTIFICACAO, pedido.getId(), e);
            }
        }

        // Criar notificação para bar (Bebida)
        if (temBebida) {
            try {
                NotificacaoBar notificacaoBar = NotificacaoBar.builder()
                        .pedido(pedido)
                        .status(StatusPedido.PENDENTE)
                        .dataHora(LocalDateTime.now())
                        .ativo(true)
                        .build();

                notificacaoBarRepository.save(notificacaoBar);
                log.info(MensagemNotificacaoUtil.LOG_NOTIFICACAO_CRIADA, "Bar", pedido.getId());
            } catch (Exception e) {
                log.error(MensagemNotificacaoUtil.LOG_ERRO_CRIAR_NOTIFICACAO, pedido.getId(), e);
            }
        }
    }

    /**
     * Atualiza notificações quando o status do pedido muda
     */
    public void atualizarNotificacoesPorStatus(Pedido pedido) {
        log.debug("Atualizando notificações para pedido : {} com status : {}", pedido.getId(), pedido.getStatus());

        int notificacoesAtualizadas = 0;

        // Atualizar notificações da cozinha
        List<NotificacaoCozinha> notificacoesCozinha = notificacaoCozinhaRepository.findByPedidoIdAndAtivoTrue(pedido.getId());
        for (NotificacaoCozinha notificacao : notificacoesCozinha) {
            notificacao.setStatus(pedido.getStatus());
            notificacao.setDataHora(LocalDateTime.now());
            notificacaoCozinhaRepository.save(notificacao);
            notificacoesAtualizadas++;
        }

        // Atualizar notificações do bar
        List<NotificacaoBar> notificacoesBar = notificacaoBarRepository.findByPedidoIdAndAtivoTrue(pedido.getId());
        for (NotificacaoBar notificacao : notificacoesBar) {
            notificacao.setStatus(pedido.getStatus());
            notificacao.setDataHora(LocalDateTime.now());
            notificacaoBarRepository.save(notificacao);
            notificacoesAtualizadas++;
        }

        log.info(MensagemNotificacaoUtil.LOG_NOTIFICACOES_PROCESSADAS, notificacoesAtualizadas, pedido.getId());
    }

    /**
     * Desativa notificações quando o pedido é cancelado
     */
    public void desativarNotificacoesPedido(Pedido pedido) {
        log.debug("Desativando notificações para pedido cancelado : {}", pedido.getId());

        int notificacoesDesativadas = 0;

        // Desativar notificações da cozinha
        List<NotificacaoCozinha> notificacoesCozinha = notificacaoCozinhaRepository.findByPedidoIdAndAtivoTrue(pedido.getId());
        for (NotificacaoCozinha notificacao : notificacoesCozinha) {
            notificacao.setAtivo(false);
            notificacaoCozinhaRepository.save(notificacao);
            notificacoesDesativadas++;
        }

        // Desativar notificações do bar
        List<NotificacaoBar> notificacoesBar = notificacaoBarRepository.findByPedidoIdAndAtivoTrue(pedido.getId());
        for (NotificacaoBar notificacao : notificacoesBar) {
            notificacao.setAtivo(false);
            notificacaoBarRepository.save(notificacao);
            notificacoesDesativadas++;
        }

        log.info("Desativadas {} notificações para pedido cancelado {}", notificacoesDesativadas, pedido.getId());
    }

    // ==================== MÉTODOS DE VALIDAÇÃO ====================

    private PedidoDTO validarPedido(Long pedidoId) {
        return pedidoService.findByID(pedidoId);
    }

    private void validarNotificacaoAtiva(Boolean ativo) {
        if (!ativo) {
            throw new BusinessRuleException(MensagemNotificacaoUtil.NOTIFICACAO_INATIVA);
        }
    }

    // ==================== MÉTODOS DE RELATÓRIO ====================

    @Transactional(readOnly = true)
    public Long countNotificacoesPendentesCozinha() {
        return (long) notificacaoCozinhaRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.PENDENTE).size();
    }

    @Transactional(readOnly = true)
    public Long countNotificacoesPendentesBar() {
        return (long) notificacaoBarRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.PENDENTE).size();
    }

    @Transactional(readOnly = true)
    public Long countNotificacoesEmPreparoCozinha() {
        return (long) notificacaoCozinhaRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.EM_PREPARO).size();
    }

    @Transactional(readOnly = true)
    public Long countNotificacoesEmPreparoBar() {
        return (long) notificacaoBarRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.EM_PREPARO).size();
    }
}
