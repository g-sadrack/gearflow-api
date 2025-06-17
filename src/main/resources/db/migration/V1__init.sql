################################################## SQL TABELAS #####################################################

create table
  cliente (
    id bigint not null auto_increment,
    cpf varchar(255),
    email varchar(255),
    nome varchar(255),
    telefone varchar(255),
    data_atualizacao datetime(6),
    data_cadastro datetime(6),
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_cidade varchar(255),
    endereco_codigo_cidade_ibge varchar(255),
    endereco_complemento varchar(255),
    endereco_estado varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    endereco_uf varchar(255),
    primary key (id)
  ) engine = InnoDB;

create table
  servico (
    id bigint not null auto_increment,
    codigo varchar(255),
    data_atualizacao datetime(6),
    data_cadastro datetime(6),
    descricao varchar(255),
    tempo_medio integer,
    valor decimal(38, 2),
    primary key (id)
  ) engine = InnoDB;

CREATE TABLE
  fornecedor (
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(255),
    prazo_entrega int,
    site varchar(255),
    telefone varchar(255),
    telefone_contato varchar(255),
    email varchar(255),
    tipo_fornecedor enum('FERRAMENTA', 'PECAS', 'SERVICOS'),
    cnpj varchar(255),
    condicao_pagamento varchar(255),
    contato_principal varchar(255),
    data_cadastro datetime(6),
    data_ultima_atualizacao datetime(6),
    desconto_padrao decimal(38, 2),
    eh_ativo TINYINT(1),
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_cidade varchar(255),
    endereco_codigo_cidade_ibge varchar(255),
    endereco_complemento varchar(255),
    endereco_estado varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    endereco_uf varchar(255),
    PRIMARY KEY (id)
  ) ENGINE = InnoDB;

create table
  mecanico (
    id bigint not null auto_increment,
    nome varchar(255),
    matricula varchar(255),
    eh_ativo TINYINT(1),
    especialidade varchar(255),
    data_atualizacao datetime(6),
    data_cadastro datetime(6),
    empresa_id bigint,
    primary key (id)
  ) engine = InnoDB;
  
  create table
  veiculo (
    id bigint not null auto_increment,
    marca varchar(255),
    modelo varchar(255),
    placa varchar(255),
    quilometragem integer,
    ano integer,
    cor varchar(255),
    data_atualizacao datetime(6),
    data_cadastro datetime(6),
    cliente_id bigint,
    primary key (id)
  ) engine = InnoDB;
  
  create table
  produto (
    id bigint not null auto_increment,
    nome varchar(255),
    valor decimal(38, 2),
    codigo varchar(255),
    data_cadastro datetime(6),
    data_ultima_atualizacao datetime(6),
    primary key (id)
  ) engine = InnoDB;
  
  create table
  estoque (
    id bigint not null auto_increment,
    quantidade_atual integer,
    quantidade_maxima integer,
    quantidade_minima integer,
    quantidade_reserva integer,
    localizacao varchar(255),
    lote varchar(255),
    status_estoque enum ('ESGOTADO','QUASE_ESGOTADO','ABAIXO_DO_MÍNIMO','NORMAL','ACIMA_DO_IDEAL','EXCESSO'),
    custo_medio decimal(38, 2),
    data_atualizacao datetime(6),
    data_cadastro datetime(6),
    data_ultima_entrada datetime(6),
    data_ultima_saida datetime(6),
    descricao varchar(255),
    fornecedor_id bigint,
    produto_id bigint not null,
    empresa_id bigint,
    primary key (id)
  ) engine = InnoDB;
  
  create table
  ordem_servico (
    id bigint not null auto_increment,
    data_abertura datetime(6),
    data_alteracao datetime(6),
    data_finalizacao datetime(6),
    descricao_problema varchar(255),
    diagnostico_tecnico varchar(255),
    numero_os varchar(255),
    status enum ('ABERTA', 'ANDAMENTO', 'FINALIZADA'),
    valor_total decimal(38, 2),
    eh_ativo TINYINT(1),
    mecanico_id bigint,
    veiculo_id bigint,
    empresa_id bigint,
    primary key (id)
  ) engine = InnoDB;

