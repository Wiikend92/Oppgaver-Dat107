drop schema if exists oblig3_jpa cascade;

create schema oblig3_jpa;
set search_path to oblig3_jpa;


create table Ansatt (
ansNr serial,
brukernavn varchar(5) unique,
fornavn varchar not null,
etternavn varchar not null,
ansettelsesdato date,
stilling varchar,
maanedslonn decimal(8,2) not null,
sted int,
constraint ansatt_pk PRIMARY KEY (ansNr)
);

create table Avdeling(
avdId serial,
navn varchar,
sjef int,
constraint id_pk primary key (avdId),
CONSTRAINT sjef_fk FOREIGN KEY (sjef) references ansatt(ansNr)
);


CREATE TABLE Prosjekt
(
  Id        SERIAL,
  Navn      VARCHAR(30),
  Beskrivelse varchar(100),
  CONSTRAINT Prosjekt_PK PRIMARY KEY (Id)
);

CREATE TABLE Prosjektdeltagelse
(
  Prosjektdeltagelse_Id SERIAL,
  Ansatt_Id INTEGER,
  Prosjekt_Id INTEGER,
  Timer     INTEGER,
  Rolle varchar,
  CONSTRAINT Prosjektdeltagelse_PK PRIMARY KEY (Prosjektdeltagelse_Id),
  CONSTRAINT AnsattProsjekt_Unik UNIQUE (Ansatt_Id, Prosjekt_Id),
  CONSTRAINT Ansatt_FK FOREIGN KEY (Ansatt_Id) REFERENCES Ansatt(ansNr),
  CONSTRAINT Prosjekt_FK FOREIGN KEY (Prosjekt_Id) REFERENCES Prosjekt(Id)  
);



insert into Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn, sted)
values
('ph1', 'Per', 'Litzheim', '2019-02-02', 'Database', '45000', 1),
('ph2', 'Per H', 'Frøiland', '2019-02-05', 'Programmering', '40000', 1),
('ph3', 'Per H L', 'Litzheim Frøiland', '2019-02-10', 'Kabel-boms', '20000', 1),
('ph4', 'Per', 'Litzheim', '2019-02-02', 'Database', '45000', 1),
('ph5', 'Per H', 'Frøiland', '2019-02-05', 'Programmering', '40000', 1),
('ph6', 'Per H L', 'Litzheim Frøiland', '2019-02-10', 'Kabel-boms', '20000', 1),
('ph7', 'Per', 'Litzheim', '2019-02-02', 'Database', '45000', 1),
('ph8', 'Per H', 'Frøiland', '2019-02-05', 'Programmering', '40000', 1),
('ph9', 'Per H L', 'Litzheim Frøiland', '2019-02-10', 'Kabel-boms', '20000', 1),
('ph10', 'Per H L', 'Litzheim Frøiland', '2019-02-10', 'Kabel-boms', '20000', 1);


insert into Avdeling(navn, sjef)
values
('Hoved', 1),
('Reserve', 2);


INSERT INTO
Prosjekt(Navn, Beskrivelse)
VALUES
('Trivselsprosjektet', 'Prosjekt for trivsel'),
('Synergiprosjektet', 'Prosjekt for synergi'),
('Utviklingsprosjektet', 'Prosjekt for utvikling');

INSERT INTO
  Prosjektdeltagelse(Ansatt_Id, Prosjekt_Id, Timer, Rolle)
VALUES
(1, 1, 50, 'Sjef'),
(2, 1, 100, 'Kodenisse'),
(3, 2, 150, 'Sjef'),
(4, 1, 200, 'Kodenisse'),
(5, 2, 250, 'Kodenisse'),
(6, 1, 300, 'Kodenisse'),
(7, 1, 300, 'Kodenisse'),
(8, 1, 600, 'Kodenisse'),
(9, 2, 10, 'Kodenisse'),
(10, 1, 0, 'Ny');
 


ALTER TABLE Ansatt
alter Column sted  set NOT NULL,
add CONSTRAINT ansatt_avd_fk FOREIGN KEY (sted) references avdeling(avdId)
;

UPDATE Ansatt
SET sted = 2
WHERE ansNr = 2;

SELECT * FROM Ansatt;
select * from Avdeling;
select * from Prosjekt;
select * from Prosjektdeltagelse;


