package com.sarlym.osmanager.domain.exception;

public class EmailJaExistenteException extends NegocioException{
    public EmailJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
