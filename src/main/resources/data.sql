DROP TABLE IF EXISTS TB_USUARIO;
DROP TABLE IF EXISTS TB_CARGO;
DROP TABLE IF EXISTS TB_PERFIL;

-- Criar Tabelas
CREATE TABLE TB_USUARIO(
    id_usuario NUMBER IDENTITY NOT NULL PRIMARY KEY,
    numero_cpf VARCHAR2(12) NOT NULL,
    nome_usuario VARCHAR2(70) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR2(100),
    senha VARCHAR2(100),
    sexo CHAR(1) NOT NULL,
    status_cadastro CHAR(1) NOT NULL,
    data_criacao DATE,
    data_atualizacao DATE,
    id_cargo_usuario NUMBER,
    id_perfil_usuario NUMBER
);

CREATE TABLE TB_CARGO(
    id_cargo NUMBER IDENTITY NOT NULL PRIMARY KEY,
    descricao VARCHAR2(20) NOT NULL
);

CREATE TABLE TB_PERFIL(
    id_perfil NUMBER IDENTITY NOT NULL PRIMARY KEY,
    descricao VARCHAR2(20) NOT NULL
);

-- Criar Links
ALTER TABLE TB_USUARIO ADD CONSTRAINT usuario_cargo_fk  FOREIGN KEY (id_cargo_usuario)  REFERENCES TB_CARGO(id_cargo);
ALTER TABLE TB_USUARIO ADD CONSTRAINT usuario_perfil_fk FOREIGN KEY (id_perfil_usuario) REFERENCES TB_PERFIL(id_perfil);

-- Inserts
INSERT INTO TB_CARGO(id_cargo, descricao) VALUES (default, 'Analista RH');
INSERT INTO TB_CARGO(id_cargo, descricao) VALUES (default, 'Desenvolvedor');
INSERT INTO TB_CARGO(id_cargo, descricao) VALUES (3, 'Gerente');

INSERT INTO TB_PERFIL(id_perfil, descricao) VALUES (default, 'ADM');
INSERT INTO TB_PERFIL(id_perfil, descricao) VALUES (default, 'Suporte');
INSERT INTO TB_PERFIL(id_perfil, descricao) VALUES (default, 'Analista');

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '35168470009', 'Peter Benjamin Parker', TO_DATE('15/08/1962', 'dd/MM/yyyy'), 'peter.parker@marvel.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq', 'M', 'A', sysdate, null, 2, 1);

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '07350695063', 'Scott Lang', TO_DATE('27/01/1962', 'dd/MM/yyyy'), 'scott.lang@marvel.com', '123', 'M', 'A', sysdate, null, 2, 1); 

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '90129304050', 'Anna Marie LeBeau', TO_DATE('01/10/1981', 'dd/MM/yyyy'), 'anna.lebeau@marvel.com', '123', 'F', 'A', sysdate, null, 1, 1); 

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '22927078025', 'Ororo Munroe', TO_DATE('01/05/1975', 'dd/MM/yyyy'), 'ororo.munroe@marvel.com', '123', 'F', 'A', sysdate, null, 1, 1); 

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '03073736069', 'Carol Susan Jane Danvers', TO_DATE('01/01/1977', 'dd/MM/yyyy'), 'carol.danvers@marvel.com', '123', 'F', 'A', sysdate, null, 1, 1); 

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '06040267062', 'Janet Van Dyne', TO_DATE('01/06/1963', 'dd/MM/yyyy'), 'janet.v.dyne@marvel.com', '123', 'F', 'A', sysdate, null, 3, 1); 

INSERT INTO TB_USUARIO(id_usuario, numero_cpf, nome_usuario, data_nascimento, email, senha, sexo, status_cadastro, data_criacao, data_atualizacao, id_cargo_usuario, id_perfil_usuario)
VALUES (default, '54893785060', 'Riri Williams', TO_DATE('01/05/2016', 'dd/MM/yyyy'), 'ririw.iron.heart@marvel.com', '123', 'F', 'A', sysdate, null, 2, 1);