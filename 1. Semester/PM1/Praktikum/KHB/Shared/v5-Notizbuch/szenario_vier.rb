require 'Notizbuch'

notizbuch = Notizbuch.new()
notizbuch.notiz_speichern("Einkaufen")
notizbuch.notiz_speichern("Aufraeumen")
notizbuch.notiz_speichern("Sport diese Woche")
notizbuch.notiz_speichern("Venedig Vortrag")
notizbuch.notiz_speichern("Eltern anrufen")


notizbuch.notizen_ausgeben
notizbuch.notiz_entfernen(1)
puts("Aufraeumen an den Freund delegiert")
notizbuch.notizen_ausgeben