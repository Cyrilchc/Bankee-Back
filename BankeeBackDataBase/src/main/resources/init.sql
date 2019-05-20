-- table client
CREATE TABLE client
(
    id            SERIAL PRIMARY KEY,
    numero_client VARCHAR(255) NOT NULL,
    adresse       VARCHAR(255) NOT NULL,
    nom           VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL
);

-- table compte
CREATE TABLE compte
(
    id                 SERIAL PRIMARY KEY,
    numero_compte      VARCHAR(255) NOT NULL,
    solde              DECIMAL DEFAULT 0,
    decouvert_autorise DECIMAL DEFAULT NULL
);

-- table appartient
CREATE TABLE appartient
(
    id_compte INTEGER NOT NULL,
    id_client INTEGER NOT NULL,
    PRIMARY KEY (id_compte, id_client),
    FOREIGN KEY (id_compte) REFERENCES compte (id),
    FOREIGN KEY (id_client) REFERENCES client (id)
);

-- table mouvement
CREATE TABLE mouvement
(
    id             SERIAL PRIMARY KEY,
    date_mouvement DATE DEFAULT CURRENT_DATE,
    compte_id      INTEGER NOT NULL,
    somme          INTEGER NOT NULL,
    debit          BOOLEAN NOT NULL,
    lib            VARCHAR(255)
);

INSERT INTO compte(numero_compte, solde, decouvert_autorise)
values ('1234', 100, 100),
       ('1235', -50, 100),
       ('1236', 40, 100),
       ('1237', 200, 100);

INSERT INTO compte(numero_compte, solde)
values ('1238', 100),
       ('1239', 50),
       ('1240', 40),
       ('1241', 200);

INSERT INTO client(numero_client, adresse, nom, password)
VALUES ('123456', 'Metz', 'DUPOND', '1234'),
       ('123457', 'Metz', 'DUBOIS', '1234'),
       ('123458', 'Paris', 'DUCHAMPS', '1234');

INSERT INTO appartient(id_compte, id_client)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 1),
       (5, 2),
       (6, 3),
       (7, 1),
       (8, 2),
       (1, 3);

INSERT INTO mouvement(compte_id, somme, debit, lib)
VALUES (1, 50, true, 'Mc. Do'),
       (1, 200, true, 'Ikea'),
       (1, 1523.16, false, 'La bonne Paye');
