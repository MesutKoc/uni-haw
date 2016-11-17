require 'Notizbuch'

notizbuch = Notizbuch.new()
notizbuch.notiz_speichern("Eintaufen")
notizbuch.notiz_speichern("Aufraeumen")
notizbuch.notiz_speichern("Sport diese Woche")
notizbuch.notiz_speichern("Venedig Vortrag")
notizbuch.notiz_speichern("Eltern anrufen")

#notizbuch.notizen_ausgeben
#notizbuch.notiz_austauschen(0,"Einkaufen")
#puts("---- KORRIGIERT ----")
#notizbuch.notizen_ausgeben

puts(notizbuch.wort_finden_in_notiz("or"))
p(notizbuch.alle_notizen_mit_wort("or"))


notizbuch.tauschen("Eintaufen","XXXXX")
notizbuch.notizen_ausgeben()