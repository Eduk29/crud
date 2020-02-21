SET NAMES utf8;

-- Pessoas

INSERT INTO CRUD_PESSOA (id_pessoa, nome_pessoa, genero_pessoa) VALUES (SEQ_CRUD_PESSOA.nextval, 'Jose Eduardo Trindade E Marques', 'Masculino');
INSERT INTO CRUD_PESSOA (id_pessoa, nome_pessoa, genero_pessoa) VALUES (SEQ_CRUD_PESSOA.nextval, 'Sarah De Villio', 'Feminino');
INSERT INTO CRUD_PESSOA (id_pessoa, nome_pessoa, genero_pessoa) VALUES (SEQ_CRUD_PESSOA.nextval, 'Marcelo Molina', 'Masculino');

-- Documentos

INSERT INTO CRUD_DOCUMENTO (id_documento, valor_documento, id_pessoa, id_tipo_documento) VALUES (SEQ_CRUD_DOCUMENTO.nextval, '99924577774', 1, 2);
INSERT INTO CRUD_DOCUMENTO (id_documento, valor_documento, id_pessoa, id_tipo_documento) VALUES (SEQ_CRUD_DOCUMENTO.nextval, '875698875', 2, 1);
INSERT INTO CRUD_DOCUMENTO (id_documento, valor_documento, id_pessoa, id_tipo_documento) VALUES (SEQ_CRUD_DOCUMENTO.nextval, '42765998632', 2, 2);
INSERT INTO CRUD_DOCUMENTO (id_documento, valor_documento, id_pessoa, id_tipo_documento) VALUES (SEQ_CRUD_DOCUMENTO.nextval, '325488836', 3, 1);
INSERT INTO CRUD_DOCUMENTO (id_documento, valor_documento, id_pessoa, id_tipo_documento) VALUES (SEQ_CRUD_DOCUMENTO.nextval, '99988877766', 3, 2);

-- Tipo de Documentos

INSERT INTO CRUD_TIPO_DOCUMENTO (id_tipo_documento, valor_tipo_documento, chave_tipo_documento) VALUES (SEQ_CRUD_TIPO_DOCUMENTO.nextval, 'RG', 'RG');
INSERT INTO CRUD_TIPO_DOCUMENTO (id_tipo_documento, valor_tipo_documento, chave_tipo_documento) VALUES (SEQ_CRUD_TIPO_DOCUMENTO.nextval, 'CPF', 'CPF');

-- Enderecos

INSERT INTO CRUD_ENDERECO (id_endereco, cep_endereco, cidade_endereco, complemento_endereco, estado_endereco, logradouro_endereco, numero_endereco) VALUES (SEQ_CRUD_ENDERECO.nextval, '02080050', 'Sao Paulo', NULL, 'SP', 'Rua Mascote Feliz', '12');
INSERT INTO CRUD_ENDERECO (id_endereco, cep_endereco, cidade_endereco, complemento_endereco, estado_endereco, logradouro_endereco, numero_endereco) VALUES (SEQ_CRUD_ENDERECO.nextval, '02569180', 'Sao Paulo', NULL, 'SP', 'Rua Biritiba', '2562');
INSERT INTO CRUD_ENDERECO (id_endereco, cep_endereco, cidade_endereco, complemento_endereco, estado_endereco, logradouro_endereco, numero_endereco) VALUES (SEQ_CRUD_ENDERECO.nextval, '13258540', 'Sao Paulo', NULL, 'SP', 'Rua Luis Carlos Mejialos', '149');

-- Relacionamento Pessoa Endereco

INSERT INTO CRUD_PESSOA_ENDERECO (id_pessoa, id_endereco) VALUES (1, 1);
INSERT INTO CRUD_PESSOA_ENDERECO (id_pessoa, id_endereco) VALUES (2, 2);
INSERT INTO CRUD_PESSOA_ENDERECO (id_pessoa, id_endereco) VALUES (3, 3);

-- Contato

INSERT INTO CRUD_CONTATO (id_contato, id_pessoa, id_tipo_contato, valor_contato, is_principal_contato) VALUES (SEQ_CRUD_CONTATO.nextval, 1, 1, '11979830357', TRUE);
INSERT INTO CRUD_CONTATO (id_contato, id_pessoa, id_tipo_contato, valor_contato, is_principal_contato) VALUES (SEQ_CRUD_CONTATO.nextval, 1, 2, '1122366944', FALSE);
INSERT INTO CRUD_CONTATO (id_contato, id_pessoa, id_tipo_contato, valor_contato, is_principal_contato) VALUES (SEQ_CRUD_CONTATO.nextval, 2, 1, '11954782354', TRUE);
INSERT INTO CRUD_CONTATO (id_contato, id_pessoa, id_tipo_contato, valor_contato, is_principal_contato) VALUES (SEQ_CRUD_CONTATO.nextval, 3, 1, '11956332569', TRUE);