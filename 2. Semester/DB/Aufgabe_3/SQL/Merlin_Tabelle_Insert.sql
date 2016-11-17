Insert into Vogelart(NAme_Eng, NAME_LAT, Art)
select B_ENGLISH_NAME as ENG, B_SCIENTIFIC_NAME as LAT , B_Category as Art
from Merlin.Birds MB
where MB.B_CATEGORY='species';

/*Kontrolle, ob die Arten korrekt in unsere Datanbank eingefügt worden sind. */
select *
from vogelart;

/*Daten einfügen in unsere Vogelart, die eine Unterart bilden. */
Insert into Vogelart(NAme_Eng, NAME_LAT, Unterart)
select B_ENGLISH_NAME as ENG, B_SCIENTIFIC_NAME as LAT , B_Category as Unterart
from Merlin.Birds MB
where MB.B_CATEGORY='subspecies';


/*Nachdem die Englischen und die Latainischen Namen eingefügt worden sind, aktualisieren wir die Tabelle Vogelart 
und fügen die deutschen Namen der Vögel hinzu. Dafür vergleichen wir die englischen Namen der Tabelle BIRDS_DE und 
unserer Vogeltabelle.*/
update vogelart
set NAME_DEU=(SELECT DE_DEUTSCH
FROM MERLIN.BIRDS_DE mb
WHERE mb.DE_ENGLISCH = Vogelart.NAME_ENG);


/*Das gleiche Update führen wir mit der Tabelle BIRDS_IOC durch, da es dort auch zusätzlich deutsche NAmen gibt. */
update vogelart
set NAME_DEU=(SELECT IOC_German_name
FROM MERLIN.BIRDS_IOC mbc
WHERE mbc.IOC_ENGLISH_Name = Vogelart.NAME_ENG);

/*Ein Testdatensatz für unsere Tabelle Ort. Wir fügen dort die Region und das Land Deutschland hinzu.
Da es in der Tabelle Birds unter B_Range keine Region Westpaläarktis gibt, fügen wir das manuell hinzu. */
insert into Beobachtungsort(Region)
values('Westpaläarktis	(WP)');

/*Ein weiterer Datensatz für die Region , jedoch diesmal mit Land und Stadt als NULL VALUE. */
insert into Beobachtungsort(Region)
values('Westpaläarktis	(WP)');

/*Wir fügen alle uns zur Verfügung stehenden Regionen hinzu aus der Tabelle Birds. Wir nehmen an, dass die B_Range unsere Region ist. */
INSERT INTO BEOBACHTUNGSORT(Region)
SELECT DISTINCT merlin.birds.b_range
FROM merlin.birds where merlin.birds.b_range is not null;

/*Unserer interpretation nach , gibt es alle Vögel mit deutschen NAmen auch in Deutschland bzw. wir definieren das. Demnach setzt sich die Checkliste aus den 
Vögel mit deutschen NAmen und Region Westpaläarktis und dem Land Deutschland*/
insert into Checkliste(BeobachtungsortID, VID)
select 1, VID
from   Vogelart V, merlin.birds_de mb
where v.Name_DEu is not null
and v.name_lat = mb.de_latein;

select *
from Beobachtungsort
where BEOBACHTUNGSORTID='1';
