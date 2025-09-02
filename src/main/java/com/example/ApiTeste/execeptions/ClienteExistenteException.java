package com.example.ApiTeste.execeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClienteExistenteException extends RuntimeException {
    
    public ClienteExistenteException(String campo, String valor) {
        super("Já existe um cliente cadastrado com " + campo + ": " + valor);
    }
}