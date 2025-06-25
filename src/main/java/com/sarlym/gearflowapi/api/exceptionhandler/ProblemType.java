package com.sarlym.gearflowapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade nao encontrada"),
    ERRO_DE_SISTEMA("/erro-sistema", "Erro no sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dado ou dados invalidos"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    PARAMETRO_INVALIDO("/identificador-inválido", "Tipo de identificador inválido"),
    ERRO_DE_NEGOCIO("/erro-de-negocio", "Erro: requisição inválida"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Erro de sintaxe ao enviar a mensagem");


    private String titulo;
    private String uri;

    ProblemType(String path, String titulo) {
        this.uri = "https://legacyink.com.br" + path;
        this.titulo = titulo;
    }
}
