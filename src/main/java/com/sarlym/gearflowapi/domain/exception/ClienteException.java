package com.sarlym.gearflowapi.domain.exception;

public class ClienteException extends EntidadeNaoEncontradaException{

    public ClienteException(String mensagem) {
        super(mensagem);
    }

    public ClienteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
