package com.example.ApiTeste.execeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends RuntimeException {
    
    public ClienteNaoEncontradoException(Long id) {
        super("Cliente não encontrado com ID: " + id);
    }
    
    public ClienteNaoEncontradoException(String cpf) {
        super("Cliente não encontrado com CPF: " + cpf);
    }
}