package com.dwws.pizzaria.service;

import com.dwws.pizzaria.domain.Usuario;
import com.dwws.pizzaria.repository.UsuarioRepository;
import com.dwws.pizzaria.service.dto.UserPasswordChangeDTO;
import com.dwws.pizzaria.service.dto.UsuarioDTO;
import com.dwws.pizzaria.service.dto.UsuarioListDTO;
import com.dwws.pizzaria.service.exception.EntityNotFoundException;
import com.dwws.pizzaria.service.mapper.UsuarioMapper;
import com.dwws.pizzaria.service.util.MensagemUsuarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public Usuario findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemUsuarioUtil.ENTITY_NOT_FOUND));
    }

    public Page<UsuarioListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public UsuarioDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public UsuarioDTO save(UsuarioDTO dto) {

        if (dto.getId() == null) {
            dto.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Usuario user = findEntity(id);
        user.setAtivo(false);
        repository.save(user);
    }

    public void updtPassword(UserPasswordChangeDTO userPasswordChangeDTO) {
        Usuario user = findEntity(userPasswordChangeDTO.getId());
        user.setSenha(passwordEncoder.encode(userPasswordChangeDTO.getSenha()));
        repository.save(user);
    }
}
