require "Notizbuch"

buch = Notizbuch.new()

buch.notiz_speichern("Di Task1")
buch.notiz_speichern("Di Task2")
buch.notiz_speichern("Mo Task3")
buch.notiz_speichern("Di Task4")

buch.notizen_nummerieren()
buch.alle_notizen_mit_wort_loeschen("Di")
puts
buch.notizen_nummerieren()