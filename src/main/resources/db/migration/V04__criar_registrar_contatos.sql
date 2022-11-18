CREATE TABLE contato(
	cod_contato SERIAL CONSTRAINT pk_cod_contato PRIMARY KEY,
	telefone VARCHAR(30),
	email VARCHAR(30),
	cod_pessoa INTEGER NOT NULL,
	FOREIGN KEY(cod_pessoa) REFERENCES pessoa(cod_pessoa) ON DELETE CASCADE
);