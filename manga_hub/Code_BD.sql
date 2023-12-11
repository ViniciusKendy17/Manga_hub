CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    cep VARCHAR(10),
    telefone VARCHAR(15)
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    preco DOUBLE PRECISION,
    estoque INT,
    genero VARCHAR(255),
    tipo_produto VARCHAR(255),
    isbn VARCHAR(255),
    imagem VARCHAR(255),
    id_vendedor INT,
	
    FOREIGN KEY (id_vendedor) REFERENCES usuario (id)
);

CREATE TABLE carrinho (
    id SERIAL PRIMARY KEY,
    total DOUBLE PRECISION,
	
	id_usuario BIGINT REFERENCES usuario(id)
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    id_produto BIGINT REFERENCES produto(id),
    quantidade INT,
    total DOUBLE PRECISION,
	
    id_carrinho BIGINT REFERENCES carrinho(id)
);

CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    id_cliente BIGINT,
    data_criacao TIMESTAMP,
    total DOUBLE PRECISION,
    status VARCHAR(255),
	
	FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);



