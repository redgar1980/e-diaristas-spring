CREATE TABLE cidade_atendida(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    cidade varchar(255) NOT NULL,
    codigo_ibge varchar(255) NOT NULL,
    estado varchar(255) NOT NULL,
    PRIMARY KEY (id)  
);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE cidades_atendidas_usuarios (
    cidade_atendida_id bigint NOT NULL,
    usuario_id bigint NOT NULL
    CREATE INDEX (usuario_id)
    CREATE INDEX (cidade_atendida_id),
    CONSTRAINT FOREIGN CREATE INDEX (cidade_atendida_id) REFERENCES cidade_atendida (id),
    CONSTRAINT FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);