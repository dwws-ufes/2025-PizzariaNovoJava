package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Cliente;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.domain.Usuario;
import com.dwws.pizzaria.domain.enums.StatusPedido;
import com.dwws.pizzaria.repository.PedidoRepository;
import com.dwws.pizzaria.service.dto.ClienteDTO;
import com.dwws.pizzaria.service.dto.PedidoDTO;
import com.dwws.pizzaria.service.dto.PedidoListDTO;
import com.dwws.pizzaria.service.dto.UsuarioDTO;
import com.dwws.pizzaria.service.exception.BusinessRuleException;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.PedidoMapper;
import com.dwws.pizzaria.service.util.MensagemPedidoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {

    private final PedidoRepository repository;
    private final PedidoMapper pedidoMapper;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final NotificacaoService notificacaoService;

    private Pedido findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemPedidoUtil.ENTITY_NOT_FOUND));
    }

    public PedidoDTO findByID(Long id) {
        return pedidoMapper.toDto(findEntity(id));
    }

    public Page<PedidoListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }


    public PedidoDTO save(PedidoDTO pedidoDTO) {
        log.debug("Request to save Pedido : {}", pedidoDTO);

        // Validar cliente
        ClienteDTO cliente = clienteService.findByID(pedidoDTO.getClienteId());

        // Validar atendente (se informado)
        UsuarioDTO atendente = null;
        if (Objects.nonNull(pedidoDTO.getAtendenteId())) {
            atendente = usuarioService.findByID(pedidoDTO.getAtendenteId());
        }

        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido.setCliente(new Cliente(cliente.getId()));

        if (atendente != null) {
            pedido.setAtendente(new Usuario(atendente.getId()));
        }

        if (pedido.getDataHora() == null) {
            pedido.setDataHora(LocalDateTime.now());
        }

        pedido = repository.save(pedido);

        // Criar notificações baseadas nos produtos do pedido
        notificacaoService.criarNotificacoesPorTipoProduto(pedido);

        return pedidoMapper.toDto(pedido);
    }

    public PedidoDTO update(PedidoDTO pedidoDTO) {
        log.debug("Request to update Pedido : {}", pedidoDTO);

        if (pedidoDTO.getId() == null) {
            throw new BusinessRuleException("ID do pedido é obrigatório para atualização");
        }

        Pedido pedidoExistente = findEntity(pedidoDTO.getId());

        // Validar cliente
        ClienteDTO cliente = clienteService.findByID(pedidoDTO.getClienteId());

        // Validar atendente (se informado)
        UsuarioDTO atendente = null;
        if (Objects.nonNull(pedidoDTO.getAtendenteId())) {
            atendente = usuarioService.findByID(pedidoDTO.getAtendenteId());
        }

        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido.setCliente(new Cliente(cliente.getId()));

        if (atendente != null) {
            pedido.setAtendente(new Usuario(atendente.getId()));
        }

        // Se o status mudou, atualizar notificações
        if (!pedidoExistente.getStatus().equals(pedido.getStatus())) {
            notificacaoService.atualizarNotificacoesPorStatus(pedido);
        }

        pedido = repository.save(pedido);
        return pedidoMapper.toDto(pedido);
    }

    public void delete(Long id) {
        log.debug("Request to delete Pedido : {}", id);

        Pedido pedido = findEntity(id);

        // Só permite cancelar pedidos que ainda não foram entregues
        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new BusinessRuleException("Não é possível cancelar um pedido já entregue");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        repository.save(pedido);

        // Desativar notificações relacionadas
        notificacaoService.desativarNotificacoesPedido(pedido);
    }
}
