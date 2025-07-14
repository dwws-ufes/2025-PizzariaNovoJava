package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.ItemPedido;
import com.dwws.pizzaria.domain.Pedido;
import com.dwws.pizzaria.domain.Produto;
import com.dwws.pizzaria.repository.ItemPedidoRepository;
import com.dwws.pizzaria.service.dto.ItemPedidoDTO;
import com.dwws.pizzaria.service.dto.PedidoDTO;
import com.dwws.pizzaria.service.dto.ProdutoDTO;
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

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemPedidoService {

    private final ItemPedidoRepository repository;
    private final ItemPedidoMapper mapper;

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    private ItemPedido findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MensagemItemPedidoUtil.ENTITY_NOT_FOUND));
    }

    public ItemPedidoDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO) {
        PedidoDTO pedido = pedidoService.findByID(itemPedidoDTO.getPedidoId());
        ProdutoDTO produto = produtoService.findByID(itemPedidoDTO.getProdutoId());

        if (Boolean.FALSE.equals(produto.getAtivo())) {
            throw new BusinessRuleException("Produto não está ativo");
        }

        ItemPedido itemPedido = mapper.toEntity(itemPedidoDTO);
        itemPedido.setPedido(new Pedido(pedido.getId()));
        itemPedido.setProduto(new Produto(produto.getId()));

        if (Objects.isNull(itemPedido.getValorItem())) {
            itemPedido.setValorItem(produto.getPrecoVenda());
        }

        itemPedido = repository.save(itemPedido);
        return mapper.toDto(itemPedido);
    }

    public Page<ItemPedidoDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    public void delete(Long id) {
        ItemPedido itemPedido = findEntity(id);
        itemPedido.setAtivo(Boolean.FALSE);
        repository.delete(itemPedido);
    }
}
