-- DML (Linguagem de manipulação de dados)

-- Inserindo Clientes

INSERT INTO ecommerce.cliente(nome_completo, email,senha,telefone,data_cadastro) 
VALUES('Felipe Evaristo Camargo', 'felipeevaristo323@gmail.com','123','11974310089','2025-09-02'),
('Ana Carolina Mendes', 'ana.mendes@email.com', 'senha_ana123', '21987654321', '2025-09-02'),
('Bruno Oliveira Santos', 'bruno.santos@email.com', 'senha_bruno456', '31998765432', '2025-09-03'),
('Camila Ferreira Costa', 'camila.costa@email.com', 'senha_camila789', '41976543210', '2025-09-03'),
('Daniel Rodrigues Lima', 'daniel.lima@email.com', 'senha_daniel101', '51965432109', '2025-09-04'),
('Eduarda Martins Pires', 'eduarda.pires@email.com', 'senha_eduarda202', '61954321098', '2025-09-04'),
('Fernando Silva Pereira', 'fernando.pereira@email.com', 'senha_fernando303', '71943210987', '2025-09-05'),
('Gabriela Alves Gomes', 'gabriela.gomes@email.com', 'senha_gabriela404', '81932109876', '2025-09-05'),
('Heitor Barbosa Rocha', 'heitor.rocha@email.com', 'senha_heitor505', '91921098765', '2025-09-06'),
('Isabela Correia Nunes', 'isabela.nunes@email.com', 'senha_isabela606', '11910987654', '2025-09-06'),
('João Pedro Souza', 'joao.souza@email.com', 'senha_joao707', '21909876543', '2025-09-07');


-- Inserindo Produtos
INSERT INTO ecommerce.produto(nome_produto, descricao, preco, estoque_disponivel, imagem_url)
VALUES('Coca Cola', 'Refrigerante', 7,100,'link'),
('Arroz Branco', 'Pacote de 5kg, tipo 1', 25.50, 50, 'link_arroz'),
('Feijão Carioca', 'Pacote de 1kg, tipo 1', 8.90, 75, 'link_feijao'),
('Leite Integral', 'Caixa de 1L, longa vida', 5.80, 200, 'link_leite'),
('Sabão em Pó', 'Embalagem de 1kg', 15.20, 40, 'link_sabao'),
('Azeite Extra Virgem', 'Garrafa de 500ml', 32.00, 30, 'link_azeite'),
('Café em Pó', 'Pacote de 500g, torra média', 18.50, 60, 'link_cafe'),
('Chocolate ao Leite', 'Barra de 90g', 6.50, 150, 'link_chocolate'),
('Macarrão Espaguete', 'Pacote de 500g', 4.10, 120, 'link_macarrao'),
('Shampoo Anti-caspa', 'Frasco de 300ml', 22.90, 80, 'link_shampoo'),
('Pão de Forma', 'Pacote de 500g', 7.90, 90, 'link_pao');

-- Inserindo pedidos

INSERT INTO ecommerce.pedido(id_cliente,valor_total,data_pedido, status)
VALUES (1,300,'2024-01-01 16:00:00','Pago'),
(2, 150.75, '2024-02-15 10:30:00', 'Processando'),
(3, 55.00, '2024-03-20 18:45:00', 'Enviado'),
(4, 210.90, '2024-04-10 09:15:00', 'Entregue'),
(5, 89.25, '2024-05-05 14:00:00', 'Cancelado'),
(6, 450.00, '2024-06-08 21:20:00', 'Pago'),
(7, 12.30, '2024-07-22 11:50:00', 'Processando'),
(8, 350.50, '2024-08-30 08:00:00', 'Enviado'),
(9, 78.99, '2024-09-02 16:30:00', 'Entregue'),
(10, 105.00, '2024-10-18 13:10:00', 'Pago'),
(1, 25.40, '2024-11-25 19:40:00', 'Cancelado');

-- Inserindo pagamentos
INSERT INTO ecommerce.pagamento(id_pedido, forma_pagamento, data_pagamento)
VALUES(2, 'Pix', '2024-02-15 10:35:00'),
(3, 'Boleto', '2024-03-20 18:50:00'),
(4, 'Cartão de Débito', '2024-04-10 09:20:00'),
(5, 'Cartão de Crédito', '2024-05-05 14:05:00'),
(6, 'Pix', '2024-06-08 21:25:00'),
(7, 'Boleto', '2024-07-22 12:00:00'),
(8, 'Cartão de Crédito', '2024-08-30 08:05:00'),
(9, 'Pix', '2024-09-02 16:35:00'),
(10, 'Cartão de Débito', '2024-10-18 13:15:00'),
(1, 'Cartão de Crédito', '2024-11-25 19:45:00');

-- Inserindo itens do pedido

INSERT INTO ecommerce.item_do_pedido(id_produto, id_pedido, quantidade)
VALUES (1, 2, 5),
(2, 3, 2),
(3, 4, 1),
(4, 5, 3),
(5, 6, 1),
(6, 7, 4),
(7, 8, 2),
(8, 9, 5),
(9, 10, 1),
(10, 1, 3),
(1, 2, 2);

