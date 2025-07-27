package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.ItemPedido;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.repository.ItemPedidoRepository;
import com.dwws.pizzaria.repository.PedidoRepository;
import com.dwws.pizzaria.repository.ProdutoRepository;
import com.dwws.pizzaria.service.dto.ItemPedidoDTO;
import com.dwws.pizzaria.service.dto.ItemPedidoListDTO;
import com.dwws.pizzaria.service.exception.BusinessRuleException;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.ItemPedidoMapper;
import com.dwws.pizzaria.service.util.MensagemItemPedidoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemPedidoService {

    private final ItemPedidoRepository repository;
    private final ItemPedidoMapper mapper;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    private ItemPedido findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemItemPedidoUtil.ENTITY_NOT_FOUND));
    }

    public ItemPedidoDTO findByID(Long id) {
        log.debug("Request to get ItemPedido : {}", id);
        return mapper.toDto(findEntity(id));
    }

    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO) {
        log.debug("Request to save ItemPedido : {}", itemPedidoDTO);

        // Validar pedido
        Pedido pedido = pedidoRepository.findById(itemPedidoDTO.getPedidoId())
                .orElseThrow(() -> new BusinessRuleException("Pedido não encontrado"));

        // Validar produto
        Produto produto = produtoRepository.findById(itemPedidoDTO.getProdutoId())
                .orElseThrow(() -> new BusinessRuleException("Produto não encontrado"));

        if (Boolean.FALSE.equals(produto.getAtivo())) {
            throw new BusinessRuleException("Produto não está ativo");
        }

        ItemPedido itemPedido = mapper.toEntity(itemPedidoDTO);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        // Se valor não informado, usar preço do produto
        if (Objects.isNull(itemPedido.getValorItem())) {
            itemPedido.setValorItem(produto.getPrecoVenda());
        }

        itemPedido = repository.save(itemPedido);
        return mapper.toDto(itemPedido);
    }

    public ItemPedidoDTO update(ItemPedidoDTO itemPedidoDTO) {
        log.debug("Request to update ItemPedido : {}", itemPedidoDTO);

        if (itemPedidoDTO.getId() == null) {
            throw new BusinessRuleException("ID do item é obrigatório para atualização");
        }

        // Validar pedido
        Pedido pedido = pedidoRepository.findById(itemPedidoDTO.getPedidoId())
                .orElseThrow(() -> new BusinessRuleException("Pedido não encontrado"));

        // Validar produto
        Produto produto = produtoRepository.findById(itemPedidoDTO.getProdutoId())
                .orElseThrow(() -> new BusinessRuleException("Produto não encontrado"));

        ItemPedido itemPedido = mapper.toEntity(itemPedidoDTO);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        itemPedido = repository.save(itemPedido);
        return mapper.toDto(itemPedido);
    }


    public Page<ItemPedidoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemPedidos");
        return repository.findAll(pageable).map(mapper::toDto);
    }


    public List<ItemPedidoListDTO> findByPedidoId(Long pedidoId) {
        log.debug("Request to get ItemPedidos by pedido : {}", pedidoId);
        return repository.findByPedidoId(pedidoId);
    }

    public Double calcularValorTotalPedido(Long pedidoId) {
        log.debug("Request to calculate total value for pedido : {}", pedidoId);
        Double total = repository.calcularValorTotalPedido(pedidoId);
        return total != null ? total : 0.0;
    }

    public void delete(Long id) {
        log.debug("Request to delete ItemPedido : {}", id);

        ItemPedido itemPedido = findEntity(id);
        itemPedido.setAtivo(Boolean.FALSE);
        repository.save(itemPedido);
    }
}
