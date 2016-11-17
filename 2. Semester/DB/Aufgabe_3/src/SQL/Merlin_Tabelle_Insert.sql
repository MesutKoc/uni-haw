INSERT INTO Vogelart(NAme_Eng, NAME_LAT, Art)
SELECT B_ENGLISH_NAME as ENG, B_SCIENTIFIC_NAME as LAT , B_Category as Art
FROM Merlin.Birds MB
WHERE MB.B_CATEGORY='species';

INSERT INTO Vogelart(NAme_Eng, NAME_LAT, Unterart)
SELECT de_englisch as ENG, B_SCIENTIFIC_NAME as LAT , B_Category as Unterart
FROM Merlin.Birds MB
WHERE MB.B_CATEGORY='subspecies';

UPDATE vogelart
SET NAME_DEU = (SELECT DE_DEUTSCH
FROM MERLIN.BIRDS_DE mb
WHERE mb.DE_ENGLISCH = Vogelart.NAME_ENG);

UPDATE vogelart
SET NAME_DEU = (SELECT IOC_German_name
FROM MERLIN.BIRDS_IOC mbc
WHERE mbc.IOC_SCIENTIFIC_Name = Vogelart.NAME_LAT);

INSERT INTO Beobachtungsort(Region)
values('Westpaläarktis	(WP)');

INSERT INTO Beobachtungsort(Region,Land)
values('Westpaläarktis	(WP)','Deutschland');

INSERT INTO Beobachtungsort(Region,Land, Stadt)
values('Westpaläarktis	(WP)','Deutschland','Berlin');


INSERT INTO CHECKLISTE (BEOBACHTUNGSORTID, VID)
SELECT BEOBACHTUNGSORT.BEOBACHTUNGSORTID, VOGELART.VID
FROM BEOBACHTUNGSORT, MERLIN.BIRDS_DE DE, VOGELART
WHERE (BEOBACHTUNGSORT.REGION = 'Westpaläarktis	(WP)' AND BEOBACHTUNGSORT.LAND = 'Deutschland' AND BEOBACHTUNGSORT.STADT IS NULL) AND
(VOGELART.NAME_DEU = DE.DE_DEUTSCH);
