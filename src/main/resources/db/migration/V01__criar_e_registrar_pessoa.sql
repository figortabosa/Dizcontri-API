CREATE TABLE pessoa(
	cod_pessoa SERIAL CONSTRAINT pk_cod_pessoa PRIMARY KEY,
	nome VARCHAR(64) NOT NULL,
	cpf VARCHAR(15) UNIQUE,
	data_nascimento DATE,
	sexo VARCHAR(1)
);