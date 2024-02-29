CREATE SCHEMA IF NOT EXISTS digitalisierungAusbildungsnachweis DEFAULT CHARACTER SET utf8 ;
USE digitalisierungAusbildungsnachweis ;

-- -----------------------------------------------------
-- Loescht alle Tabellen falls sie exestieren
-- -----------------------------------------------------
DROP TABLE IF EXISTS digitalisierungAusbildungsnachweis.Quittierung ;
DROP TABLE IF EXISTS digitalisierungAusbildungsnachweis.Nachweis ;
DROP TABLE IF EXISTS digitalisierungAusbildungsnachweis.Ausbildung ;
DROP TABLE IF EXISTS digitalisierungAusbildungsnachweis.Eintrag ;
DROP TABLE IF EXISTS digitalisierungAusbildungsnachweis.Person ;

-- -----------------------------------------------------
-- Table digitalisierungAusbildungsnachweis.Person
-- -----------------------------------------------------
-- Falls eine 2.Ausbildung ansteht wird ein neuer User erstellt.
CREATE TABLE IF NOT EXISTS digitalisierungAusbildungsnachweis.Person (
  idPerson INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(32) NOT NULL,
  email VARCHAR(255) NOT NULL,
  passwordHash VARCHAR(32) NOT NULL,
  vorname VARCHAR(32) NOT NULL,
  nachname VARCHAR(32) NOT NULL,
  rolle INT(1) NOT NULL COMMENT '1 = Pruefer/Lehrer; 2 = Ausbilder; 3 = auszubildender',
  
  -- PK
  PRIMARY KEY (idPerson)
  );
  
  -- -----------------------------------------------------
-- Table digitalisierungAusbildungsnachweis.Eintrag
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS digitalisierungAusbildungsnachweis.Eintrag (
  idEintrag INT NOT NULL AUTO_INCREMENT,
  ausgefuerteArbeit VARCHAR(45) NOT NULL,
  einzelStunden VARCHAR(45) NOT NULL,
  gesamtStunden INT(2) NOT NULL,
  abteilung VARCHAR(45) NOT NULL,
  
  -- PK
  PRIMARY KEY (idEintrag)
  );


-- -----------------------------------------------------
-- Table digitalisierungAusbildungsnachweis.Ausbildung
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS digitalisierungAusbildungsnachweis.Ausbildung (
  idAusbildung INT UNSIGNED NOT NULL AUTO_INCREMENT,
  auszubildenderId INT NOT NULL,
  ausbilderId INT NOT NULL,
  beruf VARCHAR(45) NOT NULL,
  startZeitpunkt DATE NOT NULL,
  endZeitpunkt VARCHAR(45) NULL,
  
  -- PK
  PRIMARY KEY (idAusbildung),
  
  -- FK
  FOREIGN KEY (auszubildenderId) REFERENCES digitalisierungAusbildungsnachweis.Person (idPerson) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (ausbilderId) REFERENCES digitalisierungAusbildungsnachweis.Person (idPerson) ON DELETE NO ACTION ON UPDATE NO ACTION
  );


-- -----------------------------------------------------
-- Table digitalisierungAusbildungsnachweis.Nachweis
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS digitalisierungAusbildungsnachweis.Nachweis (
  idNachweis INT NOT NULL AUTO_INCREMENT,
  zurQuittierung TINYINT NOT NULL,
  auszubildenderId INT NOT NULL,
  ggfAusbildungsAbteilung VARCHAR(45) NULL,
  startAusbildungswoche DATE NOT NULL,
  eintragMontagId INT NOT NULL,
  eintagDienstagId INT NOT NULL,
  eintagMittwochId INT NOT NULL,
  eintagDonnerstagId INT NOT NULL,
  eintragFreitagId INT NOT NULL,
  eintragSamstagId INT NULL,
  
  -- PK
  PRIMARY KEY (idNachweis),
  
  -- FK Azubi
  FOREIGN KEY (auszubildenderId) REFERENCES digitalisierungAusbildungsnachweis.Person (idPerson) ON DELETE NO ACTION ON UPDATE NO ACTION,
    
  -- FK Eintraege
  FOREIGN KEY (eintragMontagId) REFERENCES digitalisierungAusbildungsnachweis.Eintrag (idEintrag) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (eintagDienstagId) REFERENCES digitalisierungAusbildungsnachweis.Eintrag (idEintrag) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (eintagMittwochId) REFERENCES digitalisierungAusbildungsnachweis.Eintrag (idEintrag) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (eintagDonnerstagId) REFERENCES digitalisierungAusbildungsnachweis.Eintrag (idEintrag) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (eintragFreitagId) REFERENCES digitalisierungAusbildungsnachweis.Eintrag (idEintrag) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (eintragSamstagId) REFERENCES digitalisierungAusbildungsnachweis.Eintrag (idEintrag) ON DELETE NO ACTION ON UPDATE NO ACTION
  );


-- -----------------------------------------------------
-- Table digitalisierungAusbildungsnachweis.Quittierung
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS digitalisierungAusbildungsnachweis.Quittierung (
  idQuittierung INT NOT NULL AUTO_INCREMENT,
  nachweisId INT NOT NULL,
  quittiert TINYINT NOT NULL,
  kommentar VARCHAR(45) NULL,
  
  -- PK
  PRIMARY KEY (idQuittierung),
  
  -- FK
  FOREIGN KEY (nachweisId) REFERENCES digitalisierungAusbildungsnachweis.Nachweis (idNachweis) ON DELETE NO ACTION ON UPDATE NO ACTION
  );
