-- table client
CREATE TABLE client
(
    id            SERIAL PRIMARY KEY,
    numero_client VARCHAR(255) NOT NULL,
    adresse       VARCHAR(255) NOT NULL,
    nom           VARCHAR(255) NOT NULL
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
    id_client INTEGER NOT NULL
);
-- les deux clé sont primaire car un compte peut appartenir à plusieur personne (ex. compte pour un colocation)
ALTER TABLE appartient ADD CONSTRAINT appartient_pkey PRIMARY KEY (id_compte,id_client);