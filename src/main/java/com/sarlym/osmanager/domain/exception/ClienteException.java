package com.sarlym.osmanager.domain.exception;

public class ClienteException extends EntidadeNaoEncontradaException{

    public ClienteException(String mensagem) {
        super(mensagem);
    }

    public ClienteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
