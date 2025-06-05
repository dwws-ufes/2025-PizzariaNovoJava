package com.dwws.pizzaria.auth;

import com.dwws.pizzaria.service.dto.UsuarioLoginDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UsuarioLoginDTO usuario;
    private String accessToken;
    private String refreshToken;
}
