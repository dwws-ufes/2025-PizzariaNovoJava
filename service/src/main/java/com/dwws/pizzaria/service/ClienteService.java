package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Cliente;
import com.dwws.pizzaria.repository.ClienteRepository;
import com.dwws.pizzaria.service.dto.ClienteDTO;
import com.dwws.pizzaria.service.dto.ClienteListDTO;
import com.dwws.pizzaria.service.exception.BusinessRuleException;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.ClienteMapper;
import com.dwws.pizzaria.service.util.MensagemClienteUtil;
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
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    private Cliente findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteUtil.ENTITY_NOT_FOUND));
    }

    public ClienteDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public Page<ClienteListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public Page<ClienteListDTO> findByFiltro(String filtro, Pageable pageable) {
        return repository.findByFiltro(filtro, pageable);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        validarDadosUnicos(clienteDTO);

        Cliente cliente = mapper.toEntity(clienteDTO);
        cliente = repository.save(cliente);
        return mapper.toDto(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = findEntity(id);
        cliente.setAtivo(Boolean.FALSE);
        repository.save(cliente);
    }

    private void validarDadosUnicos(ClienteDTO clienteDTO) {
        // Validar CPF único
        if (repository.existsByCpfAndIdNotAndAtivoTrue(clienteDTO.getCpf(),
                Objects.nonNull(clienteDTO.getId()) ? clienteDTO.getId() : 0L)) {
            throw new BusinessRuleException("CPF já cadastrado para outro cliente");
        }

        // Validar Email único
        if (repository.existsByEmailAndIdNotAndAtivoTrue(clienteDTO.getEmail(),
                Objects.nonNull(clienteDTO.getId()) ? clienteDTO.getId() : 0L)) {
            throw new BusinessRuleException("Email já cadastrado para outro cliente");
        }
    }
}


