CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    data_cadastro DATETIME,
    data_atualizacao DATETIME
);