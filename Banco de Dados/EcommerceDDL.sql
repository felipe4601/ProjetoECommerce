<<<<<<< HEAD
CREATE SCHEMA ecommerce;
-- CREATE pode ser usado em; Schema, tabela)
USE SCHEMA Ecommerce;

CREATE TABLE ecommerce.produto(
	id_produto int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome_produto text not null,
	descricao text not null,
	preco numeric(5,4) not null,
	estoque_disponivel int not null,
	imagem_url text not null
);

CREATE TABLE ecommerce.cliente(
	id_pedido int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome_completo text not null,
	email text not null,
	senha text not null, 
	telefone text not null,
	data_cadastro TIMESTAMP WITH TIME ZONE
);


CREATE TABLE ecommerce.pedido(
	id_pedido int PRIMARY KEY GENERATED ALW,
	cliente_id int not null,
	data_pedido timestamp with time zone,
	valor_total numeric(5,4),
	status text not null,
	FOREIGN KEY(cliete_id) REFERENCES cliente(id_cliente)
);

CREATE TABLE ecommerce.pagamento(
	id_pagamento INT PRIMARY KEY SERIAL,
	pedido_id INT NOT NULL,
	form_pagamento TEXT NOT NULL,
	status TEXT NOT NULL,
	data_pagamento TIMESTAMP WITH TIME ZONE
);



CREATE TABLE ecommerce.item_produto(
	id_item_produto int,
	produto_id int not null,
	pedido_id int not null,
	quantidade int not null,
	FOREIGN KEY (produto_id) REFERENCES produto(id_produto)
);
=======
-- DDL Ecommerce 
CREATE SCHEMA ecommerce;

CREATE TABLE ecommerce.cliente(
	id_cliente INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome_completo TEXT NOT NULL,
	email TEXT NOT NULL,
	senha TEXT NOT NULL,
	telefone TEXT NOT NULL,
	data_cadastro TIMESTAMPTZ
);

CREATE TABLE ecommerce.produto(
	id_produto INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome_produto TEXT NOT NULL,
	descricao TEXT NOT NULL,
	preco NUMERIC(10,4),
	estoque_disponivel INT NOT NULL,
	imagem_url TEXT NOT NULL
);

CREATE TABLE ecommerce.pedido(
	id_pedido INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	id_cliente INT NOT NULL,
	data_pedido TIMESTAMPTZ,
	valor_total NUMERIC(10,4),
	status TEXT NOT NULL
);

CREATE TABLE ecommerce.item_do_pedido(
	id_item_do_pedido INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	id_produto INT NOT NULL,
	id_pedido INT NOT NULL,
	quantidade INT NOT NULL
);

CREATE TABLE ecommerce.pagamento(
	id_pagamento INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	id_pedido INT NOT NULL,
	forma_pagamento TEXT NOT NULL,
	status TEXT NOT NULL,
	data_pagamento TIMESTAMPTZ NOT NULL	
);

ALTER TABLE ecommerce.pagamento
DROP COLUMN status;
>>>>>>> ab8354c0bbaa918c3eb7575dbe4d7d4082c736e9