create table
  servico_prestado (
    id bigint not null auto_increment,
    data_cadastro datetime(6),
    data_ultima_atualizacao datetime(6),
    observacoes varchar(255),
    valor_servico_prestado decimal(38, 2),
    ordem_servico_id bigint,
    servico_id bigint,
    primary key (id)
  ) engine = InnoDB;
  
create table produto_ordem_servico (
    id bigint not null auto_increment,
    data_cadastro datetime(6),
    data_ultima_atualizacao datetime(6),
    quantidade integer,
    valor decimal(38,2),
    ordem_servico_id bigint,
    produto_id bigint,
    primary key (id)
  ) engine=InnoDB;
  
create table empresa (
    id bigint not null auto_increment,
    nome_fantasia varchar(255),
    razao_social varchar(255) not null,
    regime_tributario enum ('LUCRO_PRESUMIDO','LUCRO_REAL','MEI','SIMPLES_NACIONAL'),
    telefone varchar(255),
    termos_padrao varchar(255),
    cnae_principal varchar(255),
    cnpj varchar(255) not null,
    codigo_servico_municipal varchar(255),
    dias_validade_orcamento integer,
    email varchar(255),
    logo longblob,
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_cidade varchar(255),
    endereco_codigo_cidade_ibge varchar(255),
    endereco_complemento varchar(255),
    endereco_estado varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    endereco_uf varchar(255),
    exibir_valores_para_cliente bit,
    inscricao_estadual varchar(255),
    inscricao_municipal varchar(255),     
    ultimo_numeronfe integer,
    validade_certificado date,
    primary key (id)
  ) engine=InnoDB;

################################################## SQL INDICES E FK #####################################################

alter table cliente add constraint uk_cliente_cpf unique (cpf);
alter table cliente add constraint uk_cliente_email unique (email);

alter table servico add constraint uk_servico_codigo unique (codigo);

alter table fornecedor add constraint uk_fornecedor_cnpj unique (cnpj);
alter table fornecedor add constraint uk_fornecedor_email unique (email);

alter table mecanico add constraint uk_mecanico_matricula unique (matricula);
alter table mecanico add constraint fk_mecanico_empresa foreign key (empresa_id) references empresa(id);

alter table veiculo add constraint uk_veiculo_placa unique (placa);
alter table veiculo add constraint fk_veiculo_cliente foreign key (cliente_id) references cliente(id);

alter table produto add constraint uk_produto_codigo unique (codigo);

alter table estoque add constraint fk_estoque_fornecedor_id foreign key (fornecedor_id) references fornecedor(id);
alter table estoque add constraint fk_estoque_peca_id foreign key (produto_id) references produto(id);
alter table estoque add constraint fk_estoque_empresa_id foreign key (empresa_id) references empresa(id);

alter table ordem_servico add constraint uk_ordem_servico_numero_os unique (numero_os);
alter table ordem_servico add constraint fk_ordem_servico_veiculo_id foreign key (veiculo_id) references veiculo(id);
alter table ordem_servico add constraint fk_ordem_servico_mecanico_id foreign key (mecanico_id) references mecanico(id);
alter table ordem_servico add constraint fk_ordem_servico_empresa foreign key (empresa_id) references empresa(id);

alter table servico_prestado add constraint fk_servico_prestado_servico_id foreign key (servico_id) references servico(id);
alter table servico_prestado add constraint fk_servico_prestado_ordem_servico_id foreign key (ordem_servico_id) references ordem_servico(id);

alter table produto_ordem_servico add constraint fk_produto_ordem_servico_produto_id foreign key (produto_id) references produto(id);
alter table produto_ordem_servico add constraint fk_produto_ordem_servico_ordem_servico_id foreign key (ordem_servico_id) references ordem_servico(id);

alter table empresa add constraint uk_empresa_cnpj unique (cnpj);

################################################## SQL INSERTS #####################################################

