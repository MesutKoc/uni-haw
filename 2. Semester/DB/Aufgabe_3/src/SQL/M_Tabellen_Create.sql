CREATE TABLE Beobachtungsort (
  BeobachtungsortID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  Region          VARCHAR(250) NOT NULL,
  Land VARCHAR(100), 
  Stadt VARCHAR(100), 
  CONSTRAINT Beobachtungsort_pk
  PRIMARY KEY(BeobachtungsortID)
);


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

CREATE TABLE Checkliste(
  BeobachtungsortID INT NOT NULL,
  VID               INT NOT NULL,
  Lifer_Tick        Varchar(250),
  PRIMARY KEY(BeobachtungsortID,VID),
  FOREIGN KEY(BeobachtungsortID) REFERENCES Beobachtungsort(BeobachtungsortID),
  FOREIGN KEY(VID)               REFERENCES Vogelart(VID)
);

CREATE TABLE Beobachtung(
  BEOBACHTUNGSID   NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  BeobachtungsortID INT NOT NUll,
  VID               INT NOT NULL,
  BenutzerID        INT NOT NULL,
  Zeitanfang        VARCHAR(250),
  Zeitende          VARCHAR(250),
  Kommentar         VARCHAR(250),
  Datum             VARCHAR(250),
  Unterart             VARCHAR(250),
  CONSTRAINT BEOBACHTUNG_pk PRIMARY KEY(BeobachtungsortID,VID,BEOBACHTUNGSID),
  FOREIGN KEY(BeobachtungsortID) REFERENCES Beobachtungsort(BeobachtungsortID),
  FOREIGN KEY(VID)               REFERENCES Vogelart(VID),
  FOREIGN KEY(BenutzerID)        REFERENCES Benutzer(BenutzerID)
);