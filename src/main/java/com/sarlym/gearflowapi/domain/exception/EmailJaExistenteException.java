package com.sarlym.gearflowapi.domain.exception;

public class EmailJaExistenteException extends NegocioException{
    public EmailJaExistenteException(String mensagem) {
        super(mensagem);
    }
}
