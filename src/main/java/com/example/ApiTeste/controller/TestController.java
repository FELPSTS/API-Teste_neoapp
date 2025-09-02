package com.example.ApiTeste.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Teste", description = "Endpoints para teste da API")
public class TestController {
    
    @GetMapping("/test")
    @Operation(summary = "Testar API", description = "Endpoint para verificar se a API está funcionando")
    @ApiResponse(responseCode = "200", description = "API funcionando corretamente")
    public String test() {
        return "API está funcionando! Tudo certo! ✅";
    }
    
    @GetMapping("/hello")
    @Operation(summary = "Mensagem de saudação", description = "Retorna uma mensagem de olá")
    @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso")
    public String hello() {
        return "Olá! Bem-vindo à API! 🚀";
    }
}