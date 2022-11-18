CREATE TABLE endereco(
	cod_endereco SERIAL CONSTRAINT pk_cod_endereco PRIMARY KEY,
	cep VARCHAR(10),
	cidade VARCHAR(30) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	rua VARCHAR(30) NOT NULL,
	numero VARCHAR(30),
	complemento VARCHAR(30),
	cod_pessoa INTEGER NOT NULL,
	FOREIGN KEY(cod_pessoa) REFERENCES pessoa(cod_pessoa) ON DELETE CASCADE
);