INSERT INTO cliente (cpf, email, nome, telefone, data_cadastro, data_atualizacao, endereco_numero, endereco_bairro, endereco_cep, endereco_logradouro, endereco_complemento, endereco_cidade, endereco_estado, endereco_uf, endereco_codigo_cidade_ibge) VALUES
('123.456.789-00', 'joao.silva@email.com', 'João Silva', '(11) 9999-8888', NOW(), NOW(), '150', 'Centro', '70000-000', 'Avenida Paulista', 'Ap 1201', 'São Paulo', 'São Paulo', 'SP', '3550308'),
('987.654.321-00', 'maria.santos@email.com', 'Maria Santos', '(21) 7777-6666', NOW(), NOW(), '2500', 'Copacabana', '22000-000', 'Avenida Atlântica', 'Cobertura', 'Rio de Janeiro', 'Rio de Janeiro', 'RJ', '3304557'),
('456.789.123-00', 'carlos.oliveira@email.com', 'Carlos Oliveira', '(31) 5555-4444', NOW(), NOW(), '85', 'Savassi', '30000-000', 'Rua Pernambuco', 'Sala 501', 'Belo Horizonte', 'Minas Gerais', 'MG', '3106200');

INSERT INTO fornecedor (nome, prazo_entrega, site, telefone, telefone_contato, email, tipo_fornecedor, cnpj, condicao_pagamento, contato_principal, data_cadastro, data_ultima_atualizacao, desconto_padrao, eh_ativo, endereco_bairro, endereco_cep, endereco_cidade, endereco_codigo_cidade_ibge, endereco_complemento, endereco_estado, endereco_logradouro, endereco_numero, endereco_uf) VALUES
('Auto Peças Ltda', 15, 'www.autopecas.com.br', '(11) 3333-2222', '(11) 99999-0000', 'vendas@autopecas.com', 'PECAS', '12.345.678/0001-90', '30 dias', 'Fernando', NOW(), NOW(), 5.00, 1, 'Centro', '01001-000', 'São Paulo', '3550308', 'Sala 101', 'SP', 'Av. Paulista', '1000', 'SP'),
('Ferramentas Master', 7, 'www.ferramentasmaster.com', '(21) 2222-3333', '(21) 98888-7777', 'contato@ferramentasmaster.com', 'FERRAMENTA', '98.765.432/0001-21', 'À vista', 'Roberta', NOW(), NOW(), 3.00, 1, 'Copacabana', '22010-000', 'Rio de Janeiro', '3304557', 'Loja A', 'RJ', 'Rua Barata Ribeiro', '500', 'RJ'),
('Serviços Rápidos', 3, 'www.servicosrapidos.com.br', '(31) 4444-5555', '(31) 97777-6666', 'suporte@servicosrapidos.com', 'SERVICOS', '45.678.912/0001-34', '15 dias', 'Gustavo', NOW(), NOW(), 2.50, 1, 'Savassi', '30140-001', 'Belo Horizonte', '3106200', 'Andar 3', 'MG', 'Av. Getúlio Vargas', '1500', 'MG');


INSERT INTO mecanico (nome, matricula, eh_ativo, especialidade, data_cadastro, data_atualizacao) VALUES
('Pedro Alves', 'MEC001', 1, 'Motor', NOW(), NOW()),
('Ana Costa', 'MEC002', 1, 'Suspensão', NOW(), NOW()),
('Ricardo Fernandes', 'MEC003', 1, 'Elétrica', NOW(), NOW());

INSERT INTO servico (codigo, descricao, tempo_medio, valor, data_cadastro, data_atualizacao) VALUES
('SV001', 'Troca de óleo', 30, 120.00, NOW(), NOW()),
('SV002', 'Alinhamento', 60, 250.00, NOW(), NOW()),
('SV003', 'Diagnóstico elétrico', 45, 180.00, NOW(), NOW());


INSERT INTO produto (nome, valor, codigo, data_cadastro, data_ultima_atualizacao) VALUES
('Filtro de óleo', 25.90, 'PROD001', NOW(), NOW()),
('Pastilha de freio', 189.90, 'PROD002', NOW(), NOW()),
('Vela de ignição', 42.50, 'PROD003', NOW(), NOW());

INSERT INTO veiculo (ano, cor, marca, modelo, placa, quilometragem, cliente_id, data_cadastro, data_atualizacao) VALUES
(2020, 'Prata', 'Ford', 'Ka', 'ABC1D23', 25000, 1, NOW(), NOW()),
(2018, 'Vermelho', 'Fiat', 'Argo', 'XYZ9W87', 42000, 2, NOW(), NOW()),
(2022, 'Preto', 'Volkswagen', 'Nivus', 'QWE5R43', 12000, 3, NOW(), NOW());

