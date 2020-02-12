-- Pessoas

INSERT INTO CRUD_PESSOA (id_pessoa, nome_pessoa, genero_pessoa) VALUES (SEQ_CRUD_PESSOA.nextval, 'Jose Eduardo Trindade E Marques', 'Masculino');
INSERT INTO CRUD_PESSOA (id_pessoa, nome_pessoa, genero_pessoa) VALUES (SEQ_CRUD_PESSOA.nextval, 'Sarah De Villio', 'Feminino');
INSERT INTO CRUD_PESSOA (id_pessoa, nome_pessoa, genero_pessoa) VALUES (SEQ_CRUD_PESSOA.nextval, 'Marcelo Molina', 'Masculino');

-- Documentos

INSERT INTO CRUD_DOCUMENTO (id_documento, tipo_documento, valor_documento, id_pessoa) VALUES (SEQ_CRUD_DOCUMENTO.nextval, 'CPF', '99988877766', 1);
INSERT INTO CRUD_DOCUMENTO (id_documento, tipo_documento, valor_documento, id_pessoa) VALUES (SEQ_CRUD_DOCUMENTO.nextval, 'RG', '875698875', 2);
INSERT INTO CRUD_DOCUMENTO (id_documento, tipo_documento, valor_documento, id_pessoa) VALUES (SEQ_CRUD_DOCUMENTO.nextval, 'CPF', '42765998632', 2);
INSERT INTO CRUD_DOCUMENTO (id_documento, tipo_documento, valor_documento, id_pessoa) VALUES (SEQ_CRUD_DOCUMENTO.nextval, 'RG', '325488836', 3);
INSERT INTO CRUD_DOCUMENTO (id_documento, tipo_documento, valor_documento, id_pessoa) VALUES (SEQ_CRUD_DOCUMENTO.nextval, 'CPF', '99988877766', 3);