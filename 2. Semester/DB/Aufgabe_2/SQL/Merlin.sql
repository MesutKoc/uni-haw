/* 
   Man muss zuerst die Relationen
   in Reihenfolge löschen 
*/
DROP TABLE CHECKLISTE; 
DROP TABLE BEOBACHTUNG;
DROP TABLE Beobachtungsort;
/*
   Und danach kann man die TAB
   löschen
*/
DROP TABLE Vogelart;
DROP TABLE Rolle;
DROP TABLE Benutzer;

/*
  Erstelle Tabelle Beobachtungsort mit:
  @BeobachtungsID, @Region, @Land, @Standt
*/
CREATE TABLE Beobachtungsort (
  BeobachtungsortID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  Region          VARCHAR(45) NOT NULL,
  Land VARCHAR(45), 
  Stadt VARCHAR(45), 
  CONSTRAINT Beobachtungsort_pk
  PRIMARY KEY(BeobachtungsortID)
);

/* 
  Erstelle Tabelle Vogelart mit
  @Name, @Art, @Gattung
*/
CREATE TABLE Vogelart (
  VID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  NAME_ENG VARCHAR(45),
  NAME_DEU VARCHAR(45),
  NAME_LAT VARCHAR(45),
  Art VARCHAR(45),
  Unterart VARCHAR(45),
  CONSTRAINT Vogelart_pk
  PRIMARY KEY(VID)
);

/* 
  Erstelle Tabelle Benutzer mit
  @BID, @Vorname, @Nachname, @Registierdatum, @Email, @Password
*/
CREATE TABLE Benutzer (
  BenutzerID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  Vorname                            VARCHAR(45) NOT NULL,
  Nachname                           VARCHAR(45) NOT NULL,
  RegisterDate                       DATE        NOT NULL,
  Email                              VARCHAR(45) NOT NULL,
  Password_                          VARCHAR(45) NOT NULL,
  CONSTRAINT Benutzer_pk
  PRIMARY KEY(BenutzerID)
);

/* 
  Erstelle Tabelle Rolle mit
  @RID, @Rolle, -BenutzerID
*/
CREATE TABLE Rolle(
  RID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  Rolle VARCHAR(45)                       NOT NULL,
  CONSTRAINT Rolle_pk PRIMARY KEY(RID)
);

CREATE TABLE Hat_Rolle(
  BenutzerID int not null,
  RID        int not null,
  PRIMARY KEY(BenutzerID, RID),
  FOREIGN KEY(BenutzerID) REFERENCES Benutzer(BenutzerID),
  FOREIGN KEY(RID)        REFERENCES Rolle(RID)
);

/* 
  Erstelle Tabellen-Relation Checkliste mit
  @BeobachtungsID, @VID
*/
CREATE TABLE Checkliste(
  BeobachtungsortID INT NOT NULL,
  VID               INT NOT NULL,
  PRIMARY KEY(BeobachtungsortID,VID),
  FOREIGN KEY(BeobachtungsortID) REFERENCES Beobachtungsort(BeobachtungsortID),
  FOREIGN KEY(VID)               REFERENCES Vogelart(VID)
);

/* 
  Erstelle Tabellen-Relation Beobachtung mit
  @BeobachtungsID, @VID, @BenutzerID, @Zeitraum, @Kommentar 
*/
CREATE TABLE Beobachtung(
  BeobachtungsortID INT NOT NUll,
  VID               INT NOT NULL,
  BenutzerID        INT NOT NULL,
  Zeitanfang        TIMESTAMP NOT NULL,
  Zeitende          TIMESTAMP NOT NULL,
  Kommentar         VARCHAR(250),
  PRIMARY KEY(BeobachtungsortID,VID),
  FOREIGN KEY(BeobachtungsortID) REFERENCES Beobachtungsort(BeobachtungsortID),
  FOREIGN KEY(VID)               REFERENCES Vogelart(VID),
  FOREIGN KEY(BenutzerID)        REFERENCES Benutzer(BenutzerID)
);

/* Stammdatenimport */
INSERT INTO Vogelart(NAME_ENG, NAME_LAT, Art, Unterart)
select B_ENGLISH_NAME, B_SCIENTIFIC_NAME, B_FAMILY, B_ORDER
FROM Merlin.Birds;

UPDATE Vogelart 
SET NAME_DEU = 
(SELECT DE_DEUTSCH
FROM MERLIN.BIRDS_DE mb
WHERE mb.DE_ENGLISCH = Vogelart.NAME_ENG);
