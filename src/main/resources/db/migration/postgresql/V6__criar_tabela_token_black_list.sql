CREATE TABLE token_black_list (
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    token varchar(255) NOT NULL,
    PRIMARY KEY (id)
)