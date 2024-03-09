drop database digitalisierungAusbildungsnachweis;
create database digitalisierungAusbildungsnachweis;
use digitalisierungAusbildungsnachweis;


create table if not exists eintrag
(
    idEintrag         int auto_increment primary key,
    ausgefuerteArbeit varchar(45) not null,
    einzelStunden     varchar(45) not null
);

create table if not exists tag
(
    idEintrag int not null primary key ,
    idTag int not null,
    constraint tag_ibfk_1
    foreign key (idEintrag) references eintrag (idEintrag)
    );

create table if not exists person
(
    idPerson     int auto_increment
    primary key,
    username     varchar(32)  not null,
    email        varchar(255) not null,
    passwordhash varchar(255) not null,
    vorname      varchar(32)  not null,
    nachname     varchar(32)  not null,
    rolle        int(1)       not null comment '1 = Pruefer/Lehrer; 2 = Ausbilder; 3 = auszubildender'
    );

create table if not exists ausbildung
(
    idAusbildung     int unsigned auto_increment
    primary key,
    auszubildenderId int         not null,
    ausbilderId      int         not null,
    beruf            varchar(45) not null,
    startZeitpunkt   date        not null,
    endZeitpunkt     varchar(45) null,
    constraint ausbildung_ibfk_1
    foreign key (auszubildenderId) references person (idPerson),
    constraint ausbildung_ibfk_2
    foreign key (ausbilderId) references person (idPerson)
    );

create index ausbilderId
    on ausbildung (ausbilderId);

create index auszubildenderId
    on ausbildung (auszubildenderId);

create table if not exists nachweis
(
    idNachweis              int auto_increment
    primary key,
    zurQuittierung          tinyint     not null,
    auszubildenderId        int         not null,
    startAusbildungswoche   date        not null,
    eintragMontagId         int         not null,
    eintagDienstagId        int         not null,
    eintagMittwochId        int         not null,
    eintagDonnerstagId      int         not null,
    eintragFreitagId        int         not null,
    eintragSamstagId        int         null,
    abteilungMontag varchar(255) not null,
    abteilungDienstag varchar(255) not null,
    abteilungMittwoch varchar(255) not null,
    abteilungDonnerstag varchar(255) not null,
    abteilungFreitag varchar(255) not null,
    abteilungSamstag varchar(255) not null
);

create index auszubildenderId
    on nachweis (auszubildenderId);

create index eintagDienstagId
    on nachweis (eintagDienstagId);

create index eintagDonnerstagId
    on nachweis (eintagDonnerstagId);

create index eintagMittwochId
    on nachweis (eintagMittwochId);

create index eintragFreitagId
    on nachweis (eintragFreitagId);

create index eintragMontagId
    on nachweis (eintragMontagId);

create index eintragSamstagId
    on nachweis (eintragSamstagId);

create table if not exists quittierung
(
    idQuittierung int auto_increment
    primary key,
    nachweisId    int         not null,
    quittiert     tinyint     not null,
    kommentar     varchar(45) null,
    constraint quittierung_ibfk_1
    foreign key (nachweisId) references nachweis (idNachweis)
    );

create index nachweisId
    on quittierung (nachweisId);
