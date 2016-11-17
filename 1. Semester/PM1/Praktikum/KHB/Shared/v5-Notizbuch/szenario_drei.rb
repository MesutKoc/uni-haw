require 'Notizbuch'

notizbuch = Notizbuch.new()
notizbuch.notiz_speichern("Einkaufen")
notizbuch.notiz_speichern("Aufraeumen")
notizbuch.notiz_speichern("Sport diese Woche")
notizbuch.notiz_speichern("Venedig Vortrag")
notizbuch.notiz_speichern("Eltern anrufen")

puts("Aufraeumen an den Freund delegiert")
notizbuch.notiz_entfernen(1)
notizbuch