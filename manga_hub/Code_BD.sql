-- Database: manga_hub

-- DROP DATABASE IF EXISTS manga_hub;

CREATE DATABASE manga_hub
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

create table usuario (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
email VARCHAR(80) NOT NULL,
senha VARCHAR(120) NOT NULL,
cep VARCHAR(8) NOT NULL,
telefone VARCHAR(12)
);



create table produto(
id SERIAL PRIMARY KEY,
nome VARCHAR(70) NOT NULL,
preco NUMERIC CHECK(preco > 0.0) NOT NULL,
estoque INT NOT NULL,
tipo_produto VARCHAR(50) NOT NULL,
genero VARCHAR(100) NOT NULL,
ISBN VARCHAR(14) NOT NULL,
imagem bytea,
id_vendedor INT REFERENCES usuario(id)
);

create table carrinho(
id SERIAL PRIMARY KEY, 
id_produto INT REFERENCES produto(id),
id_usuario INT REFERENCES usuario(id),
quantidade INT NOT NULL,
total_produto MONEY NOT NULL
);


create table pedidio (
id SERIAL PRIMARY KEY,
itens_carrinho INT REFERENCES carrinho(id),
id_usuario INT REFERENCES usuario(id),
valor_total MONEY NOT NULL,
DataPedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table avaliacao(
id SERIAL PRIMARY KEY,
id_usuario INT REFERENCES usuario(id),
comentario VARCHAR(150),
classficacao INT NOT NULL
);

create table produtosAvaliacoes  (
id_associacao SERIAL PRIMARY KEY,
id_produto INT REFERENCES produto(id),
id_avaliacao INT REFERENCES avaliacao(id),
dataAvaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


