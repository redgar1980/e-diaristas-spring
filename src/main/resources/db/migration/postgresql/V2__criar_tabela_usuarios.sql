CREATE TABLE usuario (
  id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
  email varchar(255) NOT NULL,
  nome_completo varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  tipo_usuario varchar(8) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT UK_5171l57faosmj8myawaucatdw UNIQUE  (email)
);