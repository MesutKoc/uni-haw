require_relative 'nummergenerator'
require_relative 'mastermindlogik'
require_relative 'interaktion'
#require 'set'

#
# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# Ein Algorithmus, welcher in unter 10 Schritten anhand des Markerfeedbacks die richtige Kombination im Spiel Mastermind findet.

class MastermindAlgorithmus
  def knuth_algo(ziel)
    #alle_elemente = [0,1,2,3,4,5,6,7,8,9]   Moeglichkeiten sind: 10*9*8*7 = 5040 Kombinationen.
    tipp = []
    durchlauf = 0
    alle_moeglichkeiten = [0,1,2,3,4,5,6,7,8,9].permutation(4).to_a
    #File.write('ausgabealgo.txt',"")

    while alle_moeglichkeiten.size > 1
      alle_moeglichkeiten.size == 5040 ? tipp = [1,2,3,4] : tipp = alle_moeglichkeiten[0]
      auswertung = MastermindLogik.new.beide_marker_setzen(tipp,ziel)
      #open('ausgabealgo.txt','a') {|f| f.puts "Alle Moeglichkeiten sind #{alle_moeglichkeiten.to_s}\nZiel ist #{ziel}"}
      alle_moeglichkeiten.delete_if {|kombination| MastermindLogik.new.beide_marker_setzen(kombination, tipp) != auswertung}
      durchlauf +=1
     # open('ausgabealgo.txt','a') {|f| f.puts "Alle Moeglichkeiten nach Loeschung: #{alle_moeglichkeiten}\nDas war Durchlauf #{durchlauf}\n\n"}
     # open('ausgabealgo.txt','a') {|f| f.puts "Sie haben gewonnen! Ihre Kombination lautet #{alle_moeglichkeiten.flatten} und die Zielkombination lautet #{ziel}. Dafuer brauchten Sie #{durchlauf} Durchlaeufe."} if alle_moeglichkeiten.flatten == ziel
    end
    [alle_moeglichkeiten,durchlauf]
  end
end

test = MastermindAlgorithmus.new
test.knuth_algo(NummerGenerator.new.generiere_nummer)

