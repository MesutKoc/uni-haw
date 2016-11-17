CREATE TABLE Beobachtungsort (
  BeobachtungsortID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  Region          VARCHAR(250) NOT NULL,
  Land VARCHAR(100), 
  Stadt VARCHAR(100), 
  CONSTRAINT Beobachtungsort_pk
  PRIMARY KEY(BeobachtungsortID)
);


/* 
  Erstelle Tabelle Vogelart mit
  @Name, @Art, @Gattung
*/
CREATE TABLE Vogelart (
  VID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  NAME_ENG VARCHAR(250),
  NAME_DEU VARCHAR(250),
  NAME_LAT VARCHAR(250),
  Art VARCHAR(250),
  Unterart VARCHAR(250),
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
  Zeitanfang        VARCHAR(250),
  Zeitende          VARCHAR(250),
  Kommentar         VARCHAR(250),
  PRIMARY KEY(BeobachtungsortID,VID),
  FOREIGN KEY(BeobachtungsortID) REFERENCES Beobachtungsort(BeobachtungsortID),
  FOREIGN KEY(VID)               REFERENCES Vogelart(VID),
  FOREIGN KEY(BenutzerID)        REFERENCES Benutzer(BenutzerID)
);