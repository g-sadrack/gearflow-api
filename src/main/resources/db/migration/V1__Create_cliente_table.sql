CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    data_cadastro DATETIME,
    data_atualizacao DATETIME
);

CREATE TABLE veiculo (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(8) UNIQUE,
    modelo VARCHAR(100),
    marca VARCHAR(100),
    cor VARCHAR(100),
    ano INT,
    quilometragem INT,
    cliente_id BIGINT NOT NULL,
    data_atualizacao DATETIME(6),
    data_cadastro DATETIME(6),
    CONSTRAINT fk_veiculo_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
);

CREATE TABLE fornecedor (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120),
    numero VARCHAR(13),
    email VARCHAR(120),
    cnpj VARCHAR(18),
    desconto_padrao DECIMAL(38, 2),
    tipo_fornecedor ENUM('FERRAMENTA', 'PECAS', 'SERVICOS')
    ativo BIT(1),
    cep VARCHAR(9),
    bairro VARCHAR(120),
    cidade VARCHAR(120),
    estado VARCHAR(120),
    logradouro VARCHAR(120),
    pais VARCHAR(120),
    prazo_entrega INT,
    condicao_pagamento VARCHAR(255),
    contato_principal VARCHAR(255),
    site VARCHAR(255),
    telefone VARCHAR(13),
    telefone_contato VARCHAR(13),
    data_cadastro DATETIME(6),
    data_ultima_atualizacao DATETIME(6),
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE produto (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120),
    codigo VARCHAR(120),
    valor_unitario DECIMAL(38, 2),
    fornecedor_id BIGINT NOT NULL,
    data_cadastro DATETIME(6),
    data_ultima_atualizacao DATETIME(6),
    CONSTRAINT fk_produto_fornecedor
        FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedor(id)
)