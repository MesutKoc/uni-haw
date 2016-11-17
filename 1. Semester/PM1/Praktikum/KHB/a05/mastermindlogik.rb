#
# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# Ein kleines Tool, welches zwei Arrays nach der Logik des Spiels MasterMind bewertet.

class MastermindLogik

  # Tipp und generierte_nummer müssen 4-Elementige Arrays mit den Zahlen 0..9 sein, ohne doppelte Elemente. Liefert ein Array mit Index 0 der weissen Marker und Index 1 der schwarzen Marker.
  def beide_marker_setzen(tipp,generierte_nummer)
    weisser_marker = 0
    schwarzer_marker = 0
    for i in (0..3) do
      weisser_marker += 1 if generierte_nummer.include?(tipp[i])
      schwarzer_marker += 1 if tipp[i] == generierte_nummer[i]
    end
    [(weisser_marker-schwarzer_marker),schwarzer_marker]
  end
end