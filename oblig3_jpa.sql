drop schema if exists oblig3_jpa cascade;

create schema oblig3_jpa;
set search_path to oblig3_jpa;


create table ansatt (
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

create table avdeling(
id serial,
navn varchar,
sjef int,
constraint id_pk primary key (id),
CONSTRAINT sjef_fk FOREIGN KEY (sjef) references ansatt(ansNr)
);



insert into ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn, sted)
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


insert into avdeling(navn, sjef)
values
('Hoved', 1),
('Reserve', 2);

ALTER TABLE ansatt
alter Column sted  set NOT NULL,
add CONSTRAINT ansatt_fk FOREIGN KEY (sted) references avdeling(id)
;

SELECT * FROM ansatt;
