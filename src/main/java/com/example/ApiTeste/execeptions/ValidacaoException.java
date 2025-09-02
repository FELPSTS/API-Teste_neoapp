package com.example.ApiTeste.execeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoException extends RuntimeException {
    
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
    
    public ValidacaoException(String campo, String mensagem) {
        super("Campo '" + campo + "': " + mensagem);
    }
}