-- Inserts básicos
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