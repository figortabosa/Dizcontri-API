ALTER TABLE pessoa add COLUMN cod_endereco INTEGER,
add CONSTRAINT fk_codigo_endereco
FOREIGN KEY (cod_endereco) REFERENCES endereco (cod_endereco)