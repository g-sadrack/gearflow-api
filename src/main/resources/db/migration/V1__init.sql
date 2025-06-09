/*-- Inserts básicos
INSERT INTO cliente (nome, telefone, email, cpf, data_cadastro, data_atualizacao)
VALUES
('João Silva', '(11) 9999-8888', 'joao.silva@email.com', '12345678901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Maria Santos', '(21) 7777-6666', 'maria.santos@email.com', '23456789012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carlos Oliveira', '(31) 5555-4444', 'carlos.oliveira@email.com', '34567890123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Cliente sem telefone
INSERT INTO cliente (nome, email, cpf, data_cadastro, data_atualizacao)
VALUES
('Ana Costa', 'ana.costa@email.com', '45678901234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Clientes adicionais para teste
INSERT INTO cliente (nome, telefone, email, cpf, data_cadastro, data_atualizacao)
VALUES
('Pedro Almeida', '(41) 3333-2222', 'pedro.almeida@email.com', '56789012345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fernanda Lima', '(51) 1111-0000', 'fernanda.lima@email.com', '67890123456', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ricardo Pereira', '(61) 2222-3333', 'ricardo.pereira@email.com', '78901234567', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Juliana Rocha', '(71) 4444-5555', 'juliana.rocha@email.com', '89012345678', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Marcos Souza', '(81) 6666-7777', 'marcos.souza@email.com', '90123456789', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Patrícia Nunes', '(91) 8888-9999', 'patricia.nunes@email.com', '01234567890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
*/

INSERT INTO cliente (cpf, data_atualizacao, data_cadastro, email, nome, telefone) 
VALUES 
('123.456.789-00', NOW(), NOW(), 'cliente1@email.com', 'João Silva', '(11) 9999-8888'),
('987.654.321-00', NOW(), NOW(), 'cliente2@email.com', 'Maria Souza', '(21) 7777-6666');

INSERT INTO fornecedor (
    ativo, bairro, cep, cidade, cnpj, condicao_pagamento, contato_principal, 
    data_cadastro, data_ultima_atualizacao, desconto_padrao, email, estado, 
    logradouro, nome, numero, pais, prazo_entrega, site, telefone, telefone_contato, tipo_fornecedor
) VALUES 
(
    1, 'Centro', '01001-000', 'São Paulo', '12.345.678/0001-99', '30 DIAS', 'Carlos',
    NOW(), NOW(), 5.0, 'fornecedor1@empresa.com', 'SP', 'Rua Principal', 
    'Fornecedor Peças Ltda', '100', 'Brasil', 15, 'www.fornecedor1.com.br', '(11) 3333-2222', '(11) 94444-3333', 'PECAS'
),
(
    1, 'Industrial', '80000-000', 'Curitiba', '98.765.432/0001-11', '45 DIAS', 'Fernanda',
    NOW(), NOW(), 3.5, 'fornecedor2@servicos.com', 'PR', 'Avenida das Indústrias', 
    'Serviços Automotivos SA', '2500', 'Brasil', 7, 'www.fornecedor2.com.br', '(41) 5555-4444', '(41) 98888-7777', 'SERVICOS'
);

INSERT INTO produto (codigo, data_cadastro, data_ultima_atualizacao, nome, valor_unitario) 
VALUES 
('P001', NOW(), NOW(), 'Pastilha de Freio', 120.50),
('P002', NOW(), NOW(), 'Filtro de Óleo', 35.90);

INSERT INTO mecanico (ativo, data_atualizacao, data_cadastro, especialidade, matricula, nome) 
VALUES 
(1, NOW(), NOW(), 'Motor', 'MEC2024001', 'Roberto Alves'),
(1, NOW(), NOW(), 'Suspensão', 'MEC2024002', 'Ana Costa');

INSERT INTO veiculo (
    ano, cor, data_atualizacao, data_cadastro, marca, modelo, placa, quilometragem, cliente_id
) VALUES 
(2020, 'Prata', NOW(), NOW(), 'Ford', 'Ka', 'ABC1D23', 45000, 1),
(2018, 'Vermelho', NOW(), NOW(), 'Fiat', 'Argo', 'XYZ9A87', 68000, 2);

INSERT INTO ordem_servico (
    ativo, data_abertura, data_alteracao, data_finalizacao, descricao_problema, 
    diagnostico_tecnico, numero_os, status, valor_total, mecanico_id, veiculo_id
) VALUES 
(
    1, '2024-05-01 09:00:00', NOW(), NULL, 'Barulho ao frear',
    'Pastilhas desgastadas', 'OS-2024-0001', 1, 0, 1, 1
),
(
    1, '2024-05-02 10:30:00', NOW(), NULL, 'Troca de óleo',
    'Troca periódica', 'OS-2024-0002', 1, 0, 2, 2
);

INSERT INTO ordem_servico (
    ativo, data_abertura, data_alteracao, data_finalizacao, descricao_problema, 
    diagnostico_tecnico, numero_os, status, valor_total, mecanico_id, veiculo_id
) VALUES 
(
    1, '2024-05-01 09:00:00', NOW(), NULL, 'Barulho ao frear',
    'Pastilhas desgastadas', 'OS-2024-0001', 1, 0, 1, 1
),
(
    1, '2024-05-02 10:30:00', NOW(), NULL, 'Troca de óleo',
    'Troca periódica', 'OS-2024-0002', 1, 0, 2, 2
);

INSERT INTO estoque (
    status_estoque, custo_medio, data_atualizacao, data_cadastro, 
    data_ultima_entrada, data_ultima_saida, descricao, localizacao, lote, 
    quantidade_atual, quantidade_maxima, quantidade_minima, quantidade_reserva, 
    fornecedor_id, produto_id
) VALUES 
(
    1, 100.00, NOW(), NOW(), '2024-04-20', NULL, 
    'Pastilha Freio Dianteira', 'Prateleira A3', 'LOTE-0424', 
    50, 100, 10, 5, 1, 1
),
(
    1, 25.00, NOW(), NOW(), '2024-04-25', NULL, 
    'Filtro Óleo Sintético', 'Prateleira B7', 'LOTE-0524', 
    80, 200, 20, 10, 1, 2
);

INSERT INTO servico (codigo, data_atualizacao, data_cadastro, descricao, tempo_medio, valor) 
VALUES 
('S001', NOW(), NOW(), 'Troca de Pastilhas de Freio', 60, 150.00),
('S002', NOW(), NOW(), 'Troca de Óleo e Filtro', 30, 80.00);

INSERT INTO produto_ordem_servico (
    data_cadastro, data_ultima_atualizacao, quantidade, valor_unitario, ordem_servico_id, produto_id
) VALUES 
(NOW(), NOW(), 2, 120.50, 1, 1),
(NOW(), NOW(), 1, 35.90, 2, 2);

INSERT INTO servico_prestado (
    data_cadastro, data_ultima_atualizacao, observacoes, valor_servico_prestado, ordem_servico_id, servico_id
) VALUES 
(NOW(), NOW(), 'Sem observações', 150.00, 1, 1),
(NOW(), NOW(), 'Óleo 5W30 sintético', 80.00, 2, 2);