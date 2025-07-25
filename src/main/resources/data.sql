INSERT INTO SYS_USUARIO (ID, NOME, LOGIN, SENHA, EMAIL, SITUACAO) VALUES (1, 'Nathan', 'nathan', '123', 'nathan@gmail.com', 'ATIVO');

-- É necessario utilizar o comando abaixo para o PostgreSQL poder iniciar corretamente os IDs
ALTER SEQUENCE SYS_USUARIO_ID_SEQ RESTART WITH 2;

INSERT INTO SYS_PERFIL (ID, DESCRICAO) VALUES (1, 'Administrador');
INSERT INTO SYS_PERFIL (ID, DESCRICAO) VALUES (2, 'Gerente');
INSERT INTO SYS_PERFIL (ID, DESCRICAO) VALUES (3, 'Cliente');

ALTER SEQUENCE SYS_PERFIL_SEQ RESTART WITH 4;

INSERT INTO SYS_RECURSO (ID, NOME, CHAVE) VALUES (1, 'Tela Usuário', 'usuario');
INSERT INTO SYS_RECURSO (ID, NOME, CHAVE) VALUES (2, 'Tela Perfil', 'perfil');
INSERT INTO SYS_RECURSO (ID, NOME, CHAVE) VALUES (3, 'Tela Recurso', 'recurso');

ALTER SEQUENCE SYS_RECURSO_ID_SEQ RESTART WITH 4;