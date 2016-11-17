require 'Notizbuch'

notizbuch = Notizbuch.new()
notizbuch.notiz_speichern("Einkaufen")
notizbuch.notiz_speichern("Aufräumen")
notizbuch.notiz_speichern("Sport diese Woche")
notizbuch.notiz_speichern("Venedig Vortrag")
notizbuch.notiz_speichern("Eltern anrufen")

notizbuch.notiz_zeigen(0)
notizbuch.notiz_zeigen(2)
notizbuch.notiz_zeigen(4)




