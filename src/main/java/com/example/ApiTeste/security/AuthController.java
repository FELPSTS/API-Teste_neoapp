package com.example.ApiTeste.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "API para autenticação de usuários")
public class AuthController {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    public AuthController(AuthenticationManager authenticationManager, 
                         JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @PostMapping("/login")
    @Operation(summary = "Realizar login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        String token = jwtTokenProvider.generateToken(authRequest.getUsername());
        return new AuthResponse(token);
    }
    
    @Data
    public static class AuthRequest {
        private String username;
        private String password;
    }
    
    @Data
    public static class AuthResponse {
        private String token;
        
        public AuthResponse(String token) {
            this.token = token;
        }
    }
}