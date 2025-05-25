package com.dwws.pizzaria.auth;

import com.dwws.pizzaria.config.JwtService;
import com.dwws.pizzaria.domain.Perfil;
import com.dwws.pizzaria.domain.Usuario;
import com.dwws.pizzaria.repository.UsuarioRepository;
import com.dwws.pizzaria.service.dto.LoginDTO;
import com.dwws.pizzaria.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UsuarioDTO usuario) {
        Perfil perfilUser = new Perfil();
        Usuario user = Usuario.builder()
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .senha(passwordEncoder.encode(usuario.getSenha()))
                .perfil(perfilUser)
                .ativo(true)
                .build();
        usuarioRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public LoginDTO authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        var usuario = usuarioRepository.findByUsuario(authenticationRequest.getUsername());
        //.orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        LoginDTO loginData = new LoginDTO(
                usuario.getLogin(),
                usuario.getNome(),
                usuario.getPerfil().getDescricao(),
                usuario.getPerfil().getId()
        );

        String jwtToken = jwtService.generateToken(usuario);

//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();

        return loginData;
    }
}
