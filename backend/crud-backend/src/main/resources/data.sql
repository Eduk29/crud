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
