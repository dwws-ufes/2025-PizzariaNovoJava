package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.ItemPedido;
import com.dwws.pizzaria.domain.NotificacaoBar;
import com.dwws.pizzaria.domain.NotificacaoCozinha;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.domain.enums.StatusPedido;
import com.dwws.pizzaria.domain.enums.TipoProduto;
import com.dwws.pizzaria.repository.NotificacaoBarRepository;
import com.dwws.pizzaria.repository.NotificacaoCozinhaRepository;
import com.dwws.pizzaria.repository.PedidoRepository;
import com.dwws.pizzaria.service.dto.NotificacaoBarDTO;
import com.dwws.pizzaria.service.dto.NotificacaoBarListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoBarPainelListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaDTO;
import com.dwws.pizzaria.service.dto.NotificacaoCozinhaListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoPizzaPainelListDTO;
import com.dwws.pizzaria.service.dto.NotificacaoSobremesaPainelListDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor

public class NotificacaoService {

    private final NotificacaoCozinhaRepository notificacaoCozinhaRepository;
    private final NotificacaoCozinhaMapper notificacaoCozinhaMapper;
    private final NotificacaoBarRepository notificacaoBarRepository;
    private final NotificacaoBarMapper notificacaoBarMapper;
    private final PedidoRepository pedidoRepository; // Usar repository diretamente
    private final ProdutoService produtoService;

