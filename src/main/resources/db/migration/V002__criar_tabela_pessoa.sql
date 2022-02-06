CREATE TABLE pessoa (
    id serial not null primary key,
    nome varchar(50) not null,
    status boolean not null default true,
    logradouro varchar,
    numero varchar,
    complemento varchar,
    bairro varchar,
    cep varchar,
    cidade varchar,
    estado varchar
);