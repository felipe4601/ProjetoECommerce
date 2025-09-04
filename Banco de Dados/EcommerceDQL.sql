-- DQL (Linguagem de Consulta de dados)
-- 1. Listagem Simples
SELECT nome_completo, email
FROM ecommerce.cliente;

-- 2. Filtragem com Condição Numérica
SELECT *
FROM ecommerce.produto
WHERE preco > 1000;

-- 3. Filtragem com Padrão de Texto
SELECT *
FROM ecommerce.cliente
WHERE telefone LIKE '%11';

-- 4. Filtro com Múltiplas Condições
SELECT * 
FROM ecommerce.produto
WHERE preco < 500 AND estoque_disponivel > 50;

-- 5. Ordenação de Resultados
SELECT *
FROM ecommerce.produto
ORDER BY preco ASC;

-- 6. Limitação de Resultados
SELECT *
FROM ecommerce.cliente
ORDER BY data_cadastro ASC
LIMIT 2;

-- 7. Contagem Simples (COUNT)
SELECT COUNT(id_produto) AS produtos_cadastrados
FROM ecommerce.produto;

--8. Média de Valores (AVG) 
SELECT AVG(preco) AS media_dos_produtos
FROM ecommerce.produto;
-- INTERMEDIÁRIOS
-- 1. Junção Simples(JOIN)
SELECT id_pedido, nome_completo, valor_total
FROM ecommerce.pedido AS ep 
JOIN ecommerce.cliente AS ec
ON ep.id_cliente = ec.id_cliente;

-- 2. Junção com Filtro
SELECT eip.id_pedido, ep.nome_produto 
FROM ecommerce.item_do_pedido AS eip
JOIN ecommerce.produto AS ep
ON eip.id_produto = ep.id_produto
JOIN ecommerce.pedido AS epr
ON epr.id_pedido = eip.id_pedido;

-- 3. Agrupamento e Contagem (GROUP BY e COUNT)
SELECT COUNT(p.id_pedido), c.nome_completo
FROM ecommerce.cliente AS c
JOIN ecommerce.pedido AS p
ON c.id_cliente = p.id_cliente
GROUP BY c.nome_completo;

-- 4. Agrupamento e Soma (GROUP BY e SUM)
SELECT SUM(p.valor_total), c.nome_completo
FROM ecommerce.cliente AS c
JOIN ecommerce.pedido AS p
ON c.id_cliente = p.id_cliente
GROUP BY c.nome_completo;

-- 1. Junção Múltipla
SELECT ec.nome_completo, epr.nome_produto, eip.quantidade, ep.data_pedido
FROM ecommerce.pedido AS ep
JOIN ecommerce.cliente AS ec
ON ep.id_cliente = ec.id_cliente
JOIN ecommerce.item_do_pedido AS eip
ON eip.id_pedido = ep.id_pedido
JOIN ecommerce.produto AS epr
ON epr.id_produto = eip.id_produto;

-- 2. Filtro de Grupos (HAVING)
SELECT c.nome_completo, SUM(p.valor_total)
FROM ecommerce.cliente AS c
JOIN ecommerce.pedido AS p
ON c.id_cliente = p.id_cliente
GROUP BY c.nome_completo
HAVING SUM(p.valor_total) > 5000;

-- 3. Agregação com Ordenação
SELECT p.nome_produto, COUNT(ep.id_produto)
FROM ecommerce.produto AS p
JOIN ecommerce.item_do_pedido AS ep
ON p.id_produto = ep.id_produto
GROUP BY p.id_produto
ORDER BY COUNT(p.id_produto) DESC
LIMIT 10;

-- 4. Junção Externa (LEFT JOIN)


