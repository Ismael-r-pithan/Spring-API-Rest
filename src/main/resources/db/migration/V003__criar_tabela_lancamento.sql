CREATE TABLE lancamento (
    id serial not null primary key,
    descricao varchar(50) not null,
    data_vencimento date not null,
    data_pagamento date,
    valor numeric(10, 2) not null,
    observacao varchar(100),
    tipo varchar(20),
    categoria integer not null,
    pessoa integer not null,
    foreign key (categoria) references categoria(id),
    foreign key (pessoa) references pessoa(id)
);
