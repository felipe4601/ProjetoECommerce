-- DQL Consulta de banco de dados

SELECT nome_completo, telefone
FROM ecommerce.cliente;

SELECT data_consulta, valor, nome
FROM clinica.consulta AS cs
JOIN clinica.medico AS med
ON cs.medico_id = med.id_medico;

-- Junção simples

SELECT id_pedido, nome_completo
FROM ecommerce.pedido AS ed
JOIN ecommerce.cliente AS ec
ON ed.cliente_id = ec.id_cliente;

-- Junção com filtro

SELECT id_pedido, nome_produto, quantidade
FROM ecommerce.item_produto AS eip
JOIN ecommerce.produto AS epr
ON eip.produto_id = epr.id_produto
JOIN ecommerce.pedido AS epe
ON epe.id_pedido = eip.pedido_id
WHERE eip.pedido_id = 1;

-- Agrupamento e Contagem