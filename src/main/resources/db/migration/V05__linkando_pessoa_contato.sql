ALTER TABLE pessoa add COLUMN cod_contato INTEGER,
add CONSTRAINT fk_codigo_contato
FOREIGN KEY (cod_contato) REFERENCES contato (cod_contato)