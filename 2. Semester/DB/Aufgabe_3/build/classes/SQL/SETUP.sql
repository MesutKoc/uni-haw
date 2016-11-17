CREATE TABLE Benutzer (
  BenutzerID NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
  Vorname                            VARCHAR(45) NOT NULL,
  Nachname                           VARCHAR(45) NOT NULL,
  RegisterDate                       DATE        NOT NULL,
  Email                              VARCHAR(45) NOT NULL,
  Password_                          VARCHAR(255) NOT NULL,
  CONSTRAINT Benutzer_pk
  PRIMARY KEY(BenutzerID)
);


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

INSERT INTO Benutzer(Vorname, Nachname, email, REGISTERDATE, password_)
VALUES ('admin', 'Admin', 'Admin@email.de', '21.06.2015', 'admin');

INSERT INTO Rolle(Rolle) VALUES ('DB-Admin');
INSERT INTO Rolle(Rolle) VALUES ('Content-Admin');
INSERT INTO Rolle(Rolle) VALUES ('Birdwatcher');

INSERT INTO hat_rolle (benutzerid, rid)
SELECT BENUTZERID, 1
FROM benutzer WHERE vorname = 'admin';