    // ==================== MÉTODOS PARA COZINHA ====================
    private NotificacaoCozinha findEntityCozinha(Long id) {
        return notificacaoCozinhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemNotificacaoUtil.ENTITY_NOT_FOUND_COZINHA));
    }

    public NotificacaoCozinhaDTO findbyIdCozinha(Long id) {
        return notificacaoCozinhaMapper.toDto(findEntityCozinha(id));
    }

    public NotificacaoCozinhaDTO saveCozinha(NotificacaoCozinhaDTO notificacaoCozinhaDTO) {
        Pedido pedido = validarPedido(notificacaoCozinhaDTO.getPedidoId());

        NotificacaoCozinha notificacaoCozinha = notificacaoCozinhaMapper.toEntity(notificacaoCozinhaDTO);
        notificacaoCozinha.setPedido(pedido);

        if (notificacaoCozinha.getDataHora() == null) {
            notificacaoCozinha.setDataHora(LocalDateTime.now());
        }

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        log.info(MensagemNotificacaoUtil.LOG_NOTIFICACAO_CRIADA, "Cozinha", pedido.getId());
        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public NotificacaoCozinhaDTO updateCozinha(NotificacaoCozinhaDTO notificacaoCozinhaDTO) {
        log.debug("Request to update NotificacaoCozinha : {}", notificacaoCozinhaDTO);

        if (notificacaoCozinhaDTO.getId() == null) {
            throw new BusinessRuleException("ID da notificação é obrigatório para atualização");
        }

        NotificacaoCozinha notificacaoExistente = findEntityCozinha(notificacaoCozinhaDTO.getId());
        validarNotificacaoAtiva(notificacaoExistente.getAtivo());

        Pedido pedido = validarPedido(notificacaoCozinhaDTO.getPedidoId());

        NotificacaoCozinha notificacaoCozinha = notificacaoCozinhaMapper.toEntity(notificacaoCozinhaDTO);
        notificacaoCozinha.setPedido(pedido);

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        log.info(MensagemNotificacaoUtil.LOG_NOTIFICACAO_ATUALIZADA, "Cozinha", notificacaoCozinha.getStatus());
        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public List<NotificacaoPizzaPainelListDTO> findAllPizza() {
        return notificacaoCozinhaRepository.listAllPizza();
    }

    public List<NotificacaoSobremesaPainelListDTO> findAllSobremesa() {
        return notificacaoCozinhaRepository.listAllSobremesa();
    }

    public List<NotificacaoBarPainelListDTO> listAllBebidas() {
        return notificacaoCozinhaRepository.listAllBebidas();
    }

    public Page<NotificacaoCozinhaListDTO> findCozinhaByStatus(StatusPedido status, Pageable pageable) {
        log.debug("Request to get NotificacoesCozinha by status : {}", status);
        return notificacaoCozinhaRepository.findByStatus(status, pageable);
    }


    public List<NotificacaoCozinhaDTO> findCozinhaByPedidoId(Long pedidoId) {
        log.debug("Request to get NotificacoesCozinha by pedido : {}", pedidoId);
        List<NotificacaoCozinha> notificacoes = notificacaoCozinhaRepository.findByPedidoIdAndAtivoTrue(pedidoId);
        return notificacaoCozinhaMapper.toDto(notificacoes);
    }


    public List<NotificacaoCozinhaDTO> findCozinhaByStatusAtivas(StatusPedido status) {
        log.debug("Request to get NotificacoesCozinha ativas by status : {}", status);
        List<NotificacaoCozinha> notificacoes = notificacaoCozinhaRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(status);
        return notificacaoCozinhaMapper.toDto(notificacoes);
    }

    public void deleteCozinha(Long id) {
        NotificacaoCozinha notificacaoCozinha = findEntityCozinha(id);
        notificacaoCozinha.setAtivo(false);
        notificacaoCozinhaRepository.save(notificacaoCozinha);

        log.info("NotificacaoCozinha {} desativada", id);
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
        Pedido pedido = validarPedido(notificacaoBarDTO.getPedidoId());

        NotificacaoBar notificacaoBar = notificacaoBarMapper.toEntity(notificacaoBarDTO);
        notificacaoBar.setPedido(pedido);

        if (notificacaoBar.getDataHora() == null) {
            notificacaoBar.setDataHora(LocalDateTime.now());
        }

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        log.info(MensagemNotificacaoUtil.LOG_NOTIFICACAO_CRIADA, "Bar", pedido.getId());
        return notificacaoBarMapper.toDto(notificacaoBar);
    }

    public NotificacaoBarDTO updateBar(NotificacaoBarDTO notificacaoBarDTO) {
        log.debug("Request to update NotificacaoBar : {}", notificacaoBarDTO);

        if (notificacaoBarDTO.getId() == null) {
            throw new BusinessRuleException("ID da notificação é obrigatório para atualização");
        }

        NotificacaoBar notificacaoExistente = findEntityBar(notificacaoBarDTO.getId());
        validarNotificacaoAtiva(notificacaoExistente.getAtivo());

        Pedido pedido = validarPedido(notificacaoBarDTO.getPedidoId());

        NotificacaoBar notificacaoBar = notificacaoBarMapper.toEntity(notificacaoBarDTO);
        notificacaoBar.setPedido(pedido);

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        log.info(MensagemNotificacaoUtil.LOG_NOTIFICACAO_ATUALIZADA, "Bar", notificacaoBar.getStatus());
        return notificacaoBarMapper.toDto(notificacaoBar);
    }

    public Page<NotificacaoBarListDTO> findAllBar(Pageable pageable) {
        return notificacaoBarRepository.listAll(pageable);
    }


    public Page<NotificacaoBarListDTO> findBarByStatus(StatusPedido status, Pageable pageable) {
        log.debug("Request to get NotificacoesBar by status : {}", status);
        return notificacaoBarRepository.findByStatus(status, pageable);
    }


    public List<NotificacaoBarDTO> findBarByPedidoId(Long pedidoId) {
        log.debug("Request to get NotificacoesBar by pedido : {}", pedidoId);
        List<NotificacaoBar> notificacoes = notificacaoBarRepository.findByPedidoIdAndAtivoTrue(pedidoId);
        return notificacaoBarMapper.toDto(notificacoes);
    }


    public List<NotificacaoBarDTO> findBarByStatusAtivas(StatusPedido status) {
        log.debug("Request to get NotificacoesBar ativas by status : {}", status);
        List<NotificacaoBar> notificacoes = notificacaoBarRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(status);
        return notificacaoBarMapper.toDto(notificacoes);
    }

    public void deleteBar(Long id) {
        NotificacaoBar notificacaoBar = findEntityBar(id);
        notificacaoBar.setAtivo(false);
        notificacaoBarRepository.save(notificacaoBar);

        log.info("NotificacaoBar {} desativada", id);
    }

    // ==================== MÉTODOS DE CONTROLE DE STATUS ====================

    public NotificacaoCozinhaDTO marcarCozinhaComoProcessada(Long id) {
        NotificacaoCozinha notificacaoCozinha = findEntityCozinha(id);
        validarNotificacaoAtiva(notificacaoCozinha.getAtivo());

        notificacaoCozinha.setStatus(StatusPedido.EM_PREPARO);
        notificacaoCozinha.setDataHora(LocalDateTime.now());

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        log.info("NotificacaoCozinha {} marcada como processada", id);
        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public NotificacaoCozinhaDTO marcarCozinhaComoPronta(Long id) {
        NotificacaoCozinha notificacaoCozinha = findEntityCozinha(id);
        validarNotificacaoAtiva(notificacaoCozinha.getAtivo());

        notificacaoCozinha.setStatus(StatusPedido.PRONTO);
        notificacaoCozinha.setDataHora(LocalDateTime.now());

        notificacaoCozinha = notificacaoCozinhaRepository.save(notificacaoCozinha);

        log.info("NotificacaoCozinha {} marcada como pronta", id);
        return notificacaoCozinhaMapper.toDto(notificacaoCozinha);
    }

    public NotificacaoBarDTO marcarBarComoProcessada(Long id) {
        NotificacaoBar notificacaoBar = findEntityBar(id);
        validarNotificacaoAtiva(notificacaoBar.getAtivo());

        notificacaoBar.setStatus(StatusPedido.EM_PREPARO);
        notificacaoBar.setDataHora(LocalDateTime.now());

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        log.info("NotificacaoBar {} marcada como processada", id);
        return notificacaoBarMapper.toDto(notificacaoBar);
    }

    public NotificacaoBarDTO marcarBarComoPronta(Long id) {
        NotificacaoBar notificacaoBar = findEntityBar(id);
        validarNotificacaoAtiva(notificacaoBar.getAtivo());

        notificacaoBar.setStatus(StatusPedido.PRONTO);
        notificacaoBar.setDataHora(LocalDateTime.now());

        notificacaoBar = notificacaoBarRepository.save(notificacaoBar);

        log.info("NotificacaoBar {} marcada como pronta", id);
        return notificacaoBarMapper.toDto(notificacaoBar);
    }

    // ==================== MÉTODOS PARA CRIAÇÃO AUTOMÁTICA ====================
    public void criarNotificacoesPorTipoProduto(Pedido pedido) {
        log.debug("Criando notificações para pedido : {}", pedido.getId());

        boolean temPizza = false;
        boolean temBebida = false;
        boolean temSobremesa = false;

        // Verificar tipos de produtos no pedido
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoService.findEntity(item.getProduto().getId());

            if (Objects.equals(produto.getTipoProduto(), TipoProduto.PIZZA)) {
                temPizza = true;
            } else if (Objects.equals(produto.getTipoProduto(), TipoProduto.BEBIDA)) {
                temBebida = true;
            } else {
                // Assumindo que outros produtos são sobremesas
                temSobremesa = true;
            }
        }

        criarNotificacaoCozinha(pedido, temPizza, temSobremesa);

        criarNotificacaoBar(pedido, temBebida);
    }

    private void criarNotificacaoCozinha(Pedido pedido, boolean temPizza, boolean temSobremesa) {
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
    }

    private void criarNotificacaoBar(Pedido pedido, boolean temBebida) {
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

    private Pedido validarPedido(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new BusinessRuleException(MensagemNotificacaoUtil.PEDIDO_NOT_FOUND));
    }

    private void validarNotificacaoAtiva(Boolean ativo) {
        if (!ativo) {
            throw new BusinessRuleException(MensagemNotificacaoUtil.NOTIFICACAO_INATIVA);
        }
    }

    // ==================== MÉTODOS DE RELATÓRIO ====================


    public Long countNotificacoesPendentesCozinha() {
        return (long) notificacaoCozinhaRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.PENDENTE).size();
    }


    public Long countNotificacoesPendentesBar() {
        return (long) notificacaoBarRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.PENDENTE).size();
    }


    public Long countNotificacoesEmPreparoCozinha() {
        return (long) notificacaoCozinhaRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.EM_PREPARO).size();
    }


    public Long countNotificacoesEmPreparoBar() {
        return (long) notificacaoBarRepository.findByStatusAndAtivoTrueOrderByDataHoraAsc(StatusPedido.EM_PREPARO).size();
    }
}
