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
    private final ProdutoService produtoService;
    private final NotificacaoService notificacaoService;

    private Pedido findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemPedidoUtil.ENTITY_NOT_FOUND));
    }

    public PedidoDTO findByID(Long id) {
        return pedidoMapper.toDto(findEntity(id));
    }

    @Transactional(readOnly = true)
    public Page<PedidoListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public PedidoDTO save(PedidoDTO pedidoDTO) {
        ClienteDTO cliente = clienteService.findByID(pedidoDTO.getClienteId());

        UsuarioDTO atendente = null;
        if (Objects.nonNull(pedidoDTO.getAtendenteId())) {
            atendente = usuarioService.findByID(pedidoDTO.getAtendenteId());
        }

        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido.setCliente(new Cliente(cliente.getId()));
        pedido.setAtendente(new Usuario(atendente.getId()));
        pedido.setDataHora(LocalDateTime.now());

        pedido = repository.save(pedido);

        notificacaoService.criarNotificacoesPorTipoProduto(pedido);

        return pedidoMapper.toDto(pedido);
    }

    public void delete(Long id) {
        Pedido pedido = findEntity(id);

        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new BusinessRuleException("Não é possível cancelar um pedido já entregue");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        repository.save(pedido);
    }


}

