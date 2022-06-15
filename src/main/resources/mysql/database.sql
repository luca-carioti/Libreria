
CREATE SCHEMA libreria;
USE libreria;

CREATE TABLE autore (
                      id INTEGER AUTO_INCREMENT PRIMARY KEY,
                      nome VARCHAR(50) NOT NULL ,
                      cognome VARCHAR(50) NOT NULL ,
                      descrizione VARCHAR  ,
                      data_nascita DATE  ,
                      città VARCHAR(90)  ,
                      immagine BLOB
);

CREATE TABLE editore (
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50) NOT NULL ,
                         descrizione VARCHAR  ,
                         immagine BLOB
);

CREATE TABLE regista (
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50) NOT NULL ,
                         cognome VARCHAR(50) NOT NULL ,
                         descrizione VARCHAR  ,
                         data_nascita DATE  ,
                         città VARCHAR(90)  ,
                         immagine BLOB
);

CREATE TABLE attore (
                        id INTEGER AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(50) NOT NULL ,
                        cognome VARCHAR(50) NOT NULL,
                        descrizione VARCHAR  ,
                        data_nascita DATE  ,
                        città VARCHAR(90)  ,
                        immagine BLOB

);

CREATE TABLE artista (
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50) NOT NULL,
                         cognome VARCHAR(50) NOT NULL,
                         descrizione VARCHAR  ,
                         data_nascita DATE  ,
                         città VARCHAR(90)  ,
                         immagine BLOB
);

CREATE TABLE cliente (
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(50) NOT NULL,
                         cognome VARCHAR(50) NOT NULL,
                         descrizione VARCHAR  ,
                         data_nascita DATE  ,
                         immagine BLOB ,
                         mail VARCHAR (50) NOT NULL ,
                         telefono VARCHAR (20) NOT NULL,
                         indirizzo VARCHAR (50) NOT NULL

);

CREATE TABLE reparto (
                        id INTEGER AUTO_INCREMENT PRIMARY KEY ,
                        nome VARCHAR (50) UNIQUE NOT NULL,
                        descrizione VARCHAR
);

CREATE TABLE libro (
                        id INTEGER AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR UNIQUE NOT NULL ,
                        descrizione VARCHAR (250) ,
                        immagine BLOB ,
                        prezzo FLOAT NOT NULL ,
                        quantità INTEGER NOT NULL,
                        disponibilità ENUM('IMMEDIATA','PROSSIME USCITE','IN PRENOTAZIONE' ) DEFAULT ('IMMEDIATA'),
                        reparto INTEGR NOT NULL,
                        editore INTEGER UNIQUE NOT NULL ,
                        data_pubblicazione DATE UNIQUE NOT NULL ,
                        FOREIGN KEY (reparto) REFERENCES reparto(id),
                        FOREIGN KEY (editore) REFERENCES editore(id)

);

CREATE TABLE film (
                      id INTEGER AUTO_INCREMENT PRIMARY KEY ,
                      nome VARCHAR (50) UNIQUE NOT NULL,
                      descrizione VARCHAR ,
                      immagine BLOB ,
                      prezzo FLOAT NOT NULL,
                      quantità INTEGER DEFAULT ('0'),
                      disponibilità ENUM('IMMEDIATA','PROSSIME USCITE','IN PRENOTAZIONE' ) DEFAULT ('IMMEDIATA'),
                      reparto INTEGER ,
                      editore INTEGER ,
                      data_pubblicazione DATE NOT NULL,
                      FOREIGN KEY (reparto) REFERENCES reparto(id),
                      FOREIGN KEY (editore) REFERENCES editore(id)
);

CREATE TABLE cd (
                        id INTEGER AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR (50) UNIQUE NOT NULL,
                        descrizione VARCHAR ,
                        immagine BLOB ,
                        prezzo FLOAT NOT NULL,
                        quantità INTEGER NOT NULL DEFAULT ('0'),
                        disponibilità ENUM('IMMEDIATA','PROSSIME USCITE','IN PRENOTAZIONE' ) DEFAULT ('IMMEDIATA'),
                        reparto INTEGER ,
                        editore INTEGER,
                        data_pubblicazione DATE NOT NULL,
                        FOREIGN KEY (reparto) REFERENCES reparto(id),
                        FOREIGN KEY (editore) REFERENCES editore(id)
);

CREATE TABLE acquisto (
                        id INTEGER AUTO_INCREMENT PRIMARY KEY,
                        cliente INTEGER,
                        data DATE NOT NULL,
                        FOREIGN KEY (cliente) REFERENCES cliente(id)
);
