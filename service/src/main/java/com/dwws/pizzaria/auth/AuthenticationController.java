package com.dwws.pizzaria.auth;

import com.dwws.pizzaria.service.dto.LoginDTO;
import com.dwws.pizzaria.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(authenticationService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthenticationRequest authenticationRequest) {
        LoginDTO usuario = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(usuario);
    }
}