INSERT INTO estoque (quantidade_atual, quantidade_maxima, quantidade_minima, quantidade_reserva, localizacao, lote, status_estoque, custo_medio, produto_id, fornecedor_id, data_cadastro, data_atualizacao, data_ultima_entrada, data_ultima_saida, descricao) VALUES
(100, 500, 20, 50, 'Prateleira A1', 'LOT2023A', 1, 22.00, 1, 1, NOW(), NOW(), NOW(), NULL, 'Filtros de óleo'),
(75, 300, 15, 30, 'Prateleira B2', 'LOT2023B', 1, 170.00, 2, 1, NOW(), NOW(), NOW(), NULL, 'Pastilhas de freio'),
(200, 1000, 50, 100, 'Prateleira C3', 'LOT2023C', 1, 38.00, 3, 2, NOW(), NOW(), NOW(), NULL, 'Velas de ignição');

INSERT INTO ordem_servico (eh_ativo, data_abertura, descricao_problema, numero_os, status, mecanico_id, veiculo_id, data_finalizacao, data_alteracao) VALUES
(1, NOW(), 'Barulho no motor', 'OS2023001', 'ABERTA', 1, 1, NOW(), NOW()),
(1, NOW(), 'Freios rangendo', 'OS2023002', 'ANDAMENTO', 2, 2, NOW(), NOW()),
(1, NOW(), 'Falha na partida', 'OS2023003', 'FINALIZADA', 3, 3, NOW(), NOW());

INSERT INTO servico_prestado (observacoes, valor_servico_prestado, ordem_servico_id, servico_id, data_cadastro, data_ultima_atualizacao) VALUES
('Troca realizada com sucesso', 120.00, 1, 1, NOW(), NOW()),
('Alinhamento completo', 250.00, 2, 2, NOW(), NOW()),
('Sistema diagnosticado', 180.00, 3, 3, NOW(), NOW());

INSERT INTO produto_ordem_servico (quantidade, valor, ordem_servico_id, produto_id, data_cadastro, data_ultima_atualizacao) VALUES
(1, 25.90, 1, 1, NOW(), NOW()),
(4, 759.60, 2, 2, NOW(), NOW()),
(4, 170.00, 3, 3, NOW(), NOW());

INSERT INTO empresa (cnae_principal, cnpj, codigo_servico_municipal, dias_validade_orcamento, email, endereco_bairro, endereco_cep, endereco_cidade, endereco_codigo_cidade_ibge, endereco_complemento, endereco_estado, endereco_logradouro, endereco_numero, endereco_uf, exibir_valores_para_cliente, inscricao_estadual, inscricao_municipal, nome_fantasia, razao_social, regime_tributario, telefone, termos_padrao, ultimo_numeronfe, validade_certificado) VALUES
('4520-0/01', '12.345.678/0001-00', '12345', 7, 'contato@oficinamodelo.com.br', 'Centro', '80010-000', 'Curitiba', '4106902', 'Sala 101', 'PR', 'Rua das Oficinas', '123', 'PR', 1, '1234567890', '987654321', 'Oficina Modelo', 'Oficina Modelo Ltda', 'SIMPLES_NACIONAL', '(41) 3333-4444', 'Termos padrão de serviço', 100, '2025-12-31'),
('4520-0/01', '98.765.432/0001-00', '54321', 5, 'contato@autotech.com.br', 'Batel', '80420-000', 'Curitiba', '4106902', 'Andar 3', 'PR', 'Av. República Argentina', '456', 'PR', 1, '0987654321', '123456789', 'AutoTech', 'AutoTech Serviços Automotivos SA', 'LUCRO_PRESUMIDO', '(41) 3333-5555', 'Termos técnicos', 75, '2026-06-30'),
('4520-0/02', '11.223.344/0001-55', '67890', 10, 'sac@mecanicarapida.com.br', 'Água Verde', '80240-000', 'Curitiba', '4106902', 'Fundos', 'PR', 'Rua João Negrão', '789', 'PR', 1, '1122334455', '5566778899', 'Mecânica Rápida', 'Mecânica Rápida Ltda', 'MEI', '(41) 3333-6666', 'Termos expressos', 50, '2024-10-15');