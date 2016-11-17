#
 # Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# Ein kleines Tool zum Erstellen einer 4stelligen Zufallszahl, welche keine doppelten Elemente besitzt.

class NummerGenerator

  # Ruft sich rekursiv selbst auf, bis der @nummer_array 4 einzigartige Zahlen beinhaltet und liefert sie als String wieder.
  def generiere_nummer
    nummer_array = []
    nummer_array << rand(0..9)  while nummer_array.uniq.length < 4
    return nummer_array.uniq if nummer_array.uniq.length == 4
  end
end

 #puts [1,2,3,4].zip([4,5,6].permutation(3)).to_a.to_s
